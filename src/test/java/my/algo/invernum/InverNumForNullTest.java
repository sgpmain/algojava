package my.algo.invernum;

import my.algo.invernum.InverNum.Algo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static my.algo.invernum.InverNumTestHelper.*;
import static my.algo.invernum.InverNumTestHelper.NULL_PREFS_STREAM;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class InverNumForNullTest {

    private final Algo algo;
    private final Supplier<List<Preference>> prefsSupplier;

    public InverNumForNullTest(Algo algo, Supplier<List<Preference>> prefsSupplier) {
        this.algo = algo;
        this.prefsSupplier = prefsSupplier;
    }


    @Parameterized.Parameters(name = "{index}: {0}}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {Algo.INVERNUM_DANDC, NULL_PREFS}
        });
    }

    @Test(expected = NullPointerException.class)
    public void givenNullPrefsListShouldThrowException() throws Exception {
        // when
        algo.compute(prefsSupplier.get());
        // then
        fail("Not thrown NullPointerException");
    }


}