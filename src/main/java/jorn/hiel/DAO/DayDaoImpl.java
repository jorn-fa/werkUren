package jorn.hiel.DAO;

import jorn.hiel.helpers.Day;
import jorn.hiel.helpers.DbaseConnection;
import jorn.hiel.helpers.Month;
import org.apache.log4j.Logger;

import java.sql.*;

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


        if(counter>0){return true;} else {return false;}
    }




}
