package org.isma.tools.jars;

import org.isma.tools.jars.model.*;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class JarNodeMergerTest {
    @Test
    public void testMerge() throws Exception {
        JarNode root1 = JavaNode.buildRoot("jar1");
        root1.addPackage(
                new PackageNode("org")).
                addPackage(new PackageNode("isma")).
                addClass(new ClassNode("IsmaClass.class"));

        JarNode root2 = JavaNode.buildRoot("jar2");
        root2.addPackage(
                new PackageNode("org")).
                addPackage(new PackageNode("isma")).
                addClass(new ClassNode("IsmaClass.class"));

        JarNodeMerger merger = new JarNodeMerger();
        ClassCache duplicateClasses = merger.merge(root1, root2);
        List<ClassNode> classes = duplicateClasses.getClasses("org/isma/IsmaClass.class");
        assertEquals("[jar1:org/isma/IsmaClass.class, jar2:org/isma/IsmaClass.class]", classes.toString());
    }
}
