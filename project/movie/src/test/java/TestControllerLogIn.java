
import client.controller.ControllerLogin;
import client.bll.services.UserService;
import client.bll.services.UserServiceI;

import org.junit.Before;
import org.junit.Test;
import client.view.gui.LoginControllerI;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class TestControllerLogIn {

    private LoginControllerI loginControllerI;
    private UserServiceI userServiceI;
    private ControllerLogin controllerLogin;
    @Before
    public void setUp() {
        loginControllerI = mock(LoginControllerI.class);
        userServiceI  = mock(UserService.class);
       // controllerLogin =new ControllerLogin(loginControllerI, userServiceI);
    }

    @Test
    public void testLogIn() throws InterruptedException, IOException, ClassNotFoundException {

        ControllerLogin controllerLogin = mock(ControllerLogin.class);
        doNothing().when(controllerLogin).login("user","password");
        controllerLogin.login("user","password");
        verify(controllerLogin, atLeastOnce()).login("user","password");
    }

}
