import jorn.hiel.helpers.Day;
import jorn.hiel.helpers.Month;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;


public class MaandTest {

    private Month maand;

    @Before
    public void setup()
    {
        maand = new Month(6,2018);
    }


    @Test
    public void base()
    {

        assertEquals(maand.getMaandnumber(),6);
        assertEquals(maand.getJaar(),2018);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithWrongData(){
        Month maanda = new Month(-6,2018);
        Month maandb = new Month(6,-2018);

        assertEquals(maanda.getMaandnumber(),null);
        assertEquals(maandb.getMaandnumber(),null);
    }

    @Test
    public void addDayToMonth(){
        //Date datum = Date.valueOf("2018-06-10");
        //Time tijd = Time.valueOf("8:00:00");
        //Time extras = Time.valueOf("00:45:00");
        int detail = 1;

        LocalDate date = LocalDate.of(2018,6,10);
        LocalTime time = LocalTime.of(8,00);
        LocalTime extras = LocalTime.of(00,45);
        detail = 1;
        Day dag = new Day(date, time, extras, detail);

        assertTrue(maand.addDag(dag));
        assertFalse(maand.addDag(dag));
        assertEquals(maand.getMaandnumber(),6);
        assertEquals(maand.getFullMonth().get(10),dag);

    }




}
