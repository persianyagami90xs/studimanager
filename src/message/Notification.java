package message;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;

public class Notification {
    public static void showAlertWindow(javafx.scene.control.Alert.AlertType alertType, Window owner, String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void showConfirm(String title, String text, Window window)  {
        Notifications.create()
                .title(title)
                .text(text)
                .owner(window)
                .position(Pos.BOTTOM_RIGHT)
                .graphic(new ImageView("file:resources/icons8-ok-48.png"))
                .show();
    }

    public static void showInfo(String title, String text, Window window)  {
        Notifications.create()
                .title(title)
                .text(text)
                .owner(window)
                .position(Pos.BOTTOM_RIGHT)
                .graphic(new ImageView("file:resources/icons8-info-48.png"))
                .show();
    }

    public static void showAlert(String title, String text, Window window)  {
        Notifications.create()
                .title(title)
                .text(text)
                .owner(window)
                .position(Pos.BOTTOM_RIGHT)
                .graphic(new ImageView("file:resources/icons8-cancel-48.png"))
                .show();
    }
}
