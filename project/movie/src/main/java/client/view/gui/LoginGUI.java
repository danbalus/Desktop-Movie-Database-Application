package client.view.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginGUI extends Application {


    public void start(Stage primaryStage) throws Exception {
        /// URL url = new File("admin.fxml").toURI().toURL();
        //Parent root = FXMLLoader.load(url);
        Parent root = FXMLLoader.load(LoginGUI.class.getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 220, 150));
        primaryStage.show();
       // System.out.println("gui thread: "+ Thread.currentThread().getName());
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
    }



}
