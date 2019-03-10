package client.view.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AdministratorGUI /*extends Application */ {



    public void startAdminGUI(){
        Parent root;
        try{

            root = FXMLLoader.load(AdministratorGUI.class.getResource("/fxml/admin.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Admin");
            primaryStage.setScene(new Scene(root, 1200, 900));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

