package jorn.hiel.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jorn.hiel.WerkUren;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HoursPref implements Initializable {

    private WerkUren werkUren;
    private Stage stage;
    private boolean closed=false;

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






    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void closeAction(ActionEvent event) throws IOException{
        System.out.println("opslaan");

        Parent parent = FXMLLoader.load(getClass().getResource("/view/Mainscreen.fxml"));
        Scene rootScene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
        //window.setScene(rootScene);


    }



}
