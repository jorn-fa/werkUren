package jorn.hiel.helpers;

import jorn.hiel.WerkUren;
import org.apache.log4j.Logger;

import java.time.LocalDate;

public class MonthCounter extends WerkUren{

    private  double totalHours;
    private Logger logger = Logger.getLogger("werkUren");
    private String[] days;
    private Integer[] daysAsNumbers = new Integer[5];


    public MonthCounter(){
        days=getDataFromProperties();

    }

    public  double getTotalHours(LocalDate date) {
        //todo  datums ingelezen als integer, loop maken om per dag te tellen en dan som te maken


        return totalHours;
    }

    private String[] getDataFromProperties(){
        return super.getWorkhours();

    }

    public Integer convert(String input){
        int counter = 0;
        if(input.contains(":")){
            int temp = input.indexOf(":");

            if(input.startsWith("0")) {
                counter += (Double.valueOf(input.substring(1, temp)) * 60);
            }
            else {
                counter += (Double.valueOf(input.substring(0, temp)) * 60);
            }

            counter+=Double.valueOf(input.substring(temp+1));
            return counter;
        }
        else
            {
                logger.debug("Wrong input on Convert String to Number ");
            }
        return null;
    }

    public void convertAlldays()
    {
        for(int counter=0;counter<days.length;counter++)
        {
            daysAsNumbers[counter]=convert(days[counter]);
        }
    }

    public Integer[] getDaysAsNumbers() {
        convertAlldays();
        return daysAsNumbers;
    }
}
