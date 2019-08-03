package guiExam.EditWindowExamResult;

import exam.Exam;
import guiExam.ControllerExam;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEditWindowExamResult implements Initializable {

    @FXML
    public TextField textFieldLectureNrResult;
    @FXML
    public TextField textFieldtechnicalNameResult;
    @FXML
    public TextField textFieldMarkResult;
    @FXML
    public TextField textFieldmodulMarkResult;
    @FXML
    public ComboBox comboBoxTrialNrResult;

    @FXML
    public Button buttonSaveExamChangesResults;

    private ControllerExam controllerExam;
    private Exam exam;


    public ControllerEditWindowExamResult()
    {

    }

    public ControllerEditWindowExamResult(ControllerExam controllerExam, Exam exam)
    {
        this.controllerExam = controllerExam;
        this.exam = exam;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboBoxTrialNrResult.getItems().addAll("1", "2", "3", "4", "5");
        textFieldLectureNrResult.setText(exam.getSubjectNumber());
        textFieldtechnicalNameResult.setText(exam.getTechnicalName());
        textFieldMarkResult.setText(exam.getMark());
        textFieldmodulMarkResult.setText(exam.getModulMark());
        comboBoxTrialNrResult.getSelectionModel().select(returnIndex(exam.getTrialNumber()));

    }

    public int returnIndex(String string) {
        int value = 0;
        switch (string) {

            case "1":
                value = 0;
                break;
            case "2":
                value = 1;
                break;
            case "3":
                value = 2;
                break;
            case "4":
                value = 3;
                break;
            case "5":
                value = 4;
                break;
            case "6":
                value = 5;
                break;
            case "7":
                value = 6;
                break;
            case "8":
                value = 7;
                break;
            case "9":
                value = 8;
                break;
            case "10":
                value = 9;
                break;
        }
        return value;
    }

    @FXML
    private void buttonSaveExamChangesResults() {

        SimpleStringProperty value = new SimpleStringProperty();
        SimpleStringProperty value1 = new SimpleStringProperty();
        SimpleStringProperty value2 = new SimpleStringProperty();
        SimpleStringProperty value3 = new SimpleStringProperty();
        SimpleStringProperty value4 = new SimpleStringProperty();


        value.setValue(textFieldLectureNrResult.getText());
        exam.setSubjectNumber(value);
        value1.setValue(textFieldtechnicalNameResult.getText());
        exam.setTechnicalName(value1);
        value2.setValue(textFieldMarkResult.getText());
        exam.setMark(value2);
        value3.setValue(textFieldmodulMarkResult.getText());
        exam.setModulMark(value3);
        value4.setValue(comboBoxTrialNrResult.getSelectionModel().getSelectedItem().toString());
        exam.setTrialNumber(value4);

        controllerExam.tableviewExamsInsisted.refresh();
        Stage stage = (Stage) this.buttonSaveExamChangesResults.getScene().getWindow();
        stage.close();
    }
}
