package jorn.hiel;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jorn.hiel.DAO.MonthDaoImpl;
import jorn.hiel.helpers.DbaseConnection;

import java.io.IOException;
import java.time.LocalDate;

import jorn.hiel.helpers.Month;
import jorn.hiel.view.MainScreen;
import org.apache.log4j.Logger;

public final class WerkUren extends Application{


    private Logger logger = Logger.getLogger("werkUren");
    private Logger dbaseLogger = Logger.getLogger("dbase");

    private Stage primaryStage;
    public int test = 5;

    private AnchorPane rootLayout;
    private Scene scene1;

    public enum Days {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }



    public WerkUren(){
    }

    public static void main(String[] args) {launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Werkuren Tracker");
        this.primaryStage.setResizable(true);
        this.primaryStage.setWidth(1000);
        this.primaryStage.setHeight(800);
        this.primaryStage.setResizable(false);
        initRootLayout();


    }



    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainScreen.fxml"));
            rootLayout = (AnchorPane) loader.load();          // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);

            MainScreen controller = loader.getController();
            controller.setWerkuren(this);

            primaryStage.setScene(scene);
            primaryStage.show();


            try {
                if (!DbaseConnection.isAlive()){showalert();}
            } catch (Exception e) {
                dbaseLogger.error("Dbase not connected");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void showalert() {

        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Database error");
        alert.setHeaderText("Please check database connection ");
        alert.setContentText("Please connect to database and restart app");

        alert.showAndWait();
        dbaseLogger.debug("DBASE NOT CONNECTED");
        logger.debug("DBASE NOT CONNECTED");

    }


    public java.time.Month getCurrentMonth(){
        return LocalDate.now().getMonth();
    }

    public int getCurrentYear(){
        return LocalDate.now().getYear();
    }

    public Month getCurrentMonthList(int month, int year){
        MonthDaoImpl monthDaoImpl = new MonthDaoImpl();
        return monthDaoImpl.getFullMonth(month,year);
    }

}
