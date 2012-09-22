package org.isma.tools.jars;

import org.isma.tools.jars.model.*;
import org.junit.Test;

import static java.lang.String.format;
import static junit.framework.Assert.assertEquals;

public class DuplicatesAnalyzerTest {
    private DuplicatesAnalyzer analyzer = new DuplicatesAnalyzer();

    @Test
    public void testDuplicates() throws Exception {
        JarNode root1 = JavaNode.buildRoot("jar1");
        JarNode root2 = JavaNode.buildRoot("jar2");

        root1.addPackage(
                new PackageNode("org")).
                addPackage(new PackageNode("isma")).
                addClass(new ClassNode("IsmaClass.class"));
        ((PackageNode) root1.getChild("org/isma")).addClass(new ClassNode("UniqueClass.class"));

        root2.addPackage(
                new PackageNode("org")).
                addPackage(new PackageNode("isma")).
                addClass(new ClassNode("IsmaClass.class"));

        ClassCache classCache = analyzer.getDuplicates(root1, root2);
        String path = "org/isma/IsmaClass.class";
        assertEquals(format("[%s]", path), classCache.getPaths().toString());
        assertEquals(format("[jar1:%s, jar2:%s]", path, path), classCache.getClasses(path).toString());
    }
}
