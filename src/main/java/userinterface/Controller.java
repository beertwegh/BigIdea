package userinterface;

import Models.Lobby;
import Models.Question;
import Models.User;
import databaseServer.repositories.UserRepository;
import interfaces.IGame;
import interfaces.IToohakGame;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import restClient.ToohakGame;
import restClient.host.IHostGame;
import restClient.player.ClientGame;
import restClient.player.websocket.ClientWebSocket;
import shared.Logging.Logger;
import shared.MultipleChoice;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
    public Button btnJoinSelectedLobby;
    public Button btnA;
    public Button btnB;
    public Button btnC;
    public Button btnD;
    public Label lblA;
    public Label lblB;
    public Label lblC;
    public Label lblD;
    public Label lblQuestion;

    public IToohakGame game = new ToohakGame(this);


    //region Authentication
    public void login() {
        String useremail = tbUserEmail.getText();
        String password = tbPassword.getText();
        if (useremail.trim().equals("") || password.trim().equals("")) {
            showMessage("Please fill in all fields!");
        } else {
            String loginResult = game.login(useremail, password);
            showMessage(loginResult);
            if (loginResult.contains("Login successful")) {
                btnLogin.setVisible(false);
                tbUserEmail.setVisible(false);
                tbPassword.setVisible(false);
                btnHostLobby.setVisible(true);
                btnJoinLobby.setVisible(true);
                btnRegister.setVisible(false);

            }
        }
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

    public void btnRegisterClicked() {
        tbRegisterEmail.setVisible(true);
        tbUserEmail.setPromptText("Username");
        btnRegisterConfirm.setVisible(true);
        btnLogin.setVisible(false);
    }
//endregion

    //region Client/Player

    public void btnJoinLobbyClicked() {
        game.chooseHostOrClient(false);
        lobbyList.setItems(FXCollections.observableArrayList(game.refreshLobbies()));
        lobbyList.setVisible(true);
        btnJoinLobby.setVisible(false);
        btnHostLobby.setVisible(false);
        btnJoinSelectedLobby.setVisible(true);
    }

    public void processAnswerCorrect() {
        showMessage("Correct! You've scored 10 points!");
    }

    public void processAnswerWrong() {
        showMessage("Wrong! Next time better!");
    }

    public void btnJoinSelectedLobbyClicked() {
        if (lobbyList.getSelectionModel().getSelectedItem() == null) {
            showMessage("Please select a lobby");
        } else {
            game.joinLobby(lobbyList.getSelectionModel().getSelectedItem());
            lobbyList.setVisible(false);
            btnJoinSelectedLobby.setVisible(false);
        }
    }

    public void processNextRoundClient() {
        toggleChoiceButtons(true);
    }

    public void btnApressed() {
        answerQuestion(MultipleChoice.A);
    }

    public void btnBpressed() {
        answerQuestion(MultipleChoice.B);
    }

    public void btnCpressed() {
        answerQuestion(MultipleChoice.C);
    }

    public void btnDpressed() {
        answerQuestion(MultipleChoice.D);
    }

    private void answerQuestion(MultipleChoice answer) {
        toggleChoiceButtons(false);
        game.answerQuestion(answer);
    }

    private void toggleChoiceButtons(boolean enabled) {
        btnA.setDisable(!enabled);
        btnB.setDisable(!enabled);
        btnC.setDisable(!enabled);
        btnD.setDisable(!enabled);
    }

    public void processStartGameClient() {
        btnA.setVisible(true);
        btnB.setVisible(true);
        btnC.setVisible(true);
        btnD.setVisible(true);

    }
    //endregion

    //region Server/Host

    public void btnHostLobbyClicked() {
        game.chooseHostOrClient(true);
        tbIp.setVisible(true);
        tbLobbyName.setVisible(true);
        btnCreateLobby.setVisible(true);
        btnHostLobby.setVisible(false);
        btnJoinLobby.setVisible(false);
    }

    public void btnCreateLobbyClicked() {

        if (tbLobbyName.getText().trim().equals("") || tbIp.getText().trim().equals("")) {
            showMessage("Please fill in all fields!");
        } else {
            InetAddress ownIP = null;
            try {
                ownIP = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                Logger.getInstance().log(e);
            }
            String ip = ownIP.getHostAddress();
            if ((game.createLobby(new Lobby(ip + ":" + tbIp.getText(), tbLobbyName.getText()))).equals("Lobby has been added")) {
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
        game.startGame();
        lblQuestion.setVisible(true);
        lblA.setVisible(true);
        lblB.setVisible(true);
        lblC.setVisible(true);
        lblD.setVisible(true);
    }

    public void processNextRoundHost(Question question) {
        lblQuestion.setText(question.getText());
        btnA.setText(question.getAnswers().get(0).toString());
        btnB.setText(question.getAnswers().get(1).toString());
        btnC.setText(question.getAnswers().get(2).toString());
        btnD.setText(question.getAnswers().get(3).toString());
    }
    //endregion

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