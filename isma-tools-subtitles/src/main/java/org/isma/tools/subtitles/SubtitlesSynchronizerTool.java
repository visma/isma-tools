package org.isma.tools.subtitles;

import org.isma.tools.subtitles.format.UnsupportedSubtitlesFormat;

import java.io.IOException;

public class SubtitlesSynchronizerTool {

    public static void main(String[] args) throws IOException, UnsupportedSubtitlesFormat {
        SubtitlesSynchronizer synchronizer = new SubtitlesSynchronizer();
        String subtitlesFilePath = null;
        int delay = -1298;
        synchronizer.doSynchro(subtitlesFilePath, delay);
    }
}
