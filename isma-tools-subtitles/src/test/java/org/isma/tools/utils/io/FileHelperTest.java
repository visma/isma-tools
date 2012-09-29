package org.isma.tools.utils.io;

import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;
import static org.isma.tools.utils.io.FileHelper.getExtension;

public class FileHelperTest {

    @Test
    public void testExtension() throws Exception {
        assertEquals("txt", getExtension(new File("toto.txt")));
        assertEquals("txt", getExtension(new File("toto.la.txt")));
        assertEquals(null, getExtension(new File("toto")));
    }
}
