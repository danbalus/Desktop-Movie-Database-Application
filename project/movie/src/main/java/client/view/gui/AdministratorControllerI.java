package client.view.gui;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import client.model.entity.Movie;
import client.model.entity.User;

import java.io.IOException;

public interface AdministratorControllerI {
    public void addMovieButtonClicked();

    public void addActorToMovieButtonClicked();

    public void deleteActorFromMovieButtonClicked();

    public void readMovieButtonClicked();

    public void updateMovieButtonClicked() throws InterruptedException;

    public void deleteMovieButtonClicked();


    public void addUserButtonClicked();

    public void readUserButtonClicked();

    public void updateUserButtonClicked();

    public void deleteUserButtonClicked();



    public TableView getUserTableView();

    public TableView getMovieTableView();

    public TableColumn getMovieIdTableColumn();

    public TableColumn getMovieGenreTableColumn();

    public TableColumn getMovieNameTableColumn();

    public TableColumn getMovieInfoTableColumn();

    public TableColumn getMovieRateTableColumn();

    public TableColumn getMovieActorsTableColumn();

    public TableColumn getUserdTableColumn();

    public TableColumn getUserPasswordTableColumn();

    public TableColumn getUserNameTableColumn();

    public TableColumn getUserdTypeAccColumn();

    public TableColumn getUserEmailTableColumn();

    public TableColumn getUserAgeTableColumn();

    public TableColumn getUserRatingsTableColumn();
    public TableColumn<Movie, String> getMovieStatusTableColumn() ;
    // public String getRaportNameTXTTextField() ;

    //public String getRaportNamePDFTextField() ;
    public void setUserTableView(TableView userTableView);

    public void setMovieTableView(TableView movieTableView);

    public void setDataT(ObservableList<Movie> dataT);


    public void init();

    public void writeInGui();

    public void setReadData(Movie movie);

    void initUser();

    void setDataUserT(ObservableList<User> data);

    public void setReadDataUser(User user);

    public void generatePDFButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;

    public void generateTXTButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;

    public void showAllMoviesButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;

    public void showAllUsersButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;
}