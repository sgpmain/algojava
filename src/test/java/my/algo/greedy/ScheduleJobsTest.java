package my.algo.greedy;

import com.google.common.collect.Lists;
import my.algo.greedy.ScheduleJobs.Algo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class ScheduleJobsTest {

//    private static final Function<String, Job> STR_TO_JOB_FUNCTION = s -> new Job(s.split(" "));

    private final Algo algo;
    private final Supplier<List<Job>> jobsSupplier;
    private final long expectedCompletionCost;

    public ScheduleJobsTest(Algo algo, Supplier<List<Job>> jobsSupplier, long expectedCompletionCost) {
        this.algo = algo;
        this.jobsSupplier = jobsSupplier;
        this.expectedCompletionCost = expectedCompletionCost;
    }


    @Parameterized.Parameters(name = "{index}: {0}}")
    public static Collection<Object[]> testCases1() {
        return Arrays.asList(new Object[][]{
                {Algo.DIFF_WEIGHT_LENGTH, noJobs, 0L},
                {Algo.RATIO_WEIGHT_LENGTH, noJobs, 0L},
                {Algo.DIFF_WEIGHT_LENGTH, jobsToDifferentiateAlgos, 23L},
                {Algo.RATIO_WEIGHT_LENGTH, jobsToDifferentiateAlgos, 22L},
                {Algo.DIFF_WEIGHT_LENGTH, oneJobList, 4L},
                {Algo.RATIO_WEIGHT_LENGTH, oneJobList, 4L}
        });
    }

    @Test
    public void givenJobListComputeCompletionCost() throws Exception {


        // when
        final long completionCost = algo.compute(jobsSupplier.get());
        // then
        Assert.assertEquals(expectedCompletionCost, completionCost);
    }

    private static Supplier<List<Job>> noJobs = () -> EMPTY_LIST;
    private static Supplier<List<Job>> jobsToDifferentiateAlgos  = () -> {
        Job job1 = mock(Job.class);
        when(job1.getWeight()).thenReturn(3L);
        when(job1.getLength()).thenReturn(5L);
        Job job2 = mock(Job.class);
        when(job2.getWeight()).thenReturn(1L);
        when(job2.getLength()).thenReturn(2L);

        return Lists.newArrayList(job1, job2);
    };

    private static Supplier<List<Job>> oneJobList = () -> {
        Job job1 = mock(Job.class);
        when(job1.getWeight()).thenReturn(2L);
        when(job1.getLength()).thenReturn(2L);
        return Lists.newArrayList(job1);
    };

}