package my.algo.divideandconquer.invernum;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InverNumTestHelper {
    private InverNumTestHelper() {
    }

    public static <T> long computeInversionCount(Supplier<T> prefsSupplier, InverNum.Algo algo) {
        long inversionCount;
        if (instanceOfStream(prefsSupplier)) inversionCount = algo.compute((Stream<Preference>) prefsSupplier.get());
        else if (instanceOfList(prefsSupplier)) inversionCount = algo.compute((List<Preference>) prefsSupplier.get());
        else if (instanceOfArray(prefsSupplier)) inversionCount = algo.compute((Preference[]) prefsSupplier.get());
        else throw new IllegalArgumentException();
        return inversionCount;
    }

    private static boolean instanceOfArray(Supplier<?> prefsSupplier) {
        return prefsSupplier.get() instanceof Preference[];
    }

    private static boolean instanceOfList(Supplier<?> prefsSupplier) {
        return prefsSupplier.get() instanceof List;
    }

    private static boolean instanceOfStream(Supplier<?> prefsSupplier) {
        return prefsSupplier.get() instanceof Stream;
    }

    enum TestCase {
        NO_PREFS_LIST(Collections::emptyList),
        NO_PREFS_STREAM(Stream::empty),
        NO_PREFS_ARRAY(() -> new Preference[]{}),
        NO_INV_PREF_LIST(() -> {
            Preference pref1 = mock(Preference.class);
            when(pref1.getValue()).thenReturn(1L);
            return Lists.newArrayList(pref1);
        }),
        NO_INV_PREF_STREAM(() -> ((List<?>) NO_INV_PREF_LIST.supplier.get()).stream()),
        NO_INV_PREF_ARRAY(() -> ((List<?>) NO_INV_PREF_LIST.supplier.get()).toArray(new Preference[0])),

        ONE_INV_PREF_LIST(() -> {
            Preference pref1 = mock(Preference.class);
            when(pref1.getValue()).thenReturn(1L);
            Preference pref2 = mock(Preference.class);
            when(pref2.getValue()).thenReturn(2L);
            return Lists.newArrayList(pref2, pref1);
        }),
        ONE_INV_PREF_STREAM(() -> ((List<?>) ONE_INV_PREF_LIST.supplier.get()).stream()),
        ONE_INV_PREF_ARRAY(() -> ((List<?>) ONE_INV_PREF_LIST.supplier.get()).toArray(new Preference[0]));

        public Supplier<?> supplier;

        TestCase(Supplier<?> supplier) {
            this.supplier = supplier;
        }
    }
}
