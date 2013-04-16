package org.isma.tools.subtitles;


import org.isma.tests.AbstractFileTestCase;
import org.joda.time.Duration;
import org.junit.Test;

import java.io.File;

public class SubtitlesSynchronizerTest extends AbstractFileTestCase {
    private SubtitlesSynchronizer subtitlesSynchronizer = new SubtitlesSynchronizer();

    @Override
    protected String getFileDirResources() {
        return super.getFileDirResources() + "subtitles" + System.getProperty("file.separator");
    }

    @Test
    public void testWin1252Format() throws Exception {

        File expectedFile = copyFile("subtitles", "win1252_format_00min15sec.srt");
        File unSynchroFile = copyFile("subtitles", "win1252_format_00min05sec.srt");

        File actualFile = subtitlesSynchronizer.doSynchro(unSynchroFile, Duration.parse("PT10.0S"));
        assertFiles(expectedFile, actualFile);
        assertEquals(actualFile.getName(), "win1252_format_00min05sec[Resync by org.isma].srt");
    }

    @Test
    public void testVideoHasContentBeforeSubtitles() throws Exception {
        File expectedFile = copyFile("subtitles", "sample_B_start_00min18sec.srt");
        File unSynchroFile = copyFile("subtitles", "sample_B_start_21min56sec.srt");

        File actualFile = subtitlesSynchronizer.doSynchro(unSynchroFile, Duration.parse("PT-1298.0S"));
        assertFiles(expectedFile, actualFile);
        assertEquals(actualFile.getName(), "sample_B_start_21min56sec[Resync by org.isma].srt");
    }

}
