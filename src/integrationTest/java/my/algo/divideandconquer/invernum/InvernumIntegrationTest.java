package my.algo.divideandconquer.invernum;

import my.algo.base.DataUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.Boolean.FALSE;

@RunWith(Parameterized.class)
public class InvernumIntegrationTest {
    private static final Function<String, Preference> STR_TO_PREFERENCE_FUNCTION =
            s -> new Preference(Long.valueOf(s));


    private final InverNum.Algo algo;
    private final String fName;
    private final long expectedInvCount;

    public InvernumIntegrationTest(InverNum.Algo algo, String fName, long expectedInvCount) {
        this.algo = algo;
        this.fName = fName;
        this.expectedInvCount = expectedInvCount;
    }


    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> testCases1() {
        return Arrays.asList(new Object[][]{
                {InverNum.Algo.INVERNUM_DANDC, "data/IntegerArray1.txt", 2},
                {InverNum.Algo.INVERNUM_DANDC, "data/IntegerArray2.txt", 56},
                {InverNum.Algo.INVERNUM_DANDC, "data/IntegerArray3.txt", 3},
                {InverNum.Algo.INVERNUM_DANDC, "data/IntegerArray.txt", 2407905288L}
        });
    }

    @Test
    public void testInvernumStreamCase() throws Exception {
        // given
        Boolean headLine = FALSE;
        Stream<Preference> prefs = DataUtils.readToStream(fName, STR_TO_PREFERENCE_FUNCTION, headLine);
        // when
        final long invCount = algo.compute(prefs);
        // then
        Assert.assertEquals(expectedInvCount, invCount);
    }

    @Test
    public void testInvernumListCase() throws Exception {
        // given
        Boolean headLine = FALSE;
        List<Preference> prefs = DataUtils.readToList(fName, STR_TO_PREFERENCE_FUNCTION, headLine);
        // when
        final long invCount = algo.compute(prefs);
        // then
        Assert.assertEquals(expectedInvCount, invCount);
    }

    @Test
    public void testInvernumArrayCase() throws Exception {
        // given
        Boolean headLine = FALSE;
        Preference[] prefsArray =
                DataUtils.readToArray(fName, STR_TO_PREFERENCE_FUNCTION, headLine);
        // when
        final long invCount = algo.compute(prefsArray);
        // then
        Assert.assertEquals(expectedInvCount, invCount);
    }

}