package org.isma.tools.jars;

import org.isma.tools.jars.model.PackageNode;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static junit.framework.Assert.assertEquals;

public class JarLoaderTest {
    public static final String JAR_FILE = "jar.jar";
    private File jar;

    @Before
    public void setUp() throws Exception {
        URL resource = getClass().getClassLoader().getResource("files/" + JAR_FILE);
        jar = new File(resource.toURI());
    }

    @Test
    public void testLoad() throws Exception {
        JarLoader jarLoader = new JarLoader();

        PackageNode rootNode = jarLoader.load(jar);
        PackageNode org = (PackageNode)rootNode.getChildren().get(0);
        PackageNode isma = (PackageNode)org.getChildren().get(0);

        assertEquals("[jar:org, jar:RootClass1.class, jar:RootClass2.class]", rootNode.getChildren().toString());
        assertEquals("[jar:org/isma, jar:org/OrgClass1.class, jar:org/OrgClass2.class]", org.getChildren().toString());
        assertEquals("[jar:org/isma/IsmaClass1.class]", isma.getChildren().toString());
    }
}
