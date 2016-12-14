package my.algo.divideandconquer.invernum;

import my.algo.divideandconquer.invernum.InverNum.Algo;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.fail;

public class InverNumNullTest {

    private final Algo algo = Algo.INVERNUM_DANDC;

    @Test(expected = NullPointerException.class)
    public void givenNullPrefsStreamShouldThrowException() throws Exception {
        // when
        Stream<Preference> nullStream = null;
        algo.compute(nullStream);
        // then
        fail("Not thrown NullPointerException");
    }

    @Test(expected = NullPointerException.class)
    public void givenNullPrefsListShouldThrowException() throws Exception {
        // when
        List<Preference> nullList = null;
        algo.compute(nullList);
        // then
        fail("Not thrown NullPointerException");
    }

    @Test(expected = NullPointerException.class)
    public void givenNullPrefsArrayShouldThrowException() throws Exception {
        // when
        Preference[] nullArray = null;
        algo.compute(nullArray);
        // then
        fail("Not thrown NullPointerException");
    }


}