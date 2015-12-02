package my.algo.greedy;

import my.algo.greedy.ScheduleJobs.Algo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static my.algo.greedy.JobTestHelper.NO_JOBS_STREAM;
import static my.algo.greedy.JobTestHelper.JOBS_TO_DIFFERENTIATE_ALGOS_STREAM;
import static my.algo.greedy.JobTestHelper.ONE_JOB_STREAM;

@RunWith(Parameterized.class)
public class ScheduleJobsStreamTest {

    private final Algo algo;
    private final Supplier<Stream<Job>> jobsSupplier;
    private final long expectedCompletionCost;

    public ScheduleJobsStreamTest(Algo algo, Supplier<Stream<Job>> jobsSupplier, long expectedCompletionCost) {
        this.algo = algo;
        this.jobsSupplier = jobsSupplier;
        this.expectedCompletionCost = expectedCompletionCost;
    }


    @Parameterized.Parameters(name = "{index}: {0}}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {Algo.DIFF_WEIGHT_LENGTH, NO_JOBS_STREAM, 0L},
                {Algo.RATIO_WEIGHT_LENGTH, NO_JOBS_STREAM, 0L},
                {Algo.DIFF_WEIGHT_LENGTH, JOBS_TO_DIFFERENTIATE_ALGOS_STREAM, 23L},
                {Algo.RATIO_WEIGHT_LENGTH, JOBS_TO_DIFFERENTIATE_ALGOS_STREAM, 22L},
                {Algo.DIFF_WEIGHT_LENGTH, ONE_JOB_STREAM, 4L},
                {Algo.RATIO_WEIGHT_LENGTH, ONE_JOB_STREAM, 4L}
        });
    }

    @Test
    public void givenJobStreamComputeCompletionCost() throws Exception {
        // when
        final long completionCost = algo.compute(jobsSupplier.get());
        // then
        assertEquals(expectedCompletionCost, completionCost);
    }
}