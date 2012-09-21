package org.isma.tools.subtitles;

import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeSynchronizer {
    private String datePattern;
    private Duration duration;

    public TimeSynchronizer(String datePattern, Duration duration) {
        this.datePattern = datePattern;
        this.duration = duration;
    }

    public String synchronize(String timeToSynchronize) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(datePattern);
        Instant instant = Instant.parse(timeToSynchronize, dateTimeFormatter);
        Instant newInstant = instant.plus(duration).plus(Hours.hours(1).toStandardDuration());
        return dateTimeFormatter.print(newInstant);
    }
}
