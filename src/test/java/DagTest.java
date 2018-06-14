import jorn.hiel.helpers.Day;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class DagTest {

    private Day dag;
    private LocalDate date;
    private LocalTime time;
    private LocalTime extras;
    private int detail;


    @Before
    public void setup(){

        date = LocalDate.of(2018,6,10);
        time = LocalTime.of(8,00);
        extras = LocalTime.of(00,45);
        detail = 1;
        dag = new Day(date, time, extras, detail);
    }


    @Test
    public void base(){
        assertEquals(dag.getDatum().toString(),"2018-06-10");
        assertEquals(dag.getTijd().toString(),"08:00");
        assertEquals(dag.getExtras().toString(),"00:45");
        assertEquals(dag.getDetail(),1);

    }

    @Test
    public void otherDetail(){
        detail=1;
        dag = new Day(date, time, extras, detail);
        assertEquals(dag.getDetail(),1);
    }
}
