package org.isma.tools.subtitles.format;

public class SrtSubtitlesFormat implements SubtitlesFormat {

    public static final String TIME_SEPARATOR = "-->";

    public boolean containsPeriods(String subtitleLine) {
        return subtitleLine.contains(TIME_SEPARATOR);
    }

    public String[] getPeriod(String subtitleLine) {
        String[] period = subtitleLine.split(TIME_SEPARATOR);
        period[0] = period[0].trim();
        period[1] = period[1].trim();
        return period;
    }

    public String getDatePattern() {
        return "HH:mm:ss,SSS";
    }

    public String formatLineWithSynchronizedPeriod(String[] period) {
        return String.format("%s %s %s", period[0], TIME_SEPARATOR, period[1]);
    }
}
