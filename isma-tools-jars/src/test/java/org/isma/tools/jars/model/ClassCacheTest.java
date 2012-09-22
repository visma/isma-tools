package org.isma.tools.jars.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ClassCacheTest {
    private ClassCache classCache;

    private JarNode root;
    private JarNode root2;
    private PackageNode org;
    private PackageNode org2;
    private ClassNode orgClass;
    private ClassNode orgClass2;

    @Before
    public void setUp() throws Exception {
        root = JavaNode.buildRoot("thejar");
        root2 = JavaNode.buildRoot("thejar2");
        org = new PackageNode("org");
        org2 = new PackageNode("org");
        orgClass = new ClassNode("OrgClass.class");
        orgClass2 = new ClassNode("OrgClass.class");

        root.addPackage(org).addClass(orgClass);
        root2.addPackage(org2).addClass(orgClass2);

        classCache = new ClassCache();
    }

    @Test
    public void testNames() throws Exception {
        classCache.addClass(orgClass);
        classCache.addClass(orgClass2);

        assertEquals("[org/OrgClass.class]", classCache.getPaths().toString());
    }

    @Test
    public void testClasses() throws Exception {
        classCache.addClass(orgClass);
        classCache.addClass(orgClass2);

        assertEquals(orgClass, classCache.getClasses("org/OrgClass.class").get(0));
        assertEquals(orgClass2, classCache.getClasses("org/OrgClass.class").get(1));
    }
}
