package my.algo.invernum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static my.algo.invernum.InverNumTestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class InverNumTest {

    private final InverNum.Algo algo;
    private final Supplier<List<Preference>> prefsSupplier;
    private final int expectedInvCount;

    public InverNumTest(InverNum.Algo algo, Supplier<List<Preference>> prefsSupplier, int expectedInvCount) {
        this.algo = algo;
        this.prefsSupplier = prefsSupplier;
        this.expectedInvCount = expectedInvCount;
    }


    @Parameterized.Parameters(name = "{index}: {0}+{2}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {InverNum.Algo.INVERNUM_DANDC, NO_PREFS, 0},
                {InverNum.Algo.INVERNUM_DANDC, NO_INV_PREF_LIST, 0},
                {InverNum.Algo.INVERNUM_DANDC, ONE_INV_PREF_LIST, 1}
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