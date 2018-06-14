import jorn.hiel.DAO.DayDaoImpl;
import jorn.hiel.helpers.Day;
import jorn.hiel.helpers.Month;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class DbaseWrite {

    private Month month;
    private Day dayOne;
    private Day dayTwo;
    private LocalDate date1;
    private LocalDate date2;
    private LocalTime time;
    private LocalTime extras;
    private int detail;


    @Before
    public void setUp()
    {
        date1 = LocalDate.of(2018,6,10);
        time = LocalTime.of(8,00);
        extras = LocalTime.of(00,45);
        detail = 1;
        dayOne = new Day(date1, time, extras, detail);
        date2 = LocalDate.of(2018,6,11);
        dayTwo = new Day(date2, time, extras, detail);
        month = new Month(6,2018);
        month.addDag(dayOne);
        month.addDag(dayTwo);

    }

    @Test
    public void base()
    {
        assertTrue(month.getFullMonth().size()==2);
    }

    @Test
    public void baseDetails()
    {
        assertEquals(date1.getDayOfMonth(),10);
        assertEquals(date2.getDayOfMonth(),11);
        assertEquals(month.getFullMonth().get(10), dayOne);
        assertEquals(month.getFullMonth().get(11), dayTwo);
    }

   @Test
    public void writeToDB()
   {
       DayDaoImpl dayDaoImpl = new DayDaoImpl();
       assertTrue(dayDaoImpl.writeDay(month));
   }

}
