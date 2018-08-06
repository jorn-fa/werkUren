package jorn.hiel;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jorn.hiel.DAO.DayDaoImpl;
import jorn.hiel.DAO.MonthDaoImpl;
import jorn.hiel.helpers.Day;
import jorn.hiel.helpers.DbaseConnection;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

import jorn.hiel.helpers.Month;
import jorn.hiel.view.MainScreen;
import org.apache.log4j.Logger;

public class WerkUren extends Application{


    private String[] workHours = new String[5];
    private Day workDays[]=new Day[5];

    private final static String fileName = "hours.properties";
    private final static String directory = System.getProperty("user.dir") + "/src/main/resources/".replace("/", File.separator);

    private static String Propfile = directory + fileName;

    private Logger logger = Logger.getLogger("werkUren");
    private Logger dbaseLogger = Logger.getLogger("dbase");

    private Stage primaryStage;
    public int test = 5;

    private AnchorPane rootLayout;
    private Scene scene1;



    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public WerkUren(){
    }

    public static void main(String[] args) {launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        WerkUren werkUren=new WerkUren();
        this.setName("test");


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
            fillWorkhourDef();
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

    public String[] getWorkHours(){
        return workHours;
    }

    public Day[] getWorkDays(){
        //refresh data on changed call
        fillWorkhourDef();
        return workDays;
    }


    public void fillWorkhourDef() {
        DayDaoImpl dayDaoimpl = new DayDaoImpl();
        this.workDays= dayDaoimpl.readConfig();
    }



}
