package org.isma.tools.subtitles;

import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.isma.utils.io.FileUtils.forceDeleteIfExists;

public abstract class AbstractFileTestCaseAJeter extends TestCase {
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    protected static final String TEST_ROOT_DIR = "_test_dir" + FILE_SEPARATOR;
    protected static final String FILE_RESOURCES = "files" + FILE_SEPARATOR;

    @Override
    public void setUp() throws Exception {
        File rootDir = new File(TEST_ROOT_DIR);
        forceDeleteIfExists(rootDir);
        rootDir.mkdir();
    }


    @Override
    public void tearDown() throws Exception {
        forceDeleteIfExists(new File(TEST_ROOT_DIR));
    }

    protected void assertFiles(File expected, File actual) throws IOException {
        String encoding = "windows-1252";
        assertTrue(String.format("expected =>\n%s\nactual =>\n%s",
                FileUtils.readFileToString(expected, encoding),
                FileUtils.readFileToString(actual, encoding)),
                FileUtils.contentEquals(expected, actual));
    }

    protected File copyFile(String dir, String name) throws Exception {
        URL resource = getClass().getClassLoader().getResource(getFileDirResources() + name);
        File src = new File(resource.toURI());
        File dst = new File(TEST_ROOT_DIR + dir + FILE_SEPARATOR + name);
        FileUtils.copyFile(src, dst);
        return dst;
    }

    protected String getFileDirResources() {
        return FILE_RESOURCES;
    }


    protected File mkdir(String dirPath) {
        File dir = new File(TEST_ROOT_DIR + dirPath);
        dir.mkdir();
        return dir;
    }


}
