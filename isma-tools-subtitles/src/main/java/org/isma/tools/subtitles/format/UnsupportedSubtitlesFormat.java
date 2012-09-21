package org.isma.tools.subtitles.format;

public class UnsupportedSubtitlesFormat extends Exception {
    public UnsupportedSubtitlesFormat(String extension) {
        super("subtitles format ." + extension + " not supported");
    }
}
