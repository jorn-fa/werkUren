package jorn.hiel.helpers;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class Day {

    private LocalTime time;
    private LocalDate date;
    private LocalTime extras;
    private int detail;


    public Day(LocalDate date, LocalTime time, LocalTime extras, int detail) {
        this.time = time;
        this.date = date;
        this.extras = extras;
        this.detail = detail;
    }



    public LocalDate getDatum() {
        return date;
    }

    public LocalTime getTijd() {
        return time;
    }

    public LocalTime getExtras() {
        return extras;
    }

    public int getDetail() {
        return detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        if (detail != day.detail) return false;
        if (time != null ? !time.equals(day.time) : day.time != null) return false;
        if (date != null ? !date.equals(day.date) : day.date != null) return false;
        return extras != null ? extras.equals(day.extras) : day.extras == null;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (extras != null ? extras.hashCode() : 0);
        result = 31 * result + detail;
        return result;
    }
}
