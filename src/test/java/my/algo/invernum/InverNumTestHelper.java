package my.algo.invernum;

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

    public static final Supplier<List<Preference>> NULL_PREFS = () -> null;
    public static final Supplier<Stream<Preference>> NULL_PREFS_STREAM = () -> null;
    public static final Supplier<List<Preference>> NO_PREFS = Collections::emptyList;
    public static final Supplier<Stream<Preference>> NO_PREFS_STREAM = Stream::empty;

    public static final Supplier<List<Preference>> NO_INV_PREF_LIST = () -> {
        Preference pref1 = mock(Preference.class);
        when(pref1.getValue()).thenReturn(1L);
        return Lists.newArrayList(pref1);
    };
    public static final Supplier<Stream<Preference>> NO_INV_PREF_STREAM = () -> NO_INV_PREF_LIST.get().stream();

    public static final Supplier<List<Preference>> ONE_INV_PREF_LIST = () -> {
        Preference pref1 = mock(Preference.class);
        when(pref1.getValue()).thenReturn(1L);
        Preference pref2 = mock(Preference.class);
        when(pref2.getValue()).thenReturn(2L);
        return Lists.newArrayList(pref2, pref1);
    };
    public static final Supplier<Stream<Preference>> ONE_INV_PREF_STREAM = () -> ONE_INV_PREF_LIST.get().stream();


}
