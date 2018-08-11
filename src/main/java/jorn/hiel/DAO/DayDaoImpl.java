package jorn.hiel.DAO;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jorn.hiel.helpers.Day;
import jorn.hiel.helpers.DbaseConnection;
import jorn.hiel.helpers.Month;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;

public class DayDaoImpl implements DayDao{


    private Connection connection = DbaseConnection.getConnection();
    private Logger logger = Logger.getLogger("dbase");


    Day config[] = new Day[7];



    public boolean writeDay(Day day) {

        String SQL = "update werkuren set  uren = ?, extraUren=?, Detail=? where datum=?";
        int counter=0;

        try (PreparedStatement ps= connection.prepareStatement(SQL)) {


            ps.setTime(1, Time.valueOf(day.getTijd()));
            ps.setTime(2, Time.valueOf(day.getExtras()));
            ps.setInt(3, day.getDetail());
            ps.setDate(4 , Date.valueOf(day.getDatum()));
            counter=ps.executeUpdate();

            System.out.println(day.getDetail());

        }
             catch(SQLException f) {
                f.printStackTrace();
            }

            if(counter==0) {


                SQL = "insert into werkuren(datum,uren,extraUren,detail) values (?,?,?,?)";
                try (PreparedStatement ps = connection.prepareStatement(SQL)) {

                    ps.setDate(1, Date.valueOf(day.getDatum()));
                    ps.setTime(2, Time.valueOf(day.getTijd()));
                    ps.setTime(3, Time.valueOf(day.getExtras()));
                    ps.setInt(4, day.getDetail());
                    counter = ps.executeUpdate();
                } catch (SQLException e) {
                    logger.error("SqlExeption @ writeDay");
                    e.printStackTrace();
                    throw (new IllegalArgumentException("Sql statement klopt niet"));

                }
            }



        return counter>0;
    }




    public void writeDay(Month month) {



        String SQL = "insert into werkuren(datum,uren,extraUren,detail) values (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {

            for (Day day : month.getFullMonth().values()) {
               writeDay(day);
            }


        } catch (SQLException e) {
            logger.error("SqlExeption @ writeDay");
            e.printStackTrace();
            throw (new IllegalArgumentException("Sql statement klopt niet"));

        }

    }


    private boolean doesTableExist(String name){

        try {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null,null,name,null);
            while (rs.next()){
                return name.equals(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public boolean writeConfig(Day day){

        String SQL = "update configdays set  uren = ? where dag=?";
        int counter = 0;

        try (PreparedStatement ps= connection.prepareStatement(SQL)) {


            //ps.setString(1,String.valueOf((day.getDatum().getDayOfWeek())));
            //ps.setTime(2,Time.valueOf(day.getTijd()));

            ps.setTime(1,Time.valueOf(day.getTijd()));
            ps.setString(2,String.valueOf((day.getDatum().getDayOfWeek())));

            counter += ps.executeUpdate();

            if (counter==0){
                SQL="insert into configdays(dag,uren) values (?,?)";
                try (PreparedStatement ps2= connection.prepareStatement(SQL)) {
                    ps2.setString(1, String.valueOf((day.getDatum().getDayOfWeek())));
                    ps2.setTime(2, Time.valueOf(day.getTijd()));
                    counter += ps2.executeUpdate();
                }
                catch(SQLException f) {
                    f.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return counter>0;
    }

    public Day[] readConfig(){


        String SQL="select * from configdays";
        Day sortedDays[]=new Day[7];

        int counter=0;

        try(PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                LocalTime zero = LocalTime.of(0,0);
                LocalDate day= LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf(rs.getString(1))));

                config[counter]=new Day(day,rs.getTime(2).toLocalTime(),zero,0);

                counter++;

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }



        sortedDays[0]=config[returnLocation("monday")];
        sortedDays[1]=config[returnLocation("tuesday")];
        sortedDays[2]=config[returnLocation("wednesday")];
        sortedDays[3]=config[returnLocation("thursday")];
       sortedDays[4]=config[returnLocation("friday")];
       sortedDays[5]=new Day(LocalDate.now(),LocalTime.now(),LocalTime.now(),1);
        sortedDays[6]=new Day(LocalDate.now(),LocalTime.now(),LocalTime.now(),1);


       config=sortedDays;


        return config;

    }

    private int returnLocation(String string){

        for(int i=0;i<config.length;i++)
        {
         if (config[i].getDatum().getDayOfWeek().toString().toLowerCase().equals(string.toLowerCase()))
            {return i;
        }
        }


        return 0;
    }


}
