package guiCalendar.welcome_screen;

import guiCalendar.calendar.ControllerCalendar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Controller;
import sample.Main;
import timetable.Timetable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David Sugar
 */
public class ControllerWelcomeScreen implements Initializable {

    @FXML
    public GridPane welcome_grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome_grid.setHgap(10);
        welcome_grid.setVgap(10);
        welcome_grid.setPadding(new Insets(25, 25, 25, 25));
        welcome_grid.setAlignment(Pos.CENTER);

        FileChooser fileChooser = new FileChooser();

        /*
        ##################### NEW TIMETABLE ###################################
         */
        Label newTimetableTitle = new Label("Create a new Timetable:");
        welcome_grid.add(newTimetableTitle, 0,0);
        Button newTimetableButton = new Button("new");
        welcome_grid.add(newTimetableButton, 1, 0);

        /*
        ###################### IMPORT #########################################
         */
        Label importTimetableTitle = new Label("Open existing Timetable:");
        welcome_grid.add(importTimetableTitle, 0, 1);
        Button importTimetableButton = new Button("open");
        welcome_grid.add(importTimetableButton, 1, 1);

        /*
        ###################### BUTTON EVENTS #################################
         */
        newTimetableButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        importTimetableButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File selectedFile = fileChooser.showOpenDialog(welcome_grid.getScene().getWindow());
                Timetable timetable = null;
                Stage primaryStage = Main.getPrimaryStage();

                if( selectedFile != null) {
                    try {
                        timetable = Timetable.load(selectedFile.getPath());
                        ControllerCalendar.setTimetable(timetable);

                        Parent root = FXMLLoader.load(getClass().getResource("../../sample/" + Main.fxml));
                        primaryStage.setTitle(Main.TITLE);
                        primaryStage.setScene(new Scene(root, Main.WIDTH , Main.HEIGHT));
                        primaryStage.show();

                        Stage stage = (Stage) welcome_grid.getScene().getWindow();
                        stage.close();
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        });
    }




}
