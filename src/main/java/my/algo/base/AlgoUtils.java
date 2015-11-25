package my.algo.base;

import org.apache.log4j.Logger;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class AlgoUtils {

    private static final Logger LOG = Logger.getLogger(AlgoUtils.class.getName());

    private AlgoUtils() {
    }

    public static long timePassed(long start) {
        return System.nanoTime() - start;
    }

    private static double timePassedInSec(long start) {
        return NANOSECONDS.toSeconds(timePassed(start));
    }

    public static long timePassedPrint(String message, long start) {
        long timePassed = timePassed(start);
        LOG.debug(format("%s  T=%d", message, NANOSECONDS.toSeconds(timePassed)));
        return start + timePassed;
    }

    public static void timeCyclePrint(String message, long cycle, long step, long start) {
        if (cycle % step == 0) {
            LOG.debug(format("%s cycle=%d T=%s", message, cycle, timePassedInSec(start)));
        }
    }

}