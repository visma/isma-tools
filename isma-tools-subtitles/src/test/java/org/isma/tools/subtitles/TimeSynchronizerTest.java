package org.isma.tools.subtitles;

import junit.framework.TestCase;
import org.joda.time.Duration;


public class TimeSynchronizerTest extends TestCase {

    public void testTimeSynchronizer() {
        TimeSynchronizer timeSynchronizer = new TimeSynchronizer("HH:mm:ss,SSS", Duration.parse("PT-1200.0S"));
        assertEquals("00:01:02,593", timeSynchronizer.synchronize("00:21:02,593"));
    }


}
