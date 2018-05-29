package userinterface;

import Models.Lobby;
import Models.User;
import interfaces.IToohakGame;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import restClient.ToohakGame;

public class Controller {


    public TextField tbUserEmail;
    public Button btnLogin;
    public PasswordField tbPassword;
    public TextField tbRegisterEmail;
    public Button btnRegisterConfirm;
    public Button btnJoinLobby;
    public Button btnHostLobby;
    public Button btnRegister;
    public ListView<Lobby> lobbyList;
    public TextField tbIp;
    public TextField tbLobbyName;
    public Button btnCreateLobby;
    public ListView<User> lbPlayersInLobby;
    public Button btnStartGame;
    public IToohakGame game = new ToohakGame(this);

    public void login() {
        String useremail = tbUserEmail.getText();
        String password = tbPassword.getText();
        if (useremail.trim().equals("") || password.trim().equals("")) {
            showMessage("Please fill in all fields!");
        } else {
            String loginResult = game.login(useremail, password);
            showMessage(loginResult);
            if (loginResult.equals("Login successful")) {
                btnLogin.setVisible(false);
                tbUserEmail.setVisible(false);
                tbPassword.setVisible(false);
                btnHostLobby.setVisible(true);
                btnJoinLobby.setVisible(true);
                btnRegister.setVisible(false);
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

    public void btnRegisterClicked() {
        tbRegisterEmail.setVisible(true);
        tbUserEmail.setPromptText("Username");
        btnRegisterConfirm.setVisible(true);
        btnLogin.setVisible(false);
    }

    public void btnRegisterConfirmClicked() {
        String username = tbUserEmail.getText();
        String password = tbPassword.getText();
        String email = tbRegisterEmail.getText();
        if (username.trim().equals("") || password.trim().equals("") || email.trim().equals("")) {
            showMessage("Please fill in all fields!");
        } else {
            String result = game.registerPlayer(username, password, email);
            if (result.equals("Account has been made")) {

                tbRegisterEmail.setVisible(false);
                tbUserEmail.setPromptText("Username/email");
                btnRegisterConfirm.setVisible(false);
                btnLogin.setVisible(true);
                showMessage(result + ". You can now log in");
                tbUserEmail.clear();
                tbRegisterEmail.clear();
                tbPassword.clear();
            } else
                showMessage(result);
        }
    }

    public void btnHostLobbyClicked() {
        game.chooseHostOrClient(true);
        tbIp.setVisible(true);
        tbLobbyName.setVisible(true);
    }

    public void btnJoinLobbyClicked() {
        game.chooseHostOrClient(false);
        lobbyList.setItems(FXCollections.observableArrayList(game.refreshLobbies()));
    }

    public void btnCreateLobbyClicked() {

        if (tbLobbyName.getText().trim().equals("") || tbIp.getText().trim().equals("")) {
            showMessage("Please fill in all fields!");
        } else {
            if (game.createLobby(new Lobby(tbIp.getText(), tbLobbyName.getText()))) {
                tbLobbyName.setVisible(false);
                tbIp.setVisible(false);
                btnCreateLobby.setVisible(false);
            } else {
                showMessage("Something went wrong, please try again!");
            }
        }
    }

    public void addToLobbyList(User user) {
        lbPlayersInLobby.getItems().add(user);
    }

    public void btnStartGameClicked() {

    }
}
