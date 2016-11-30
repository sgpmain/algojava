package my.algo.invernum;

import my.algo.invernum.InverNum.Algo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static my.algo.invernum.InverNumTestHelper.*;
import static my.algo.invernum.InverNumTestHelper.ONE_INV_PREF_LIST;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class InverNumStreamTest {

    private final Algo algo;
    private final Supplier<Stream<Preference>> prefsSupplier;
    private final long expectedInvCount;

    public InverNumStreamTest(Algo algo, Supplier<Stream<Preference>> prefsSupplier, long expectedInvCount) {
        this.algo = algo;
        this.prefsSupplier = prefsSupplier;
        this.expectedInvCount = expectedInvCount;
    }


    @Parameterized.Parameters(name = "{index}: {0}}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {InverNum.Algo.INVERNUM_DANDC, NO_PREFS_STREAM, 0},
                {InverNum.Algo.INVERNUM_DANDC, NO_INV_PREF_STREAM, 0},
                {InverNum.Algo.INVERNUM_DANDC, ONE_INV_PREF_STREAM, 1}
        });
    }

    @Test
    public void givenPrefsListComputeInversionCount() throws Exception {
        // when
        final long inversionCount = algo.compute(prefsSupplier.get());
        // then
        assertEquals(expectedInvCount, inversionCount);
    }


}