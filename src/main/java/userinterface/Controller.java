package userinterface;

import interfaces.IToohakGame;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controller {


    public TextField tbUserEmail;
    public TextField tbPassword;
    public IToohakGame game;

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
                alert.setTitle("Toohak");
                alert.setHeaderText("Message");
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }

}
