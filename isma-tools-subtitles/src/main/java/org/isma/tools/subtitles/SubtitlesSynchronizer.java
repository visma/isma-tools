package org.isma.tools.subtitles;

import org.apache.commons.io.FileUtils;
import org.isma.tools.subtitles.format.SubtitlesFormat;
import org.isma.tools.subtitles.format.SubtitlesFormatFactory;
import org.isma.tools.subtitles.format.UnsupportedSubtitlesFormat;
import org.isma.tools.utils.io.FileHelper;
import org.joda.time.Duration;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;
import static org.apache.commons.io.FileUtils.writeStringToFile;

public class SubtitlesSynchronizer {
    public static final String LINE_SEPARATOR = getProperty("line.separator");
    //    public static final String ENCODING = "UTF-8";
    public static final String ENCODING = "windows-1252";
    private final SubtitlesFormatFactory formatFactory = new SubtitlesFormatFactory();

    public File doSynchro(String subtitlesFilePath, int delay) throws IOException, UnsupportedSubtitlesFormat {
        String durationPattern = String.format("PT%s.0S", delay);
        return doSynchro(new File(subtitlesFilePath), Duration.parse(durationPattern));
    }

    public File doSynchro(File subtitlesFile, Duration duration) throws IOException, UnsupportedSubtitlesFormat {
        SubtitlesFormat format = formatFactory.getFormat(subtitlesFile);
        TimeSynchronizer timeSynchronizer = new TimeSynchronizer(format.getDatePattern(), duration);

        StringBuilder newContent = new StringBuilder();
        String fileContent = FileUtils.readFileToString(subtitlesFile, ENCODING);
        for (String originalLine : fileContent.split(LINE_SEPARATOR)) {
            if (!format.containsPeriods(originalLine)) {
                newContent.append(originalLine).append(LINE_SEPARATOR);
                continue;
            }
            String[] period = format.getPeriod(originalLine);
            period[0] = timeSynchronizer.synchronize(period[0]);
            period[1] = timeSynchronizer.synchronize(period[1]);
            newContent.append(format.formatLineWithSynchronizedPeriod(period)).append(LINE_SEPARATOR);
        }

        File resyncSubtitlesFile = createEmptyOuputFile(subtitlesFile);
        writeStringToFile(resyncSubtitlesFile, newContent.toString(), ENCODING);
        return resyncSubtitlesFile;
    }


    private File createEmptyOuputFile(File subtitlesFile) {
        String extension = FileHelper.getExtension(subtitlesFile);
        String fileName = subtitlesFile.getName();
        int lastIndexOfExtension = fileName.lastIndexOf(extension);
        String outputFileName = fileName.substring(0, lastIndexOfExtension - 1) + "[Resync by org.isma]." + extension;
        return new File(subtitlesFile.getParentFile(), outputFileName);
    }
}
