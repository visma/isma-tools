package org.isma.tools.jars.model;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class PackageNodeTest {
    private PackageNode root;
    private PackageNode org;
    private PackageNode isma;
    private ClassNode orgClass;
    private ClassNode ismaClass;
    private ClassNode rootClass;

    @Before
    public void setUp() throws Exception {
        root = JavaNode.buildRoot("thejar");
        org = new PackageNode("org");
        isma = new PackageNode("isma");
        orgClass = new ClassNode("OrgClass.class");
        ismaClass = new ClassNode("IsmaClass.class");
        rootClass = new ClassNode("RootClass.class");

        root.addPackage(org);
    }

    @Test
    public void testRoot() throws Exception {
        PackageNode rootNode = new JarNode("thejar");
        PackageNode notRootNode = new PackageNode("package");
        assertTrue(rootNode.isRoot());
        assertFalse(notRootNode.isRoot());
        assertEquals("thejar", rootNode.toString());
    }

    @Test
    public void testAddPackage() throws Exception {
        org.addPackage(isma);

        assertEquals("thejar:org", org.getPath());
        assertEquals("thejar:org/isma", isma.getPath());
    }

    @Test
    public void testAddSamePackage() throws Exception {
        org.addPackage(new PackageNode("duplicate"));
        assertEquals(1, org.getChildren().size());
        org.addPackage(new PackageNode("duplicate"));
        assertEquals(1, org.getChildren().size());
    }

    @Test
    public void testAddClass() throws Exception {
        org.addPackage(isma).addClass(ismaClass);

        assertEquals("thejar:org", org.getPath());
        assertEquals("thejar:org/isma", isma.getPath());
        assertEquals("thejar:org/isma/IsmaClass.class", ismaClass.getPath());
    }

    @Test
    public void testChildren() throws Exception {
        org.addPackage(isma).addClass(ismaClass);
        org.addClass(orgClass);

        assertEquals("[thejar:org/isma, thejar:org/OrgClass.class]", org.getChildren().toString());
        assertEquals("[thejar:org/isma/IsmaClass.class]", isma.getChildren().toString());
    }

    @Test
    public void testParent() throws Exception {
        org.addPackage(isma).addClass(ismaClass);
        org.addClass(orgClass);

        assertEquals(org, isma.getParent());
        assertEquals(org, orgClass.getParent());
        assertEquals(isma, ismaClass.getParent());
    }

    @Test
    public void testChild() throws Exception {
        org.addPackage(isma).addClass(ismaClass);
        Assert.assertEquals(org, root.getChild("org"));
        Assert.assertEquals(isma, root.getChild("org/isma"));
        Assert.assertEquals(ismaClass, root.getChild("org/isma/IsmaClass.class"));
    }

    @Test
    public void testPath() throws Exception {
        root.addClass(rootClass);
        org.addPackage(isma).addClass(ismaClass);
        org.addClass(orgClass);
        assertEquals("thejar", root.getPath());
        assertEquals("thejar:RootClass.class", rootClass.getPath());
        assertEquals("thejar:org", org.getPath());
        assertEquals("thejar:org/isma", isma.getPath());
        assertEquals("thejar:org/isma/IsmaClass.class", ismaClass.getPath());
    }

    @Test
    public void testPaths() throws Exception {
        org.addPackage(isma).addClass(ismaClass);
        org.addClass(orgClass);
        assertEquals("[thejar:org/OrgClass.class, thejar:org/isma/IsmaClass.class]", org.getPaths().toString());
    }

    @Test
    public void testClasses() throws Exception {
        org.addPackage(isma).addClass(ismaClass);
        org.addClass(orgClass);
        assertEquals("[thejar:org/OrgClass.class]", org.getClasses().toString());
    }

    @Test
    public void testPackages() throws Exception {
        org.addPackage(isma).addClass(ismaClass);
        org.addPackage(new PackageNode("isma2"));
        org.addClass(orgClass);
        assertEquals("[thejar:org/isma, thejar:org/isma2]", org.getPackages().toString());
    }

    @Test
    public void testCountClasses() throws Exception {
        org.addPackage(isma).addClass(ismaClass);
        isma.addClass(ismaClass);
        assertEquals(2, isma.getClassCount(ismaClass.getName()));
    }

}
