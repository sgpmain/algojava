package my.algo.greedy;

import my.algo.greedy.ScheduleJobs.Algo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static my.algo.greedy.JobTestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class ScheduleJobsStreamForNullTest {

    private final Algo algo;
    private final Supplier<Stream<Job>> jobsSupplier;

    public ScheduleJobsStreamForNullTest(Algo algo, Supplier<Stream<Job>> jobsSupplier) {
        this.algo = algo;
        this.jobsSupplier = jobsSupplier;
    }


    @Parameterized.Parameters(name = "{index}: {0}}")
    public static Collection<Object[]> testCases1() {
        return Arrays.asList(new Object[][]{
                {Algo.DIFF_WEIGHT_LENGTH, nullJobsStream},
                {Algo.RATIO_WEIGHT_LENGTH, nullJobsStream},
        });
    }

    @Test(expected = NullPointerException.class)
    public void givenNullJobStreamShouldThrowException() throws Exception {
        // when
        algo.compute(jobsSupplier.get());
        // then
        fail("Not thrown NullPointerException");
    }
}