package guiTodolist.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logging.MyLogger;
import todolist.Task;
import todolist.TaskCheckListItem;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * The <code>ControllerTask</code> object represents the controller of the Gui CreateTask.
 * In the controller the logic is separated from the Gui and its elements.
 *
 * @author Lukas Mendel
 */


public class ControllerTask implements Initializable {

    @FXML
    public AnchorPane anchorPaneCreateTask;

    @FXML
    public TextField textFieldHeadingTask;

    @FXML
    public TextArea textAreaDescription;
    @FXML
    public TextArea textAreaNotes;

    @FXML
    public DatePicker datePickerDueDate;

    @FXML
    public ListView listViewChecklist;
    @FXML
    public TextField textFieldChecklistNewEntry;

    @FXML
    public ListView listViewFileAttachment;
    @FXML
    public TextField textFieldNewFileEntry;
    @FXML
    public ComboBox comboboxPriority;

    @FXML
    public Button buttonCreateTask;


    private ObservableList<String> itemsChecklist = FXCollections.observableArrayList();
    private ObservableList<String> itemsFilesList = FXCollections.observableArrayList();
    private ObservableList<String> itemsPriority = FXCollections.observableArrayList();

    private ArrayList<TaskCheckListItem> taskCheckListItems = new ArrayList<>();
    private ArrayList<File> taskFiles = new ArrayList<>();

    private VBoxTasklist vboxTodoList;
    private Task currentTask;
    private VBoxTask vBoxTask;


    public ControllerTask(VBoxTask vBoxTask) {

        this.vBoxTask = vBoxTask;
    }

    public ControllerTask(VBoxTasklist vboxTasklist) {

        this.vboxTodoList = vboxTasklist;
    }


    /**
     * Called to initialize a controller after its root element has been completely processed
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        listViewChecklist.setItems(itemsChecklist);
        listViewFileAttachment.setItems(itemsFilesList);
        itemsPriority.addAll("Hoch", "Mittel", "Niedrig");
        comboboxPriority.setItems(itemsPriority);
        comboboxPriority.getSelectionModel().select(2);

        // Load information into Window...
        if (this.vBoxTask != null) {
            this.currentTask = vBoxTask.getTask();
            initLeftSide();
            initRightSide();
            loadFileAttachments();
        }


    }


    /**
     * load Information into Gui
     */

    private void initLeftSide() {

        if (this.currentTask.getProjectTitle() != null)
            textFieldHeadingTask.setText(this.currentTask.getProjectTitle());
        if (this.currentTask.getProjectDescription() != null)
            textAreaDescription.setText(this.currentTask.getProjectDescription());
        if (this.currentTask.getDeadline() != null)
            datePickerDueDate.setValue(currentTask.getDeadline());
        if (this.currentTask.getNotes() != null)                //Notes
            textAreaNotes.setText(this.currentTask.getNotes());
    }


    /**
     * load Information into Gui
     */

    private void initRightSide() {

        String prio = "";
        if (this.currentTask.getPriority() != null)
            prio = this.currentTask.getPriority();
        switch (prio) {
            case "Hoch":
                comboboxPriority.getSelectionModel().select(0);
                ;
                break;
            case "Mittel":
                comboboxPriority.getSelectionModel().select(1);
                ;
                break;
            case "Niedrig":
                comboboxPriority.getSelectionModel().select(2);
                ;
                break;
        }

        if (this.currentTask.getItemsChecklist() != null) {
            for (TaskCheckListItem taskCheckListItem : this.currentTask.getItemsChecklist()) {

//... noch unvollständig... Checkbox fehlt noch...
            }
        }
    }


    /**
     * load Information into Gui
     */

    private void loadFileAttachments() {

        for (File file : this.currentTask.getFileArrayList()) {

            this.itemsFilesList.add(file.getName());
            this.taskFiles.add(file);
        }
    }


    /**
     * Adds a new entry to the checklist
     */

    public void addEntryToChecklist() {

        MyLogger.LOGGER.entering(getClass().toString(), "addEntryToChecklist");

        itemsChecklist.add("o " + textFieldChecklistNewEntry.getText());
        TaskCheckListItem taskCheckListItem = new TaskCheckListItem(textFieldChecklistNewEntry.getText());
        taskCheckListItems.add(taskCheckListItem);
        textFieldChecklistNewEntry.clear();

        MyLogger.LOGGER.exiting(getClass().toString(), "addEntryToChecklist");
    }

    /**
     * Removes a new entry to the checklist
     */

    public void deleteEntryToChecklist() {

        MyLogger.LOGGER.entering(getClass().toString(), "deleteEntryToChecklist");
        int index = listViewChecklist.getSelectionModel().getSelectedIndex();
        itemsChecklist.remove(index);
        taskCheckListItems.remove(index);
        MyLogger.LOGGER.exiting(getClass().toString(), "deleteEntryToChecklist");
    }


    /**
     * Adds a new file attachment to a list of file.
     */

    public void AddFileAttachmentToTask() {

        MyLogger.LOGGER.entering(getClass().toString(), "AddFileAttachmentToTask");
        if (!textFieldNewFileEntry.getText().trim().isEmpty()) {

            String filename = textFieldNewFileEntry.getText();
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Datei für Aufgabe auswählen");

        List<File> files = fileChooser.showOpenMultipleDialog(null);
        for (File file : files) {

            this.itemsFilesList.add(file.getName());
            this.taskFiles.add(file);
        }
        MyLogger.LOGGER.exiting(getClass().toString(), "AddFileAttachmentToTask");
    }

    /**
     * Removes a new entry to the checklist
     */

    public void deleteFileAttachmentToTask() {

        MyLogger.LOGGER.entering(getClass().toString(), "deleteFileAttachmentToTask");

        int index = listViewFileAttachment.getSelectionModel().getSelectedIndex();
        this.itemsFilesList.remove(index);
        this.taskFiles.remove(index);

        MyLogger.LOGGER.exiting(getClass().toString(), "deleteFileAttachmentToTask");
    }


    /**
     * This method creates a task object as well as the corresponding Gui Task object.
     * Furthermore, different references and IDs are exchanged in order to be able
     * to create one of the tasks belonging to the corresponding task list or the objects behind it.
     */

    public void createTask() {

        MyLogger.LOGGER.entering(getClass().toString(), "createTask");
        this.currentTask = createTaskObjekt();                  /* Object Task is created */
        VBoxTask vBoxnewTask = createNewGuiElemnts();               /* VboxTask will be created  */
        vBoxnewTask.setTaskID(this.currentTask.getTaskId());            /*  Add TaskID from Object */
        currentTask.setTaskListId(vboxTodoList.getTaskListID());            /* Add TaskList-ID to Object from taskList */
        vboxTodoList.getChildren().add(vBoxnewTask);
        Stage stage = (Stage) this.buttonCreateTask.getScene().getWindow();
        stage.close();
        MyLogger.LOGGER.exiting(getClass().toString(), "createTask");
    }

    /**
     * This method creates the Gui object task and all associated functions,
     * e.g. the drag and drop feature.
     */

    public VBoxTask createNewGuiElemnts() {

        MyLogger.LOGGER.entering(getClass().toString(), "createNewGuiElemnts");
        VBoxTask vBoxTask = new VBoxTask(currentTask, vboxTodoList);
        addEventDragDetected(vBoxTask, this.currentTask);
        this.vboxTodoList.setMargin(vBoxTask, new Insets(5, 10, 5, 10));
        MyLogger.LOGGER.exiting(getClass().toString(), "createNewGuiElemnts", vBoxTask);
        return vBoxTask;

    }

    /**
     * This method allows Gui Task to drag as user from one ToDoList to another ToDoList.
     *
     * @param vBoxTask Gui object to add the new event to.
     * @param task     Associated object behind the Gui object
     */

    public void addEventDragDetected(VBoxTask vBoxTask, Task task) {

        MyLogger.LOGGER.entering(getClass().toString(), "addEventDragDetected", new Object[]{vBoxTask, task});
        vBoxTask.setOnDragDetected(mouseEvent -> {
            Dragboard dragboard = vBoxTask.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            DataFormat dataFormat = new DataFormat("VBox");
            clipboardContent.put(dataFormat, task);
            dragboard.setContent(clipboardContent);
        });
        MyLogger.LOGGER.exiting(getClass().toString(), "addEventDragDetected");
    }


    /**
     * Generates the task object based on the user input from the Gui.
     * If nothing is entered in a field, the property of the object is set to null.
     */

    private Task createTaskObjekt() {

        MyLogger.LOGGER.entering(getClass().toString(), "createTaskObjekt");
        Task task = new Task(textFieldHeadingTask.getText().trim().isEmpty() ? null : textFieldHeadingTask.getText());
        task.setProjectDescription(((textAreaDescription.getText().trim().isEmpty() ? null : textAreaDescription.getText())));
        task.setDone(false);
        task.setNotes(textAreaNotes.getText().trim().isEmpty() ? null : textAreaNotes.getText());
        task.setPriority(comboboxPriority.getSelectionModel().getSelectedItem().toString());

        if (listViewChecklist.getItems().size() > 0) {
            ObservableList observableList = listViewChecklist.getItems();
            ArrayList<TaskCheckListItem> arrayList = new ArrayList<>();
            for (Object object : observableList) {
                arrayList.add(new TaskCheckListItem(object.toString()));
            }
            task.setItemsChecklist(arrayList);
        }
        if (taskFiles.size() > 0) {
            task.setFileArrayList(this.taskFiles);
        }
        task.setDeadline(datePickerDueDate.getValue());
        MyLogger.LOGGER.exiting(getClass().toString(), "createTaskObjekt", task);
        return task;
    }


}




