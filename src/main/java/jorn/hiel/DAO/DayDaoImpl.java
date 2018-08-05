package jorn.hiel.DAO;

import jorn.hiel.helpers.Day;
import jorn.hiel.helpers.DbaseConnection;
import jorn.hiel.helpers.Month;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DayDaoImpl implements DayDao{


    private Connection connection = DbaseConnection.getConnection();
    private Logger logger = Logger.getLogger("dbase");


    @Override
    public boolean writeDay(Month month) {

        int counter = 0;

        String SQL = "insert into werkuren(datum,uren,extraUren,detail) values (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {

            for (Day day : month.getFullMonth().values()) {
                ps.setDate(1 , Date.valueOf(day.getDatum()));
                ps.setTime(2, Time.valueOf(day.getTijd()));
                ps.setTime(3, Time.valueOf(day.getExtras()));
                ps.setInt(4, day.getDetail());

                counter+=ps.executeUpdate();
            }


        } catch (SQLException e) {
            logger.error("SqlExeption @ writeDay");
            e.printStackTrace();
            throw (new IllegalArgumentException("Sql statement klopt niet"));

        }

        return counter>0;

        //if(counter>0){return true;} else {return false;}
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
        Day config[] = new Day[7];
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

        return config;

    }


}
