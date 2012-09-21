package org.isma.tools.subtitles.format;

public interface SubtitlesFormat {
    public boolean containsPeriods(String subtitleLine);

    public String[] getPeriod(String subtitleLine);

    public String getDatePattern();

    public String formatLineWithSynchronizedPeriod(String[] period);
}
