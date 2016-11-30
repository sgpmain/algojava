package my.algo.invernum;

import my.algo.invernum.InverNum.Algo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static my.algo.invernum.InverNumTestHelper.NULL_PREFS;
import static my.algo.invernum.InverNumTestHelper.NULL_PREFS_STREAM;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class InverNumStreamForNullTest {

    private final Algo algo;
    private final Supplier<Stream<Preference>> prefsSupplier;

    public InverNumStreamForNullTest(Algo algo, Supplier<Stream<Preference>> prefsSupplier) {
        this.algo = algo;
        this.prefsSupplier = prefsSupplier;
    }


    @Parameterized.Parameters(name = "{index}: {0}}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {Algo.INVERNUM_DANDC, NULL_PREFS_STREAM}
        });
    }

    @Test(expected = NullPointerException.class)
    public void givenNullPrefsStreamShouldThrowException() throws Exception {
        // when
        algo.compute(prefsSupplier.get());
        // then
        fail("Not thrown NullPointerException");
    }


}