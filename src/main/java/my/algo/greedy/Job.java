package my.algo.greedy;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Job {

    private final long weight;
    private final long length;

    public Job(long weight, long length) {
        this.weight = weight;
        this.length = length;
    }

    public Job(String[] jobData) {
        this(Long.parseLong(jobData[0]), Long.parseLong(jobData[1]));
    }

    public Job(long[] jobData) {
        this(jobData[0], jobData[1]);

    }

    public long getWeight() {
        return weight;
    }

    public long getLength() {
        return length;
    }

    @Override
    public String toString() {
        return toStringHelper(this).
                add("w:", weight).
                add("l:", length).toString();
    }
}
