package userinterface;

import client.ToohakGame;
import interfaces.IToohakGame;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controller {


    public TextField tbUserEmail;
    public TextField tbPassword;
    public IToohakGame game = new ToohakGame();

    public void login() {
        String useremail = tbUserEmail.getText();
        String password = tbPassword.getText();
        if (useremail.trim().equals("") || password.trim().equals("")) {
            showMessage("Please fill in all fields!");
        } else {
            if (game.login(useremail, password)) {

            }
        }
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
