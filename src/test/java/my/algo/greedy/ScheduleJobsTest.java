package my.algo.greedy;

import my.algo.greedy.ScheduleJobs.Algo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static my.algo.greedy.JobTestHelper.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ScheduleJobsTest {

    private final Algo algo;
    private final Supplier<List<Job>> jobsSupplier;
    private final long expectedCompletionCost;

    public ScheduleJobsTest(Algo algo, Supplier<List<Job>> jobsSupplier, long expectedCompletionCost) {
        this.algo = algo;
        this.jobsSupplier = jobsSupplier;
        this.expectedCompletionCost = expectedCompletionCost;
    }


    @Parameterized.Parameters(name = "{index}: {0}}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {Algo.DIFF_WEIGHT_LENGTH, NO_JOBS, 0L},
                {Algo.RATIO_WEIGHT_LENGTH, NO_JOBS, 0L},
                {Algo.DIFF_WEIGHT_LENGTH, JOBS_TO_DIFFERENTIATE_ALGOS, 23L},
                {Algo.RATIO_WEIGHT_LENGTH, JOBS_TO_DIFFERENTIATE_ALGOS, 22L},
                {Algo.DIFF_WEIGHT_LENGTH, ONE_JOB_LIST, 4L},
                {Algo.RATIO_WEIGHT_LENGTH, ONE_JOB_LIST, 4L}
        });
    }

    @Test
    public void givenJobListComputeCompletionCost() throws Exception {
        // when
        final long completionCost = algo.compute(jobsSupplier.get());
        // then
        assertEquals(expectedCompletionCost, completionCost);
    }


}