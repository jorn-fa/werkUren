package jorn.hiel.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import jorn.hiel.WerkUren;
import jorn.hiel.helpers.Day;
import jorn.hiel.helpers.Month;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainScreen implements Initializable {

    public MainScreen(){}
    private WerkUren werkUren;
    private int monthnumber, yearnumber;
    private Month month;

    private LocalDate firstDayOfMonth;


    private ObservableList<Month> monthData ;

    private TableView<Month> monthTable;
    private TableColumn<Day,String> Monthday;

    @FXML
    private Label monthLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private Label day1,day2,day3,day4,day5,day6,day7,day8,day9,day10,day11,day12,day13,day14,day15,day16,
            day17,day18,day19,day20,day21,day22,day23,day24,day25,day26,day27,
    day28,day29,day30,day31;

    @FXML
    List<Label> labels = new ArrayList<Label>();




    @FXML
    private TableView<Month> monthFirstHalfTable;
    private TableColumn<Month,String> monthFirstHalfDate;
    private TableColumn<Month,String> monthFirstHalfHour;

    @FXML
    private TableView monthSecondHalfTable;

    public ObservableList<Month> getMonthData() {
        return FXCollections.observableArrayList(monthData);
    }


    @FXML
    public void nextMonth(){
        firstDayOfMonth=firstDayOfMonth.plusMonths(1);
        setDateLabels();
    }

    @FXML
    public void previousMonth(){
        firstDayOfMonth=firstDayOfMonth.minusMonths(1);
        setDateLabels();
    }

    @FXML
    public void dateToCurrentMonth()
    {
        firstDayOfMonth=LocalDate.of(werkUren.getCurrentYear(),werkUren.getCurrentMonth(),1);
        setDateLabels();
    }

    @FXML
    public void verifyVisibility()
    {

        day28.setVisible(firstDayOfMonth.lengthOfMonth()>=28);
        day29.setVisible(firstDayOfMonth.lengthOfMonth()>=29);
        day30.setVisible(firstDayOfMonth.lengthOfMonth()>=30);
        day31.setVisible(firstDayOfMonth.lengthOfMonth()>=31);

    }

    @FXML
    public void stopApp()
    {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labels.addAll(Arrays.asList(day1,day2,day3,day4,day5,day6,day7,day8,day9,day10,day11,day12,day13,day14,day15,day16,
                day17,day18,day19,day20,day21,day22,day23,day24,day25,day26,day27,
                day28,day29,day30,day31));




    }

    public void setDateLabels(){
        LocalDate teller;
        monthLabel.setText(String.valueOf(firstDayOfMonth.getMonth()));
        yearLabel.setText(String.valueOf(firstDayOfMonth.getYear()));
        verifyVisibility();


        //reset to the 1st of the month
        teller=LocalDate.of(firstDayOfMonth.getYear(),firstDayOfMonth.getMonth(),1);

        for (Label label:labels) {
            String splitter="\t\t";
            if(teller.getDayOfWeek().toString().equals("WEDNESDAY")){ splitter="\t";}
            label.setText(String.valueOf(teller.getDayOfWeek()).toLowerCase() + splitter+ "-\t"  + teller.getDayOfMonth()  );
            teller=teller.plusDays(1);
        }



    }

    public void setWerkuren(WerkUren werkUren){
        this.werkUren=werkUren;
        dateToCurrentMonth();

        setDateLabels();

        monthnumber=firstDayOfMonth.getMonthValue();
        yearnumber=firstDayOfMonth.getYear();



        fillMonth();




        //monthTable.setItems(FXCollections.observableArrayList(month));
        //System.out.println(monthTable);


    }

    private void fillMonth(){
        month=werkUren.getCurrentMonthList(monthnumber,yearnumber);


    }


}
