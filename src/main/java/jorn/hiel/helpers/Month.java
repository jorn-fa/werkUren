package jorn.hiel.helpers;

import java.time.LocalTime;
import java.util.HashMap;
import org.apache.log4j.Logger;

public final class Month {

    private int maandNumber;
    private int jaar;
    private Logger logger = Logger.getLogger("werkUren");

    private HashMap<Integer,Day> maand = new HashMap<>();


    public Month(int numberOfTheMonth, int year) {

        if ((numberOfTheMonth < 0)||(year < 0)) {
            logger.debug("Attempt to create month with negative details");
            throw new IllegalArgumentException("Negative numbers : Cannot create");
        }

        this.maandNumber = numberOfTheMonth;
        this.jaar = year;
    }

    public int getMaandnumber() {
        return maandNumber;
    }

    public int getJaar() {
        return jaar;
    }
    public HashMap<Integer,Day> getFullMonth(){
        return maand;
    }


    public boolean addDag(Day dag)
    {
        int day = dag.getDatum().getDayOfMonth();


        if(maand.containsKey(day)){
            return false;
        }

        maand.put(day,dag);


        return true;
    }



}
