package my.algo.invernum;

import java.util.List;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

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
            return compute(prefs.stream());
        }

        public long compute(Stream<Preference> prefs) {
            checkNotNull(prefs);
            //implement algo

            return 0;
        }

    }
}