package my.algo.greedy;

import com.google.common.collect.Lists;
import my.algo.greedy.ScheduleJobs.Algo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.Collections.EMPTY_LIST;
import static my.algo.greedy.JobTestHelper.jobsToDifferentiateAlgosStream;
import static my.algo.greedy.JobTestHelper.noJobsStream;
import static my.algo.greedy.JobTestHelper.oneJobStream;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public static Collection<Object[]> testCases1() {
        return Arrays.asList(new Object[][]{
                {Algo.DIFF_WEIGHT_LENGTH, noJobsStream, 0L},
                {Algo.RATIO_WEIGHT_LENGTH, noJobsStream, 0L},
                {Algo.DIFF_WEIGHT_LENGTH, jobsToDifferentiateAlgosStream, 23L},
                {Algo.RATIO_WEIGHT_LENGTH, jobsToDifferentiateAlgosStream, 22L},
                {Algo.DIFF_WEIGHT_LENGTH, oneJobStream, 4L},
                {Algo.RATIO_WEIGHT_LENGTH, oneJobStream, 4L}
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