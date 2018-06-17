package jorn.hiel.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TextField
            //uren
            day1Uren,day2Uren,day3Uren,day4Uren,day5Uren,day6Uren,day7Uren,day8Uren,day9Uren,day10Uren,day11Uren,day12Uren,day13Uren,day14Uren,day15Uren,day16Uren,
            day17Uren,day18Uren,day19Uren,day20Uren,day21Uren,day22Uren,day23Uren,day24Uren,day25Uren,day26Uren,day27Uren,
            day28Uren,day29Uren,day30Uren,day31Uren,
            //extra
            day1Extra,day2Extra,day3Extra,day4Extra,day5Extra,day6Extra,day7Extra,day8Extra,day9Extra,day10Extra,day11Extra,day12Extra,day13Extra,day14Extra,day15Extra,day16Extra,
                    day17Extra,day18Extra,day19Extra,day20Extra,day21Extra,day22Extra,day23Extra,day24Extra,day25Extra,day26Extra,day27Extra,
                    day28Extra,day29Extra,day30Extra,day31Extra,


            //details
            // day1Detail,day2Detail,day3Detail,day4Detail,day5Detail,day6Detail,day7Detail,day8Detail,day9Detail,day10Detail,day11Detail,day12Detail,day13Detail,day14Detail,day15Detail,day16Detail,
            day17Detail,day18Detail,day19Detail,day20Detail,day21Detail,day22Detail,day23Detail,day24Detail,day25Detail,day26Detail,day27Detail,
            day28Detail,day29Detail,day30Detail,day31Detail;



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
        day29.setVisible(firstDayOfMonth.lengthOfMonth()>=29);
        day29Uren.setVisible(firstDayOfMonth.lengthOfMonth()>=29);
        day29Extra.setVisible(firstDayOfMonth.lengthOfMonth()>=29);
        day29Detail.setVisible(firstDayOfMonth.lengthOfMonth()>=29);

        day30.setVisible(firstDayOfMonth.lengthOfMonth()>=30);
        day30Uren.setVisible(firstDayOfMonth.lengthOfMonth()>=30);
        day30Extra.setVisible(firstDayOfMonth.lengthOfMonth()>=30);
        day30Detail.setVisible(firstDayOfMonth.lengthOfMonth()>=30);

        day31.setVisible(firstDayOfMonth.lengthOfMonth()>=31);
        day31Uren.setVisible(firstDayOfMonth.lengthOfMonth()>=31);
        day31Extra.setVisible(firstDayOfMonth.lengthOfMonth()>=31);
        day31Detail.setVisible(firstDayOfMonth.lengthOfMonth()>=31);




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