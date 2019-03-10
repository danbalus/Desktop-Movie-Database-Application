package client.controller;

import client.bll.repository.UserRepositoryI;
import client.model.entity.User;
import client.bll.services.UserServiceI;
import client.view.gui.LoginControllerI;

import java.io.IOException;
import java.util.List;

public class ControllerLogin  /*implements Constants*/ {

    private final LoginControllerI loginControllerI;
    //private final UserServiceI userServiceI;
    private final UserRepositoryI userRepositoryI;
    private String idLoggedIn;

    public ControllerLogin(LoginControllerI loginControllerI, UserRepositoryI userRepositoryI ) {
        this.loginControllerI = loginControllerI;
        this.userRepositoryI = userRepositoryI;
    }

    public String getIdLoggedIn() {
        return idLoggedIn;
    }

    public void login(String username, String password)   {

        int ok = 1;
        if (username.equals("admin") && password.equals("admin")) {
            ok = 0;
            loginControllerI.showAdminView();
        } else {
            List<User> userList = null;
            try {
                userList = userRepositoryI.listUser();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (!userList.isEmpty()) {
                for (User user : userList) {
                    if (username.equals(user.getId()) && password.equals(user.getPassword())) {
                        ok = 0;
                        idLoggedIn = username;
                        loginControllerI.showRegularView();
                        break;
                    }
                }
            }
        }
        if (ok == 1) {
            loginControllerI.showErrorMessage();
        }
    }
}
