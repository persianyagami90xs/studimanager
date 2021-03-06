package Main;

import guiCalendar.calendar.ControllerCalendar;
import guiCalendar.create.timetable.TimetableController;
import guiExam.ControllerExam;
import guiTodolist.ControllerTodolist;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logging.MyLogger;
import message.Notification;
import serializer.TimetableObjectCollection;
import timetable.Timetable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class Controller implements Initializable {

    @FXML
    private VBox main_vbox;

    @FXML
    public Menu fileButton;

    @FXML
    public MenuItem saveButton;

    @FXML
    public MenuItem settingsButton;

    @FXML
    public MenuItem newTimetable;

    @FXML
    public MenuItem saveAsButton;

    @FXML
    public MenuItem openTimetable;

    @FXML
    public Menu helpButton;
    @FXML
    public MenuItem about;

    @FXML
    public Tab tabTimetable;
    @FXML
    public Tab tabToDoList;
    @FXML
    public Tab tabExamOverview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        fileButton.setText(Main.getBundle().getString("File"));
        newTimetable.setText(Main.getBundle().getString("New"));
        openTimetable.setText(Main.getBundle().getString("Open"));
        saveButton.setText(Main.getBundle().getString("Save"));
        saveAsButton.setText(Main.getBundle().getString("SaveAs"));
        settingsButton.setText(Main.getBundle().getString("Settings"));

        helpButton.setText(Main.getBundle().getString("Help"));
        about.setText(Main.getBundle().getString("Documetation"));

        tabTimetable.setText(Main.getBundle().getString("Timetable"));
        tabToDoList.setText(Main.getBundle().getString("ToDoList"));
        tabExamOverview.setText(Main.getBundle().getString("ExamOverview"));

        registerButtonEvents();
    }

    private void registerButtonEvents() {
        /*
        ################### SAVE Timetable ###############################
         */
        saveButton.setOnAction(actionEvent -> {
            try {
                String path = Main.getConfig().getTimetablePath();
                if(path.equals("")) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialFileName("timetable_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ".json");
                    File file = fileChooser.showSaveDialog(Main.getPrimaryStage());

                    if(file == null) return;

                    path = file.getPath();
                    Main.getConfig().setTimetablePath(path);
                    Main.getConfig().store();
                }

                ControllerCalendar.getTimetable().store(path);
                /*
                visual notification: FILE SAVED
                 */
                Notification.showConfirm(Main.getBundle().getString("Success"),
                        Main.getBundle().getString("FileSaved"), Main.getPrimaryStage());
            }  catch (Exception exc) {
                Notification.showAlert(Main.getBundle().getString("Failure"),
                        Main.getBundle().getString("FileNotSaved"), Main.getPrimaryStage());
            }
        });

        saveAsButton.setOnAction(actionEvent -> {
            String path;
            FileChooser fileChooser = new FileChooser();

            fileChooser.setInitialFileName("timetable_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ".json");
            File file = fileChooser.showSaveDialog(Main.getPrimaryStage());

            if(file == null) return;

            path = file.getPath();
            Main.getConfig().setTimetablePath(path);
            try {
                Main.getConfig().store();
                ControllerCalendar.getTimetable().store(path);
            } catch (Exception exc) {
                Notification.showAlert(Main.getBundle().getString("Failure"),
                        Main.getBundle().getString("FileNotSaved"), Main.getPrimaryStage());
            }

            Notification.showConfirm(Main.getBundle().getString("Success"),
                    Main.getBundle().getString("FileSaved"), Main.getPrimaryStage());
        });



        settingsButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/config/gui/layoutConfig.fxml"));
                    Parent root = loader.load();

                    // show settings
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(Main.getBundle().getString("Settings"));

                    // prevent interaction with the primary stage until the new window is closed
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(Main.getPrimaryStage());
                    stage.setResizable(false);
                    // show window
                    stage.show();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });

        newTimetable.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiCalendar/create/timetable/layoutTimetable.fxml"));
                    Parent root = loader.load();

                    /* assign this stage as parent (this stage should only be closed after the successful creation of a new timetable)*/
                    TimetableController timetableController = loader.getController();
                    timetableController.setParent(Main.getPrimaryStage());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(Main.getBundle().getString("New") + " " + Main.getBundle().getString("Timetable"));

                    // prevent interaction with the primary stage until the new window is closed
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(Main.getPrimaryStage());
                    stage.setResizable(false);
                    // show window
                    stage.show();
                } catch (IOException e) {
                    MyLogger.LOGGER.log(Level.SEVERE, "Unable to open New Lecture dialog window.\n" + e.getMessage());
                }
            }
        });

        openTimetable.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                Stage stage = (Stage) main_vbox.getScene().getWindow();

                File selectedFile = fileChooser.showOpenDialog(stage);
                TimetableObjectCollection timetableObjectCollection;

                if( selectedFile != null) {     // open the primary stage using the chosen File
                    try {
                        /*
                        -------------- Set Timetable object ----------------------------------
                         */
                        timetableObjectCollection = Timetable.load(selectedFile.getPath());
                        ControllerCalendar.setTimetable(timetableObjectCollection.getTimetable());
                        ControllerExam.setExamList(timetableObjectCollection.getExamList());
                        ControllerTodolist.setTaskListCollection(timetableObjectCollection.getTaskListCollection());


                        /*
                        ----------------- UPDATE timetablePath IN CONFIG_FILE ----------------
                         */
                        Main.getConfig().setTimetablePath(selectedFile.getPath());
                        try {
                            Main.getConfig().store();
                        } catch (IOException e) {
                            MyLogger.LOGGER.log(Level.SEVERE, "Couldn't update config data." +
                                    "\nClass: " + getClass().toString() + "\nMethod: handle()" + "\n" + e.getMessage());
                        }


                        stage.close();
                        /*
                        --------------- Show primary stage ------------------------------------
                         */
                        Parent root = FXMLLoader.load(getClass().getResource(Main.fxml));
                        Main.getPrimaryStage().setTitle(Main.TITLE);
                        Main.getPrimaryStage().setScene(new Scene(root, Main.WIDTH , Main.HEIGHT));
                        Main.getPrimaryStage().show();
                    } catch (Exception exc) {
                        Notification.showInfo("Oops...",
                                Main.getBundle().getString("CantOpenFile"),
                                stage);
                    }
                }
            }
        });
    }

    @FXML
    public void handleAboutButtonAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("about/layoutAbout.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("About");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(Main.getPrimaryStage());
            stage.show();
        } catch (IOException exc) {
            MyLogger.LOGGER.log(Level.SEVERE, "in: src.Main.Controller\nat:handleAboutButtonAction\n" + exc.getMessage());
        }
    }


}
