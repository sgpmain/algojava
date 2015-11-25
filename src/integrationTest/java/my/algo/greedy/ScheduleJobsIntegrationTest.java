package my.algo.greedy;

import my.algo.base.DataUtils;
import my.algo.greedy.ScheduleJobs.Algo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.lang.Boolean.TRUE;

@RunWith(Parameterized.class)
public class ScheduleJobsIntegrationTest {

    private static final Function<String, Job> STR_TO_JOB_FUNCTION = s -> new Job(s.split(" "));

    private final Algo algo;
    private final String fName;
    private final long expectedCompletionCost;

    public ScheduleJobsIntegrationTest(Algo algo, String fName, long expectedCompletionCost) {
        this.algo = algo;
        this.fName = fName;
        this.expectedCompletionCost = expectedCompletionCost;
    }


    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> testCases1() {
        return Arrays.asList(new Object[][]{
                {Algo.DIFF_WEIGHT_LENGTH, "data/jobs.txt", 69119377652L},
                {Algo.DIFF_WEIGHT_LENGTH, "data/jobs1-20000-19400.txt", 20000},
                {Algo.DIFF_WEIGHT_LENGTH, "data/jobs2-40135-38638.txt", 40135},
                {Algo.DIFF_WEIGHT_LENGTH, "data/jobs3-7226993-7078040.txt", 7226993},
                {Algo.RATIO_WEIGHT_LENGTH, "data/jobs.txt", 67311454237L},
                {Algo.RATIO_WEIGHT_LENGTH, "data/jobs1-20000-19400.txt", 19400},
                {Algo.RATIO_WEIGHT_LENGTH, "data/jobs2-40135-38638.txt", 38638},
                {Algo.RATIO_WEIGHT_LENGTH, "data/jobs3-7226993-7078040.txt", 7078040}
        });
    }

    @Test
    public void testScheduleJobsCase() throws Exception {

        // given
        Boolean headLine = TRUE;
        List<Job> jobs = DataUtils.readInpData(fName, STR_TO_JOB_FUNCTION, headLine);
        // when
        final long completionCost = algo.compute(jobs);
        // then
        Assert.assertEquals(expectedCompletionCost, completionCost);
    }

}