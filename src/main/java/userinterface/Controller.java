package userinterface;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

import java.awt.*;

public class Controller {


    public TextField tbUserEmail;
    public TextField tbPassword;

    public void login() {
        if (tbUserEmail.getText().trim().equals("") || tbPassword.getText().trim().equals("")) {
            showMessage("Please fill in all fields!");
        } else
            showMessage("tet");
    }

    public void showMessage(final String message) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sea battle");
                alert.setHeaderText("Message");
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }

}
