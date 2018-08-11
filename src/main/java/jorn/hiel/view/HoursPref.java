package jorn.hiel.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jorn.hiel.DAO.DayDaoImpl;
import jorn.hiel.WerkUren;
import jorn.hiel.helpers.Day;


import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;

public class HoursPref implements Initializable {

    private WerkUren werkUren;
    private Stage stage;
    private boolean closed=false;
    Day days[];




    @FXML
    private AnchorPane ap;

    @FXML
    public TextField mondayField;

    @FXML
    public TextField tuesdayField;

    @FXML
    public TextField wednesdayField;

    @FXML
    public TextField thursdayField;

    @FXML
    public TextField fridayField;

    @FXML
    public Button saveButton;

    private TextField weekfields[];





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mondayField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    tuesdayField.requestFocus();
                }
            }
        });
        tuesdayField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    wednesdayField.requestFocus();
                }
            }
        });
        wednesdayField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    thursdayField.requestFocus();
                }
            }
        });
        thursdayField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    fridayField.requestFocus();
                }
            }
        });
        fridayField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    mondayField.requestFocus();
                }
            }
        });




        fillDays();
        passDays();


    }

    @FXML
    private void closeAction(ActionEvent event) throws IOException{

        weekfields = new TextField[]{mondayField,tuesdayField,wednesdayField,thursdayField,fridayField};

        int counter=0;

        for (TextField textfield:weekfields
             ) {
            counter += verifyData(textfield) ? 1 : 0;
        }



        if( counter==weekfields.length){

            saveData();



            Parent parent = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            Scene rootScene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

        }



    }

    public void setWerkuren(WerkUren werkUren) {
        this.werkUren = werkUren;
        System.out.println(werkUren.getName());
    }

    private void fillDays(){
        DayDaoImpl dayDao = new DayDaoImpl();
        days=dayDao.readConfig();
        mondayField.setText(compareDay("MONDAY"));
        tuesdayField.setText(compareDay("TUESDAY"));
        wednesdayField.setText(compareDay("WEDNESDAY"));
        thursdayField.setText(compareDay("THURSDAY"));
        fridayField.setText(compareDay("FRIDAY"));
    }

    private void passDays(){

        String[] hourList = new String[5];
        hourList[0]=mondayField.getText();
        hourList[1]=tuesdayField.getText();
        hourList[2]=wednesdayField.getText();
        hourList[3]=thursdayField.getText();
        hourList[4]=fridayField.getText();


    }


    private String compareDay(String compareString )
    {
        String totalTime="";
        for ( int i = 0;i<days.length;i++){
            if ((days[i].getDatum().getDayOfWeek().toString().toLowerCase()).equals(compareString.toLowerCase())){

                return days[i].getTijd().toString();
            }

            else {totalTime="not set";}
        }
        return totalTime;
    }

    private boolean verifyData(TextField textField )
    {

        boolean pass=true;
        Alert alert=new Alert(Alert.AlertType.ERROR);

        String name = textField.getId().substring(0,textField.getId().length()-5);

        alert.setTitle("Error");
        alert.setHeaderText("Error in field: " + name);

        //correction on 0x on hour notation
        if (textField.getText().matches("\\d{1}:\\d{2}")) {
            textField.setText(0+textField.getText());
        }

        if (textField.getText().length()>5){
            alert.setContentText("To many characters");
            pass=false;
        }
        if (textField.getText().length()<5) {
            alert.setContentText("To few characters");
            pass=false;
        }


        if (textField.getText().matches("\\d{2}:\\d{2}")) {

            if (textField.getText(0, 2).contains(":")) {
                alert.setContentText("Hour format out of bounds");
                pass = false;
            } else {

                if (Integer.valueOf(textField.getText(0, 2)) >= 24 || (Integer.valueOf(textField.getText(0, 2)) < 0)) {
                    alert.setContentText("Hour value out of bounds");
                    pass = false;
                }
                if (Integer.valueOf(textField.getText(3, 5)) >= 60 || (Integer.valueOf(textField.getText(3, 5)) < 0)) {
                    System.out.println(Integer.valueOf(textField.getText(3, 5)));
                    alert.setContentText("Minute value out of bounds");
                    pass = false;
                }
            }

        }
        else
        {
            alert.setContentText("No numbers");
            pass=false;
        }

        if (!pass){alert.showAndWait();}
        return pass;
    }

    private void saveData(){
        for (TextField textField:weekfields
             ) {
            DayDaoImpl dayDaoImpl = new DayDaoImpl();

            String date = textField.getId().substring(0,textField.getId().length()-5).toLowerCase();

            LocalDate today=LocalDate.now();

            switch (date){
                case "monday": today = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));break;
                case "tuesday": today = today.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));break;
                case "wednesday": today = today.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));break;
                case "thursday": today = today.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));break;
                case "friday":today = today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));break;
            }

            LocalTime time = LocalTime.of(Integer.valueOf(textField.getText(0, 2)),Integer.valueOf(textField.getText(3, 5)));
            Day day = new Day(today, time, time, 0);
            dayDaoImpl.writeConfig(day);


        }
    }




}
