package org.isma.tools.subtitles.format;

import org.isma.utils.io.FileUtils;

import java.io.File;

public class SubtitlesFormatFactory {
    private enum FormatEnum {
        SRT(new SrtSubtitlesFormat(), "srt");

        private SubtitlesFormat format;
        private String extension;

        private FormatEnum(SubtitlesFormat format, String extension) {
            this.format = format;
            this.extension = extension;
        }

        public String getExtension() {
            return extension;
        }

        public SubtitlesFormat getFormat() {
            return format;
        }
    }

    public SubtitlesFormat getFormat(File file) throws UnsupportedSubtitlesFormat {
        String extension = FileUtils.getExtension(file).toLowerCase();
        for (FormatEnum formatEnum : FormatEnum.values()) {
            if (formatEnum.getExtension().equals(extension)) {
                return formatEnum.getFormat();
            }
        }
        throw new UnsupportedSubtitlesFormat(extension);
    }
}
