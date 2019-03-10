package client.view.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class RegularUserGUI /*extends Application*/ {

    public void startRegularUserGUI(/*String idLoggedUser*/) {

        Parent root;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/fxml/regularUser.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("User");
            primaryStage.setScene(new Scene(root, 1200, 900));
            primaryStage.show();

           // FXMLLoader loader = new FXMLLoader(
            //        getClass().getResource(
             //               "/fxml/regularUser.fxml"));
           // RegularUserController client.controller =
            //        loader.<RegularUserController>getController();
            //System.out.println("\n\n\n\n\n\n=======" + idLoggedUser);
            //client.controller.setIdLoggedUser(idLoggedUser);
            //primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

