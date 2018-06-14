package jorn.hiel.DAO;

        import jorn.hiel.helpers.Day;
        import jorn.hiel.helpers.DbaseConnection;
        import jorn.hiel.helpers.Month;
        import org.apache.log4j.Logger;

        import java.sql.*;


public class MonthDaoImpl implements MonthDao{



    @Override
    public Month getFullMonth(int month, int year) {

        //select uren,extraUren from werkuren where month(datum)=6 and year(datum)=2018
        return null;
    }



/*

    select cast(sum(uren) as time) from werkuren;   <<opvragen totale uren
    select cast(sum(uren) as time) from werkuren where month(datum)=6;   filter op maand
    select cast(sum(uren) as time) from werkuren where month(datum)=6 and year(datum)=2018; filter year
     */

}
