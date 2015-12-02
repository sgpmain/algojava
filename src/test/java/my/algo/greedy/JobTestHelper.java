package my.algo.greedy;

import com.google.common.collect.Lists;
import java.util.Collections;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class JobTestHelper {
    private JobTestHelper() {
    }

    public static final Supplier<List<Job>> NULL_JOBS = () -> null;
    public static final Supplier<Stream<Job>> NULL_JOBS_STREAM = () -> null;
    public static final Supplier<List<Job>> NO_JOBS = Collections::emptyList;
    public static final Supplier<Stream<Job>> NO_JOBS_STREAM = Stream::empty;
    public static final Supplier<List<Job>> JOBS_TO_DIFFERENTIATE_ALGOS = () -> {
        Job job1 = mock(Job.class);
        when(job1.getWeight()).thenReturn(3L);
        when(job1.getLength()).thenReturn(5L);
        Job job2 = mock(Job.class);
        when(job2.getWeight()).thenReturn(1L);
        when(job2.getLength()).thenReturn(2L);

        return Lists.newArrayList(job1, job2);
    };
    public static final Supplier<Stream<Job>> JOBS_TO_DIFFERENTIATE_ALGOS_STREAM = () ->
            JOBS_TO_DIFFERENTIATE_ALGOS.get().stream();

    public static final Supplier<List<Job>> ONE_JOB_LIST = () -> {
        Job job1 = mock(Job.class);
        when(job1.getWeight()).thenReturn(2L);
        when(job1.getLength()).thenReturn(2L);
        return Lists.newArrayList(job1);
    };
    public static final Supplier<Stream<Job>> ONE_JOB_STREAM = () -> ONE_JOB_LIST.get().stream();
}
