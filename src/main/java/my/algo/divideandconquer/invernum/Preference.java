package my.algo.divideandconquer.invernum;

public class Preference {
    final private long value;

    public Preference(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Preference{" +
                "value=" + value +
                '}';
    }
}
