import static org.junit.Assert.*;

import jorn.hiel.helpers.Month;
import jorn.hiel.helpers.MonthCounter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;


public class MonthCounterTest {

    private LocalDate dayOne;
    private MonthCounter monthCounter;


    @Before
    public void setup(){
        dayOne=LocalDate.of(2018,6,1);
        monthCounter = new MonthCounter();
    }

    @Test
    public void baseTest(){
        assertEquals(dayOne,LocalDate.of(2018,6,1));
    }

    @Test
    public void getTotal()
    {
        assertTrue(monthCounter.getTotalHours(dayOne)==163d);
    }

    @Test
    public void testConvert(){
        MonthCounter monthCounter =  new MonthCounter();
        assertTrue(monthCounter.convert("08:00")==480d);
        assertTrue(monthCounter.convert("00:30")==30d);
    }

    @Test
    public void testWeek(){
        monthCounter = new MonthCounter();
        assertTrue(monthCounter.getDaysAsNumbers()[0]==480);
        assertTrue(monthCounter.getDaysAsNumbers()[1]==480);
        assertTrue(monthCounter.getDaysAsNumbers()[2]==480);
        assertTrue(monthCounter.getDaysAsNumbers()[3]==480);
        assertTrue(monthCounter.getDaysAsNumbers()[4]==420);
    }
}
