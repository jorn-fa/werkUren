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










    @FXML
    public void nextMonth(){
        firstDayOfMonth=firstDayOfMonth.plusMonths(1);
        clearTextfields();
        setDateLabels();
        fillMonth();
    }

    @FXML
    public void previousMonth(){
        firstDayOfMonth=firstDayOfMonth.minusMonths(1);
        clearTextfields();
        setDateLabels();
        fillMonth();
    }

    @FXML
    public void dateToCurrentMonth()
    {
        firstDayOfMonth=LocalDate.of(werkUren.getCurrentYear(),werkUren.getCurrentMonth(),1);
        clearTextfields();
        setDateLabels();
        fillMonth();
    }

    @FXML
    private void clearTextfields()
    {
        for (TextField textField: detailsTextFields
                ) {textField.setText("");

        }
        for (TextField textField: urenTextFields
                ) {textField.setText("");

        }
        for (TextField textField: extraTextFields
                ) {textField.setText("");

        }
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
        urenTextFields.addAll(Arrays.asList(day1Uren,day2Uren,day3Uren,day4Uren,day5Uren,day6Uren,day7Uren,day8Uren,day9Uren,day10Uren,day11Uren,day12Uren,day13Uren,day14Uren,day15Uren,day16Uren,
                day17Uren,day18Uren,day19Uren,day20Uren,day21Uren,day22Uren,day23Uren,day24Uren,day25Uren,day26Uren,day27Uren,
                day28Uren,day29Uren,day30Uren,day31Uren));
        detailsTextFields.addAll(Arrays.asList(day1Detail,day2Detail,day3Detail,day4Detail,day5Detail,day6Detail,day7Detail,day8Detail,day9Detail,day10Detail,day11Detail,day12Detail,day13Detail,day14Detail,day15Detail,day16Detail,
                day17Detail,day18Detail,day19Detail,day20Detail,day21Detail,day22Detail,day23Detail,day24Detail,day25Detail,day26Detail,day27Detail,
                day28Detail,day29Detail,day30Detail,day31Detail));
        extraTextFields.addAll(Arrays.asList(day1Extra,day2Extra,day3Extra,day4Extra,day5Extra,day6Extra,day7Extra,day8Extra,day9Extra,day10Extra,day11Extra,day12Extra,day13Extra,day14Extra,day15Extra,day16Extra,
                day17Extra,day18Extra,day19Extra,day20Extra,day21Extra,day22Extra,day23Extra,day24Extra,day25Extra,day26Extra,day27Extra,
                day28Extra,day29Extra,day30Extra,day31Extra));

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
            if(teller.getDayOfWeek().toString().equals("SATURDAY")||teller.getDayOfWeek().toString().equals("SUNDAY")){textfield.setText("weekend");
            }
            teller=teller.plusDays(1);
        }



    }

    private void updateNumbers(){
        monthnumber=firstDayOfMonth.getMonthValue();
        yearnumber=firstDayOfMonth.getYear();
    }

    public void setWerkuren(WerkUren werkUren){
        this.werkUren=werkUren;
        dateToCurrentMonth();
        setDateLabels();



        fillMonth();

    }

    private void fillFields(int counter){
        String urenFieldName="day"+counter+"Uren";
        String extrasFieldName="day"+counter+"Extra";
        String detailFieldName="day"+counter+"Detail";
        System.out.println(urenFieldName);

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
                textfield.setText(String.valueOf(month.getFullMonth().get(counter).getDetail()));
            }
        }
    }

    private void fillMonth(){
        updateNumbers();
        month=werkUren.getCurrentMonthList(monthnumber,yearnumber);


        /*for (Day day:month.getFullMonth().values()
             ) {
            System.out.println(day.getTijd());

        }*/
        for (Integer counter : month.getFullMonth().keySet()
                ) {

            System.out.println(month.getFullMonth().get(counter).getTijd());
            fillFields(counter);
        }

        if(month.getFullMonth().size()>=1) {


        }


    }


}
