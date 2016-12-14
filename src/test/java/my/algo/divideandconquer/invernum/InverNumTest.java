package my.algo.divideandconquer.invernum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import static my.algo.divideandconquer.invernum.InverNumTestHelper.computeInversionCount;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class InverNumTest {

    private final InverNum.Algo algo;
    private final Supplier<?> prefsSupplier;
    private final int expectedInvCount;

    public InverNumTest(InverNum.Algo algo, InverNumTestHelper.TestCase prefsSupplier, int expectedInvCount) {
        this.algo = algo;
        this.prefsSupplier = prefsSupplier.supplier;
        this.expectedInvCount = expectedInvCount;
    }


    @Parameterized.Parameters(name = "{index}: {0} case:{1}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                //array
                {InverNum.Algo.INVERNUM_DANDC, InverNumTestHelper.TestCase.NO_PREFS_ARRAY, 0},
                {InverNum.Algo.INVERNUM_DANDC, InverNumTestHelper.TestCase.NO_INV_PREF_ARRAY, 0},
                {InverNum.Algo.INVERNUM_DANDC, InverNumTestHelper.TestCase.ONE_INV_PREF_ARRAY, 1},
                // list
                {InverNum.Algo.INVERNUM_DANDC, InverNumTestHelper.TestCase.NO_PREFS_LIST, 0},
                {InverNum.Algo.INVERNUM_DANDC, InverNumTestHelper.TestCase.NO_INV_PREF_LIST, 0},
                {InverNum.Algo.INVERNUM_DANDC, InverNumTestHelper.TestCase.ONE_INV_PREF_LIST, 1},
                // stream
                {InverNum.Algo.INVERNUM_DANDC, InverNumTestHelper.TestCase.NO_PREFS_STREAM, 0},
                {InverNum.Algo.INVERNUM_DANDC, InverNumTestHelper.TestCase.NO_INV_PREF_STREAM, 0},
                {InverNum.Algo.INVERNUM_DANDC, InverNumTestHelper.TestCase.ONE_INV_PREF_STREAM, 1}

        });
    }

    @Test
    public void givenPrefsComputeInversionCount() throws Exception {

        // when
        final long inversionCount;
        inversionCount = computeInversionCount(prefsSupplier, algo);
        // then
        assertEquals(expectedInvCount, inversionCount);
    }
 }