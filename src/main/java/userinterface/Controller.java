package userinterface;

import interfaces.IToohakGame;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Lobby;
import models.Question;
import models.User;
import restclient.ToohakGame;
import restclient.host.HostGame;
import restclient.player.ClientGame;
import shared.logging.Logger;
import shared.MultipleChoice;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Controller {

    private final static String emptyFieldsMsg = "Please fill in all fields!";
    @FXML
    private TextField tbUserEmail;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField tbPassword;
    @FXML
    private TextField tbRegisterEmail;
    @FXML
    private Button btnRegisterConfirm;
    @FXML
    private Button btnJoinLobby;
    @FXML
    private Button btnHostLobby;
    @FXML
    private Button btnRegister;
    @FXML
    private ListView<Lobby> lobbyList;
    @FXML
    private TextField tbIp;
    @FXML
    private TextField tbLobbyName;
    @FXML
    private Button btnCreateLobby;
    @FXML
    private ListView<User> lbPlayersInLobby;
    @FXML
    private Button btnStartGame;
    @FXML
    private Button btnJoinSelectedLobby;
    @FXML
    private Button btnA;
    @FXML
    private Button btnB;
    @FXML
    private Button btnC;
    @FXML
    private Button btnD;
    @FXML
    private Label lblA;
    @FXML
    private Label lblB;
    @FXML
    private Label lblC;
    @FXML
    private Label lblD;
    @FXML
    private Label lblQuestion;
    @FXML
    private Button btnNextRound;
    @FXML
    private Label lblEnded;
    @FXML
    private Button btnClearLobbies;
    private IToohakGame game = new ToohakGame(this);


    //region Authentication
    public void login() {
        String useremail = tbUserEmail.getText();
        String password = tbPassword.getText();
        if (useremail.trim().equals("") || password.trim().equals("")) {
            showMessage(emptyFieldsMsg);
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
                if (loginResult.contains("admin")) {
                    btnClearLobbies.setVisible(true);
                }
            }
        }
    }


    public void btnRegisterConfirmClicked() {
        String username = tbUserEmail.getText();
        String password = tbPassword.getText();
        String email = tbRegisterEmail.getText();
        if (username.trim().equals("") || password.trim().equals("") || email.trim().equals("")) {
            showMessage(emptyFieldsMsg);
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
        game.chooseHostOrClient(new ClientGame());
        lobbyList.setItems(FXCollections.observableArrayList(game.refreshLobbies()));
        lobbyList.setVisible(true);
        btnJoinLobby.setVisible(false);
        btnHostLobby.setVisible(false);
        btnJoinSelectedLobby.setVisible(true);
        btnClearLobbies.setVisible(false);
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

    public void processEndGameClient() {
        btnA.setVisible(false);
        btnB.setVisible(false);
        btnC.setVisible(false);
        btnD.setVisible(false);
        showMessage("Game has ended!");
    }
    //endregion

    //region Server/Host

    public void btnHostLobbyClicked() {
        game.chooseHostOrClient(new HostGame());
        tbIp.setVisible(true);
        tbLobbyName.setVisible(true);
        btnCreateLobby.setVisible(true);
        btnHostLobby.setVisible(false);
        btnJoinLobby.setVisible(false);
        btnClearLobbies.setVisible(false);
    }

    public void btnCreateLobbyClicked() {

        if (tbLobbyName.getText().trim().equals("") || tbIp.getText().trim().equals("")) {
            showMessage(emptyFieldsMsg);
        } else {
            InetAddress ownIP = null;
            try {
                ownIP = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                Logger.getInstance().log(e);
            }
            String ip = null;
            if (ownIP != null) {
                ip = ownIP.getHostAddress();
            }
            if ((game.createLobby(new Lobby(ip + ":" + tbIp.getText(), tbLobbyName.getText()))).equals("Lobby has been added")) {
                tbLobbyName.setVisible(false);
                tbIp.setVisible(false);
                btnCreateLobby.setVisible(false);
                lbPlayersInLobby.setVisible(true);
                btnStartGame.setVisible(true);
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
        lbPlayersInLobby.setVisible(false);
        btnStartGame.setVisible(false);
        lblQuestion.setVisible(true);
        lblA.setVisible(true);
        lblB.setVisible(true);
        lblC.setVisible(true);
        lblD.setVisible(true);

    }

    public void processNextRoundHost(Question question) {
        btnNextRound.setVisible(true);
        lblQuestion.setText(question.getText());
        lblA.setText(question.getAnswers().get(0).toString());
        lblB.setText(question.getAnswers().get(1).toString());
        lblC.setText(question.getAnswers().get(2).toString());
        lblD.setText(question.getAnswers().get(3).toString());
    }

    public void btnNextRoundClicked() {
        game.nextRound();
    }

    public void processEndGameHost() {
        lblQuestion.setVisible(false);
        lblA.setVisible(false);
        lblB.setVisible(false);
        lblC.setVisible(false);
        lblD.setVisible(false);
        lblEnded.setVisible(true);
    }
    //endregion

    public void btnClearLobbiesClicked() {
        showMessage(game.clearLobbies());
    }

    private void showMessage(final String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Toohak");
            alert.setHeaderText("Message");
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}