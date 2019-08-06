package guiTodolist;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The <code>ControllerTodoList</code> object represents the controller of the Gui ToDoList.
 * This is one of the tabs of the TabPane.
 * In the controller the logic is separated from the Gui and its elements.
 *
 * @author Lukas Mendel
 */

public class ControllerTodolist implements Initializable {

    @FXML
    public AnchorPane anchorPaneToDoList;
    @FXML
    public Button buttonEditCanBan;

    @FXML
    public HBox hboxToDoLists;


    /**
     * Called to initialize a controller after its root element has been completely processed
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void createNewSection()
    {
        VBox vBoxList = new VBox(10);
        vBoxList.setPrefWidth(200);
        vBoxList.setPrefWidth(200);
        hboxToDoLists.setMargin( vBoxList , new Insets(10,10,10,10));

        /*  add List controls and heading */
        HBox hBoxHeading = new HBox();
        Label labelHeading = new Label("Überschrift");
        labelHeading.setPadding(new Insets(2,10,2,10));
        Button button = new Button("...");

        hBoxHeading.getChildren().addAll(labelHeading, button);
        vBoxList.getChildren().add(hBoxHeading);
        hBoxHeading.setAlignment(Pos.CENTER_RIGHT);
        hBoxHeading.setMargin( labelHeading , new Insets(0,35,0,0));

        vBoxList.getStyleClass().add("vBox");
        vBoxList.setMargin( hBoxHeading , new Insets(10,10,10,10));
        hboxToDoLists.getChildren().add(vBoxList);

    }

}
