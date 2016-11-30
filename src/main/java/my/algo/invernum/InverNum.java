package my.algo.invernum;

import com.google.common.primitives.Longs;

import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/*
File contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some order,
with no integer repeated.
Your task is to compute the number of inversions in the file given,
where the ith row of the file indicates the ith entry of an array.
 */
public class InverNum {

    public enum Algo {

        INVERNUM_DANDC("InverNum DandC basic");

        private String name;

        Algo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public long compute(List<Preference> prefs) {
            if(prefs.size() == 0) return 0L;
            return compute(prefs.stream());
        }

        public long compute(Stream<Preference> prefs) {
            checkNotNull(prefs);
            //implement algo
            long[] prefsArr = prefs.mapToLong(Preference::getValue).toArray();
            if(prefsArr.length == 0) return 0L;
            return computeInRange(0, prefsArr.length - 1, prefsArr, new long[prefsArr.length]);
        }

        private long computeInRange(int from, int to, long[] prefs, long[] prefsAux) {
            if (from == to) return 0;
            final int pivot = pivot(from, to);
            final long lInv = computeInRange(from, pivot, prefs, prefsAux);
            final long rInv = computeInRange(pivot + 1, to, prefs, prefsAux);
            final long splitInv = countAndMerge(from, to, prefs, prefsAux);
            return lInv + rInv + splitInv;
        }


        private long countAndMerge(int from, int to, long[] prefs, long[] prefsAux) {
            final int pivot = pivot(from, to);
            int rI = pivot + 1;
            int lI = from;
            long invCount = 0L;
            for (int i = from; i <= to; i++) {
                if (lI > pivot || (rI <= to && prefs[lI] > prefs[rI])) {
                    invCount += rI - Math.max(lI, i);
                    prefsAux[i] = prefs[rI++];
                } else {
                    prefsAux[i] = prefs[lI++];
                }
            }
            System.arraycopy(prefsAux, from, prefs, from, to - from + 1);
            return invCount;
        }

        private int pivot(int from, int to) {
            return (to + from) >>> 1;
        }
    }
}