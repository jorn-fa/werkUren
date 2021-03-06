package jorn.hiel.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jorn.hiel.WerkUren;
import jorn.hiel.helpers.Day;
import jorn.hiel.helpers.Daytype;
import jorn.hiel.helpers.Month;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class MainScreen implements Initializable {

    public MainScreen(){}
    private WerkUren werkUren;
    private int monthnumber, yearnumber;
    private Month month;

    private LocalDate firstDayOfMonth;

    @FXML
    MenuItem setHoursOfWeek;

    @FXML
    private Label differenceWorkHoursLabel;

    @FXML
    private Label workHoursEachWeek;

    @FXML
    private Label workedHours;

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
            day1Detail,day2Detail,day3Detail,day4Detail,day5Detail,day6Detail,day7Detail,day8Detail,day9Detail,day10Detail,day11Detail,day12Detail,day13Detail,day14Detail,day15Detail,day16Detail,
            day17Detail,day18Detail,day19Detail,day20Detail,day21Detail,day22Detail,day23Detail,day24Detail,day25Detail,day26Detail,day27Detail,
            day28Detail,day29Detail,day30Detail,day31Detail;



    @FXML
    private List<Label> labels = new ArrayList<>();
    private List<TextField> urenTextFields = new ArrayList<>();
    private List<TextField> extraTextFields = new ArrayList<>();
    private List<TextField> detailsTextFields = new ArrayList<>();
    private List<TextField> allTextFields = new ArrayList<>();

    @FXML
    private Button addAdayButton,exitButton, nextMonthButton, prevMonthButton, currentMonthButton;







    @FXML
    public void nextMonth(){
        firstDayOfMonth=firstDayOfMonth.plusMonths(1);
        clearTextfields();
        setDateLabels();
        fillMonth();
        calculateHoursToWork();
    }

    @FXML
    public void previousMonth(){
        firstDayOfMonth=firstDayOfMonth.minusMonths(1);
        clearTextfields();
        setDateLabels();
        fillMonth();
        calculateHoursToWork();

    }

    @FXML
    public void dateToCurrentMonth()
    {
        firstDayOfMonth=LocalDate.of(werkUren.getCurrentYear(),werkUren.getCurrentMonth(),1);
        clearTextfields();
        setDateLabels();
        fillMonth();
        calculateHoursToWork();

    }

    @FXML
    private void clearTextfields()
    {
        for (TextField textfield:allTextFields
                ) {textfield.setOpacity(1.0);
            textfield.clear();
        }
    }




    private void verifyVisibility()
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
        urenTextFields.addAll(Arrays.asList(day1Uren,day2Uren,day3Uren,day4Uren,day5Uren,day6Uren,day7Uren,day8Uren,day9Uren,day10Uren,day11Uren,day12Uren,day13Uren,day14Uren,day15Uren,day16Uren,
                day17Uren,day18Uren,day19Uren,day20Uren,day21Uren,day22Uren,day23Uren,day24Uren,day25Uren,day26Uren,day27Uren,
                day28Uren,day29Uren,day30Uren,day31Uren));
        detailsTextFields.addAll(Arrays.asList(day1Detail,day2Detail,day3Detail,day4Detail,day5Detail,day6Detail,day7Detail,day8Detail,day9Detail,day10Detail,day11Detail,day12Detail,day13Detail,day14Detail,day15Detail,day16Detail,
                day17Detail,day18Detail,day19Detail,day20Detail,day21Detail,day22Detail,day23Detail,day24Detail,day25Detail,day26Detail,day27Detail,
                day28Detail,day29Detail,day30Detail,day31Detail));
        extraTextFields.addAll(Arrays.asList(day1Extra,day2Extra,day3Extra,day4Extra,day5Extra,day6Extra,day7Extra,day8Extra,day9Extra,day10Extra,day11Extra,day12Extra,day13Extra,day14Extra,day15Extra,day16Extra,
                day17Extra,day18Extra,day19Extra,day20Extra,day21Extra,day22Extra,day23Extra,day24Extra,day25Extra,day26Extra,day27Extra,
                day28Extra,day29Extra,day30Extra,day31Extra));
        allTextFields.addAll(urenTextFields);
        allTextFields.addAll(detailsTextFields);
        allTextFields.addAll(extraTextFields);


        for (TextField textfield:allTextFields
             ) {textfield.setAlignment(Pos.CENTER); textfield.setEditable(false);

        }

        setHoursOfWeek.setOnAction(e -> showHoursPopup());

        Platform.runLater(new Runnable() {
            public void run() {
                addAdayButton.requestFocus();
            }
        });

        addAdayButton.setOnKeyPressed((event) -> {if (event.getCode() == KeyCode.ENTER){addADay();}});
        exitButton.setOnKeyPressed((event) -> {if (event.getCode() == KeyCode.ENTER){stopApp();}});
        prevMonthButton.setOnKeyPressed((event) -> {if (event.getCode() == KeyCode.ENTER){previousMonth();}});
        nextMonthButton.setOnKeyPressed((event) -> {if (event.getCode() == KeyCode.ENTER){nextMonth();}});
        currentMonthButton.setOnKeyPressed((event) -> {if (event.getCode() == KeyCode.ENTER){dateToCurrentMonth();}});


    }

    private void showHoursPopup()
    {

    Stage stage = new Stage();
        try {


            Parent root = FXMLLoader.load(getClass().getResource("/view/hoursPref.fxml"));
            stage.setScene((new Scene(root)));
            stage.setTitle("View / Edit hours");
            stage.initModality(Modality.APPLICATION_MODAL);


            stage.showAndWait();
            calculateHoursToWork();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addADay(){

        Stage stage = new Stage();
        try {


            Parent root = FXMLLoader.load(getClass().getResource("/view/addDay.fxml"));
            stage.setScene((new Scene(root)));
            stage.setTitle("Add a day");
            stage.initModality(Modality.APPLICATION_MODAL);


            stage.showAndWait();
            calculateHoursToWork();


        } catch (IOException e) {
            e.printStackTrace();
        }

        fillMonth();
        calculateHoursToWork();

    }



    private void setDateLabels(){
        LocalDate teller,reset;
        monthLabel.setText(String.valueOf(firstDayOfMonth.getMonth()));
        yearLabel.setText(String.valueOf(firstDayOfMonth.getYear()));
        verifyVisibility();


        //reset to the 1st of the month
        reset=LocalDate.of(firstDayOfMonth.getYear(),firstDayOfMonth.getMonth(),1);
        teller=reset;

        for (Label label:labels) {
            String splitter="\t\t";
            if(teller.getDayOfWeek().toString().equals("WEDNESDAY")){ splitter="\t";}
            label.setText(String.valueOf(teller.getDayOfWeek()).toLowerCase() + splitter+ "-\t"  + teller.getDayOfMonth()  );
            teller=teller.plusDays(1);
        }

        teller=reset;





        for (TextField textfield: detailsTextFields)
        {
            if(teller.getDayOfWeek().toString().equals("SATURDAY")||teller.getDayOfWeek().toString().equals("SUNDAY")){textfield.setText("weekend");textfield.setOpacity(0.5);
            }
            teller=teller.plusDays(1);
        }
        teller=reset;

        for (TextField textfield: extraTextFields)
        {
            if(teller.getDayOfWeek().toString().equals("SATURDAY")||teller.getDayOfWeek().toString().equals("SUNDAY")){textfield.setText("weekend");textfield.setOpacity(0.5);
            }
            teller=teller.plusDays(1);
        }
        teller=reset;

        for (TextField textfield: urenTextFields)
        {
            if(teller.getDayOfWeek().toString().equals("SATURDAY")||teller.getDayOfWeek().toString().equals("SUNDAY")){textfield.setText("weekend");textfield.setOpacity(0.5);
            }
            teller=teller.plusDays(1);
        }

    }

    private void updateNumbers(){
        monthnumber=firstDayOfMonth.getMonthValue();
        yearnumber=firstDayOfMonth.getYear();
    }

    public void setWerkuren(WerkUren transferUren){
        this.werkUren=transferUren;
        dateToCurrentMonth();
        setDateLabels();
        fillMonth();
        calculateHoursToWork();


    }

    private void fillFields(int counter){
        String urenFieldName="day"+counter+"Uren";
        String extrasFieldName="day"+counter+"Extra";
        String detailFieldName="day"+counter+"Detail";

        for (TextField textfield:urenTextFields
                ) {
            if(textfield.getId().equals(urenFieldName)){
                textfield.setText(month.getFullMonth().get(counter).getTijd().toString());

                }
        }

        for (TextField textfield:extraTextFields
                ) {
            if(textfield.getId().equals(extrasFieldName)){
                textfield.setText(month.getFullMonth().get(counter).getExtras().toString());

            }
        }
        for (TextField textfield:detailsTextFields
                ) {
            if(textfield.getId().equals(detailFieldName)){

                Integer temp=month.getFullMonth().get(counter).getDetail();
                String output="";


                if (temp==Daytype.WERK.ordinal()){output="";}
                if (temp==Daytype.ZIEK.ordinal()){output="Ziek";}
                if (temp==Daytype.VERLOF.ordinal()){output="Verlof";}

                textfield.setText(output);
            }
        }
    }

    private void fillMonth(){
        updateNumbers();
        month=werkUren.getCurrentMonthList(monthnumber,yearnumber);

        if(month.getFullMonth().size()>=1) {

            for (Integer counter : month.getFullMonth().keySet()
                    ) {
                fillFields(counter);
            }

        }



    }

    private void calculateHoursToWork()
    {


        int minutes=0;
        int hours=0;


        Day[] calculateDays;

        calculateDays=Arrays.copyOf(werkUren.getWorkDays(),5);

        for (Day day:calculateDays) {

            int multiplier = dayCounter(day.getDatum().getDayOfWeek().getValue());

            minutes+=(multiplier* (day.getTijd().getMinute()));

            if (minutes>59){
                hours+=Math.floor(minutes/60);
            }

            minutes=minutes%60;
            hours+=multiplier*(day.getTijd().getHour());


        }


        for (Day day:month.getFullMonth().values()
             ) {
            int temp;
            if(day.getDetail()==Daytype.ZIEK.ordinal()){ temp=day.getDatum().getDayOfWeek().getValue();
                    hours-=werkUren.getWorkDays()[temp-1].getTijd().getHour();
            }
            if(day.getDetail()==Daytype.VERLOF.ordinal()){ temp=day.getDatum().getDayOfWeek().getValue();
                hours-=werkUren.getWorkDays()[temp-1].getTijd().getHour();
            }

        }

        workHoursEachWeek.setText(String.format("%02d",hours ) + " : " + String.format("%02d",  minutes));

        calculateHoursWorked();

    }

    private void calculateHoursWorked()
    {
        int hours=0;
        int minutes=0;
        for (Day day:month.getFullMonth().values()
             ) {
            if(day.getDetail()==Daytype.WERK.ordinal()){
                hours+=day.getTijd().getHour();
                hours+=day.getExtras().getHour();
                minutes+=day.getTijd().getMinute();
                minutes+=day.getExtras().getMinute();
            }

        }

        if (minutes>59){
            hours+=Math.floor(minutes/60);
        }

        minutes=minutes%60;

        workedHours.setText(String.format("%02d",hours ) + " : " + String.format("%02d",  minutes));
        calculateDifference();


    }

    private void calculateDifference(){

        int totaltime;
        int workedTime;
        int result;
        int totalFieldLength=workHoursEachWeek.getText().length();
        int workedFieldLength=workedHours.getText().length();
        String token="  ";

        totaltime=(Integer.valueOf(workHoursEachWeek.getText().substring(0,totalFieldLength-5))*60)+Integer.valueOf(workHoursEachWeek.getText().substring(totalFieldLength-2,totalFieldLength));
        workedTime=(Integer.valueOf(workedHours.getText().substring(0,workedFieldLength-5))*60)+Integer.valueOf(workedHours.getText().substring(workedFieldLength-2,workedFieldLength));

        result=(totaltime-workedTime);

        if (result>0){differenceWorkHoursLabel.setTextFill(Color.RED);token+="Short";}
        if (result<0){differenceWorkHoursLabel.setTextFill(Color.BLUE);result=Math.abs(result);token+="Surplus";}

        differenceWorkHoursLabel.setText(String.valueOf((int)Math.floor(result/60))+" : "+String.valueOf(result%60) +" "+token );


    }



    private int dayCounter(int day ){

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        int endDate=firstDayOfMonth.getMonth().maxLength();

        //Correction to handle 28 days as max instead of default 29 in Calender
        if(!firstDayOfMonth.isLeapYear()&&firstDayOfMonth.getMonth().getValue()==2){
            endDate=--endDate;
        }

        //-1 correctie naar 0 based format
        start.set(firstDayOfMonth.getYear(),firstDayOfMonth.getMonth().getValue()-1,0);
        end.set(firstDayOfMonth.getYear(),firstDayOfMonth.getMonth().getValue()-1,endDate);

        int numberOfDays = 0;

        Integer test=0;


        while (start.before(end)) {
            if (start.get(Calendar.DAY_OF_WEEK) == day) {
                numberOfDays++;
                start.add(Calendar.DATE, 7);
            } else {
                start.add(Calendar.DATE, 1);
            }
        }

        return numberOfDays;


    }


}

