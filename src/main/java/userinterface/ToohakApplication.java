package userinterface;

import DatabaseServer.DataContext.CredentialsDataContext;
import DatabaseServer.DataContext.IDataContext;
import DatabaseServer.Repositories.IRepository;
import DatabaseServer.Repositories.IUserRepository;
import DatabaseServer.Repositories.UserRepository;
import Models.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;


public class ToohakApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UserInterface.fxml"));
        primaryStage.setTitle("Toohak");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        //  launch(args);
        IUserRepository repo = new UserRepository(new CredentialsDataContext());
        repo.save(new User("test", "tester@gmail.com", "testpsswd"));
    }


}