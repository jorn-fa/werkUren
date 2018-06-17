package jorn.hiel.DAO;

        import jorn.hiel.helpers.Day;
        import jorn.hiel.helpers.DbaseConnection;
        import jorn.hiel.helpers.Month;
        import org.apache.log4j.Logger;

        import java.sql.*;
        import java.time.LocalDate;
        import java.time.LocalTime;


public class MonthDaoImpl implements MonthDao{

    private Connection connection = DbaseConnection.getConnection();
    private Logger logger = Logger.getLogger("dbase");

    @Override
    public Month getFullMonth(int month, int year) {


        Month resultMonth = new Month(month,year);


        String SQL = "select * from werkuren where month(datum)=? and year(datum)=?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {


                ps.setInt(1 , month);
                ps.setInt(2, year);


                ResultSet resultSet= ps.executeQuery();

                while (resultSet.next())
                {
                    LocalDate date = resultSet.getDate(2).toLocalDate();
                    LocalTime time = resultSet.getTime(3).toLocalTime();
                    LocalTime extras = resultSet.getTime(4).toLocalTime();
                    int details = resultSet.getInt(5);

                    resultMonth.addDag(new Day(date,time,extras,details));
                }

        } catch (SQLException e) {
            logger.error("SqlExeption @ writeDay");
            e.printStackTrace();
            throw (new IllegalArgumentException("Sql statement klopt niet"));

        }


        //select uren,extraUren from werkuren where month(datum)=6 and year(datum)=2018
        return resultMonth;
    }



/*

    select cast(sum(uren) as time) from werkuren;   <<opvragen totale uren
    select cast(sum(uren) as time) from werkuren where month(datum)=6;   filter op maand
    select cast(sum(uren) as time) from werkuren where month(datum)=6 and year(datum)=2018; filter year
     */

}
