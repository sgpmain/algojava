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
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class ScheduleJobsForNullTest {

    private final Algo algo;
    private final Supplier<List<Job>> jobsSupplier;

    public ScheduleJobsForNullTest(Algo algo, Supplier<List<Job>> jobsSupplier) {
        this.algo = algo;
        this.jobsSupplier = jobsSupplier;
    }


    @Parameterized.Parameters(name = "{index}: {0}}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {Algo.DIFF_WEIGHT_LENGTH, NULL_JOBS},
                {Algo.RATIO_WEIGHT_LENGTH, NULL_JOBS},
        });
    }

    @Test(expected = NullPointerException.class)
    public void givenNullJobListShouldThrowException() throws Exception {
        // when
        algo.compute(jobsSupplier.get());
        // then
        fail("Not thrown NullPointerException");
    }


}