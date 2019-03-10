package client.view.gui;

import client.bll.repository.UserRepository;
import client.bll.repository.UserRepositoryI;
import client.controller.ControllerLogin;
import javafx.fxml.FXML;
//import java.awt.*;
import javafx.scene.control.TextField;
import client.model.constants.SingletonClass;
import server.dao.UserDAOI;
import client.bll.services.UserService;
import client.bll.services.UserServiceI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginController extends Component implements LoginControllerI{

    private  ControllerLogin controllerLogin;
    private AdministratorGUI administratorGUI;
    private RegularUserGUI regularUserGUI;
    UserDAOI userDAOI;
    //private UserServiceI userServiceI;
    private UserRepositoryI userRepositoryI;

   // private String idLoggedin;


    public LoginController(){
        userRepositoryI = new UserRepository();
        controllerLogin = new ControllerLogin(this, userRepositoryI);
        administratorGUI = new AdministratorGUI();
        regularUserGUI = new RegularUserGUI();
    }

    @FXML
    TextField idTextField = new TextField();
    //String username = idTextField.getText();
    @FXML
    TextField passwordTextField = new TextField();

    public void loginButtonCliked()  {
        System.out.println("am apasat pe login buton");
        System.out.println(idTextField.getText());
        System.out.println(passwordTextField.getText());

        controllerLogin.login(idTextField.getText(),passwordTextField.getText());
        //controllerLogin.getIdLoggedIn();

    }

    @Override
    public String getUsername() {
        return idTextField.getText();
    }

    @Override
    public String getPassword() {
        return passwordTextField.getText();
    }
    @Override
    public void showErrorMessage() {
        JOptionPane.showMessageDialog(null, "account does not exist", "WARNING", JOptionPane.WARNING_MESSAGE);
    }
    @Override
    public void showAdminView() {
         administratorGUI.startAdminGUI();
    }

    @Override
    public void showRegularView() {
      //  Platform.setImplicitExit(true);
        String idUser = idTextField.getText();
        SingletonClass x = SingletonClass.getInstance();
        x.setId(idUser);
        //Constants constants = new Constants(id);
        regularUserGUI.startRegularUserGUI(/*idTextField.getText()*/);

        //Application.launch(RegularUserGUI.class);
    }

    //public void hideLogin(){
    //    ((Node)event.getSource()).getScene().getWindow().hide();
    //}

}
