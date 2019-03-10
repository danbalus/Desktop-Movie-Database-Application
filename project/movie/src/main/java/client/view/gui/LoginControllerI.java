package client.view.gui;

import java.io.IOException;

public interface LoginControllerI {
    public void loginButtonCliked() throws InterruptedException, IOException, ClassNotFoundException;
    String getUsername();

    String getPassword();

    public void showErrorMessage( );

    public void showAdminView() ;

    public void showRegularView();
   // public void hideLogin( );
}
