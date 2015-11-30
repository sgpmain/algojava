package my.algo.greedy;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JobTestHelper {
    private JobTestHelper() {
    }

    public static Supplier<List<Job>> nullJobs = () -> null;
    public static Supplier<Stream<Job>> nullJobsStream = () -> null;
    public static Supplier<List<Job>> noJobs = () -> EMPTY_LIST;
    public static Supplier<Stream<Job>> noJobsStream = Stream::empty;
    public static Supplier<List<Job>> jobsToDifferentiateAlgos = () -> {
        Job job1 = mock(Job.class);
        when(job1.getWeight()).thenReturn(3L);
        when(job1.getLength()).thenReturn(5L);
        Job job2 = mock(Job.class);
        when(job2.getWeight()).thenReturn(1L);
        when(job2.getLength()).thenReturn(2L);

        return Lists.newArrayList(job1, job2);
    };
    public static Supplier<Stream<Job>> jobsToDifferentiateAlgosStream = () ->
            jobsToDifferentiateAlgos.get().stream();

    public static Supplier<List<Job>> oneJobList = () -> {
        Job job1 = mock(Job.class);
        when(job1.getWeight()).thenReturn(2L);
        when(job1.getLength()).thenReturn(2L);
        return Lists.newArrayList(job1);
    };
    public static Supplier<Stream<Job>> oneJobStream = () -> oneJobList.get().stream();
}
