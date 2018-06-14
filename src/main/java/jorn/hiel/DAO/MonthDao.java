package jorn.hiel.DAO;

import jorn.hiel.helpers.Month;

public interface MonthDao {

    Month getFullMonth(int month, int year);
}
