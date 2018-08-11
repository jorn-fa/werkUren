package jorn.hiel.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import jorn.hiel.DAO.DayDaoImpl;
import jorn.hiel.helpers.Day;
import jorn.hiel.helpers.Daytype;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class AddDay implements Initializable{

    ObservableList<String> dayselection = FXCollections.observableArrayList("Werk","Ziek", "Verlof");

    @FXML
    private TextField dayTextfield;

    @FXML
    private TextField extraWorkedTextfield;

    @FXML
    private TextField hoursWorkedTextField;

    @FXML
    private TextField otherTextfield;

    @FXML
    private Button saveAndExit;

    @FXML
    private Button exit;

    @FXML
    private ChoiceBox choichesChoicheBox;

    @FXML
    private void goBack(ActionEvent event)  throws IOException{


        Parent parent = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene rootScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


                dayTextfield.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)){
                        hoursWorkedTextField.requestFocus();
                    }
                });

        hoursWorkedTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)){
                extraWorkedTextfield.requestFocus();
            }

        });

        extraWorkedTextfield.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)){
                otherTextfield.requestFocus();
            }

        });

        choichesChoicheBox.setItems(dayselection);
        choichesChoicheBox.setValue("Werk");


    }


    @FXML
    private void saveAndExit() throws IOException{

        boolean verified = verifyData();


        boolean skipSave=false;
        if(dayTextfield.getText().length()==0&& hoursWorkedTextField.getText().length()==0&& extraWorkedTextfield.getText().length()==0){
            skipSave=true;
        }

        if(!skipSave && verified){
            DayDaoImpl dayDao = new DayDaoImpl();
            dayDao.writeDay(makeADay());
        }

        if(verified) {

            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        }

    }


    private Day makeADay(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        LocalDate date = LocalDate.parse(dayTextfield.getText(),formatter);
        LocalTime time = LocalTime.parse(hoursWorkedTextField.getText());
        LocalTime extras = LocalTime.parse(extraWorkedTextfield.getText());

        Integer other=0;
        System.out.println(choichesChoicheBox.getValue()+"<");

        if(choichesChoicheBox.getValue().toString().toUpperCase().equals(Daytype.VERLOF.name())){other=Daytype.VERLOF.ordinal();}
        if(choichesChoicheBox.getValue().toString().toUpperCase().equals(Daytype.ZIEK.name())){other=Daytype.ZIEK.ordinal();}
        if(choichesChoicheBox.getValue().toString().toUpperCase().equals(Daytype.WERK.name())){other=Daytype.WERK.ordinal();}

        return new Day(date,time,extras, other);
    }


    private boolean verifyData(){

        Alert alert=new Alert(Alert.AlertType.ERROR);
        boolean canPass=true;



        if(dayTextfield.getText().length()==0&& hoursWorkedTextField.getText().length()==0&& extraWorkedTextfield.getText().length()==0&&choichesChoicheBox.getValue().equals("Werk")){

            canPass=true;
            return canPass;
        }





        if (hoursWorkedTextField.getText().matches("\\d{1}:\\d{1}")) {
            String temp = hoursWorkedTextField.getText();
            temp = 0 + temp.substring(0,2) + "0" + temp.substring(2,temp.length());
            hoursWorkedTextField.setText(temp);
        }

        if (extraWorkedTextfield.getText().matches("\\d{1}:\\d{1}")) {
            String temp = extraWorkedTextfield.getText();
            temp = 0 + temp.substring(0,2) + "0" + temp.substring(2,temp.length());
            extraWorkedTextfield.setText(temp);
        }


        if (hoursWorkedTextField.getText().matches("\\d{1}:\\d{2}")) {
            hoursWorkedTextField.setText(0+hoursWorkedTextField.getText());
        }

        if (extraWorkedTextfield.getText().matches("\\d{1}:\\d{2}")) {
            extraWorkedTextfield.setText(0+extraWorkedTextfield.getText());
        }

        if (!hoursWorkedTextField.getText().matches("\\d{2}:\\d{2}") && !hoursWorkedTextField.getText().matches("\\d{2}:\\d{2}"))   {
            alert.setContentText("Verify hour notations");
            canPass=false;
        }

        if(!dayTextfield.getText().matches("\\d{2}/\\d{2}/\\d{4}")){
            alert.setContentText("Wrong format on date dd/mm/yyyy");
            canPass=false;
        }

        if (!choichesChoicheBox.getValue().equals("Werk")&&hoursWorkedTextField.getText().length()==0&&extraWorkedTextfield.getText().length()==0&&dayTextfield.getText().length()==0){
            canPass=false;
            alert.setContentText("Day type selected without details");
        }

        if(choichesChoicheBox.getValue().toString().toUpperCase().equals(Daytype.VERLOF.name())||choichesChoicheBox.getValue().toString().toUpperCase().equals(Daytype.ZIEK.name())){
            hoursWorkedTextField.setText("00:00");
            extraWorkedTextfield.setText("00:00");

            if(!dayTextfield.getText().matches("\\d{2}/\\d{2}/\\d{4}")) {

                alert.setContentText("Forgot to enter the date");
            }
        }




        if(!canPass){alert.showAndWait();}

        return canPass;

    }








}
