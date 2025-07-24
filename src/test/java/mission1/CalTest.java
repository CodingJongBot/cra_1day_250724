package mission1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CalTest {


    @Test
    void getSum() {
//        Cal cal = mock(Cal.class);
        Cal cal = new Cal();
        int expected =7;

        int ret = cal.getSum(3,4);

        assertEquals(expected,ret);
    }
}