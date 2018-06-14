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
}
