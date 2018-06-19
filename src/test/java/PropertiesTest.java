import static org.junit.Assert.*;

import jorn.hiel.WerkUren;
import org.junit.Before;
import org.junit.Test;

public class PropertiesTest {

    private String[] hours;
    private WerkUren werkUren = new WerkUren();

    @Before
    public void Setup(){
        hours=null;
    }

    @Test
    public void hourReading()
    {
     assertTrue(hours==null);

     hours=werkUren.getWorkhours();
     assertTrue(hours.length==5);
    }

    @Test
    public void hasAllValues()
    {
        assertTrue(hours==null);
        hours=werkUren.getWorkhours();
        for (String string:hours
             ) {
            assertTrue(string.length()>0);

        }
    }
}
