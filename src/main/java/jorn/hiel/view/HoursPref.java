package jorn.hiel.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import jorn.hiel.WerkUren;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HoursPref implements Initializable {

    private WerkUren werkUren;

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
    public void saveAction(){
        System.out.println("opslaan");
    }

    @FXML
    public void close(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
