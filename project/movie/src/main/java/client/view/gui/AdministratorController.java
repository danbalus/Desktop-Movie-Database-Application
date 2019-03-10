package client.view.gui;

import client.bll.repository.ActorRepository;
import client.bll.repository.ActorRepositoryI;
import client.controller.ControllerAdmin;
import client.model.entity.Actor;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import client.model.entity.Movie;
import client.model.entity.User;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class AdministratorController  implements AdministratorControllerI{
    private ControllerAdmin controllerAdmin;
    private ObservableList<Movie> dataT;
    private ObservableList<User> dataUserT;

    public AdministratorController(){
        controllerAdmin = new ControllerAdmin(this);
        dataT = FXCollections.observableArrayList();
        dataUserT = FXCollections.observableArrayList();
    }

    @FXML
    TextField addMovieNameTextField = new TextField();

    @FXML
    TextField addMovieGenreTextField = new TextField();
    @FXML
    TextField addMovieInfoTextField = new TextField();
    @FXML
    TextField addMovieRateTextField = new TextField();


    @FXML
    TextField addMovieStatusTextField = new TextField();


    @FXML
    TextField addActorIdMovieTextField = new TextField();
    @FXML
    TextField addActorIdActorTextField = new TextField();

    @FXML
    TextField deleteActorIdMovieTextField = new TextField();
    @FXML
    TextField deleteActorIdActorTextField = new TextField();

    @FXML
    TextField readMovieIdMovieTextField = new TextField();

    @FXML
    TextField updateMovieIdMovieTextField = new TextField();
    @FXML
    TextField updateMovieNameTextField = new TextField();
    @FXML
    TextField updateMovieGenreTextField = new TextField();
    @FXML
    TextField updateMovieInfoTextField = new TextField();
    @FXML
    TextField updateMovieRateTextField = new TextField();
    @FXML
    TextField updateMovieStatusTextField = new TextField();



    @FXML
    TextField deleteMovieIdTextField = new TextField();



    @FXML
    TextField addUserIdTextField = new TextField();

    @FXML
    TextField addUserNameTextField = new TextField();

    @FXML
    TextField addUserTypeAccTextField = new TextField();

    @FXML
    TextField addUserEmailTextField = new TextField();

    @FXML
    TextField addUserAgeTextField = new TextField();


    @FXML
    TextField readUserIdTextField = new TextField();



    @FXML
    TextField updateUserIdTextField = new TextField();

    @FXML
    TextField updateUserNameTextField = new TextField();

    @FXML
    TextField updateUserTypeAccTextField = new TextField();

    @FXML
    TextField updateUserEmailTextField = new TextField();

    @FXML
    TextField updateUserAgeTextField = new TextField();

    @FXML
    TextField deleteUserIdTextField = new TextField();



    @FXML
    TextField addUserPasswordTextField = new TextField();


    @FXML
    TextField raportNameTXTTextField = new TextField();




    @FXML
    TextField raportNamePDFTextField = new TextField();

    @FXML
    TextField updateUserPasswordTextField = new TextField();


    @FXML
    TableView userTableView = new TableView();

    @FXML
    TableView movieTableView = new TableView();

    @FXML
    TableColumn<Movie,Integer>  movieIdTableColumn  = new TableColumn();

    @FXML
    TableColumn<Movie,String>  movieGenreTableColumn = new TableColumn();

    @FXML
    TableColumn<Movie,String> movieNameTableColumn = new TableColumn();

    @FXML
    TableColumn<Movie,String> movieInfoTableColumn = new TableColumn();

    @FXML
    TableColumn<Movie,String> movieRateTableColumn = new TableColumn();

    @FXML
    TableColumn <Movie,String> movieActorsTableColumn = new TableColumn();




    @FXML
    TableColumn <User,String> userdTableColumn = new TableColumn();

    @FXML
    TableColumn <User,String> userPasswordTableColumn = new TableColumn();

    @FXML
    TableColumn<User,String>  userNameTableColumn = new TableColumn();

    @FXML
    TableColumn <User,Integer> userdTypeAccColumn = new TableColumn();


    @FXML
    TableColumn<User,String>  userEmailTableColumn = new TableColumn();

    @FXML
    TableColumn <User,Integer> userAgeTableColumn = new TableColumn();

    @FXML
    TableColumn  <User,String> userRatingsTableColumn = new TableColumn();



    @FXML
    TableColumn  <Movie,String> movieStatusTableColumn = new TableColumn();

    @FXML
    TableColumn<User, String> userFavouriteMovieTableColumn = new TableColumn<>();

    public String getAddUserPasswordTextField() {
        return addUserPasswordTextField.getText();
    }

    public String getUpdateUserPasswordTextField() {
        return updateUserPasswordTextField.getText();
    }
    public String getAddMovieNameTextField() {
        return addMovieNameTextField.getText();
    }

    public String getAddMovieGenreTextField() {
        return addMovieGenreTextField.getText();
    }

    public String getAddMovieInfoTextField() {
        return addMovieInfoTextField.getText();
    }

    public Integer getAddMovieRateTextField() {
        return Integer.parseInt(addMovieRateTextField.getText());
    }

    public String getAddMovieStatusTextField() {
        return addMovieStatusTextField.getText();
    }

    public String getUpdateMovieStatusTextField() {
        return updateMovieStatusTextField.getText();
    }
    public Integer getAddActorIdMovieTextField() {
        return Integer.parseInt(addActorIdMovieTextField.getText());
    }

    public Integer getAddActorIdActorTextField() {
        return Integer.parseInt(addActorIdActorTextField.getText());
    }

    public Integer getDeleteActorIdMovieTextField() {
        return Integer.parseInt(deleteActorIdMovieTextField.getText());
    }

    public Integer getDeleteActorIdActorTextField() {
        return Integer.parseInt(deleteActorIdActorTextField.getText());
    }

    public Integer getReadMovieIdMovieTextField() {
        return Integer.parseInt(readMovieIdMovieTextField.getText());
    }

    public Integer getUpdateMovieIdMovieTextField() throws IllegalArgumentException{

        return Integer.parseInt(updateMovieIdMovieTextField.getText());
    }

    public String getUpdateMovieNameTextField() {
        return updateMovieNameTextField.getText();
    }

    public String getUpdateMovieGenreTextField() {
        return updateMovieGenreTextField.getText();
    }

    public String getUpdateMovieInfoTextField() {
        return updateMovieInfoTextField.getText();
    }

    public Integer getUpdateMovieRateTextField()throws IllegalArgumentException {
        return Integer.parseInt(updateMovieRateTextField.getText());
    }

    public Integer getDeleteMovieIdTextField() throws IllegalArgumentException{
        return Integer.parseInt(deleteMovieIdTextField.getText());
    }


    public String getAddUserIdTextField() {
        return addUserIdTextField.getText();
    }

    public String getAddUserNameTextField() {
        return addUserNameTextField.getText();
    }

    public Integer getAddUserTypeAccTextField()throws IllegalArgumentException {
        return Integer.parseInt(addUserTypeAccTextField.getText());
    }

    public String getAddUserEmailTextField() {
        return addUserEmailTextField.getText();
    }

    public Integer getAddUserAgeTextField() {
        return Integer.parseInt(addUserAgeTextField.getText());
    }

    public String getReadUserIdTextField() {
        return readUserIdTextField.getText();
    }

    public String getUpdateUserIdTextField() {
        return updateUserIdTextField.getText();
    }

    public String getUpdateUserNameTextField() {
        return updateUserNameTextField.getText();
    }

    public Integer getUpdateUserTypeAccTextField()throws IllegalArgumentException {
        return Integer.parseInt(updateUserTypeAccTextField.getText());
    }

    public String getUpdateUserEmailTextField() {
        return updateUserEmailTextField.getText();
    }

    public Integer getUpdateUserAgeTextField()throws IllegalArgumentException {
        return Integer.parseInt(updateUserAgeTextField.getText());
    }

    public String getDeleteUserIdTextField() {
        return deleteUserIdTextField.getText();
    }

    public TableColumn getMovieIdTableColumn() {
        return movieIdTableColumn;
    }

    public TableColumn getMovieGenreTableColumn() {
        return movieGenreTableColumn;
    }

    public TableColumn getMovieNameTableColumn() {
        return movieNameTableColumn;
    }

    public TableColumn getMovieInfoTableColumn() {
        return movieInfoTableColumn;
    }

    public TableColumn getMovieRateTableColumn() {
        return movieRateTableColumn;
    }

    public TableColumn getMovieActorsTableColumn() {
        return movieActorsTableColumn;
    }

    public TableColumn getUserdTableColumn() {
        return userdTableColumn;
    }

    public TableColumn getUserPasswordTableColumn() {
        return userPasswordTableColumn;
    }

    public TableColumn getUserNameTableColumn() {
        return userNameTableColumn;
    }

    public TableColumn getUserdTypeAccColumn() {
        return userdTypeAccColumn;
    }

    public TableColumn getUserEmailTableColumn() {
        return userEmailTableColumn;
    }

    public TableColumn getUserAgeTableColumn() {
        return userAgeTableColumn;
    }

    public TableColumn getUserRatingsTableColumn() {
        return userRatingsTableColumn;
    }
    public TableColumn<Movie, String> getMovieStatusTableColumn() {
        return movieStatusTableColumn;
    }
    public String getRaportNameTXTTextField() {
        return raportNameTXTTextField.getText();
    }

    public String getRaportNamePDFTextField() {
        return raportNamePDFTextField.getText();
    }


    public TableView getUserTableView() {
        return userTableView;
    }

    public TableView getMovieTableView() {
        return movieTableView;
    }
    public void setUserTableView(TableView userTableView) {
        this.userTableView = userTableView;
    }

    public void setMovieTableView(TableView movieTableView) {
        this.movieTableView = movieTableView;
    }

    @FXML
    public void addMovieButtonClicked(){
        System.out.println("add movie");
        controllerAdmin.addMovie(getAddMovieNameTextField(), getAddMovieGenreTextField(),getAddMovieInfoTextField(), getAddMovieRateTextField(),getAddMovieStatusTextField());
    }

    public void addActorToMovieButtonClicked(){
        System.out.println("add actor to movie");
        controllerAdmin.addActorToMovie(getAddActorIdMovieTextField(), getAddActorIdActorTextField());
    }

    public void deleteActorFromMovieButtonClicked(){
        System.out.println("delete actor from movie");
        controllerAdmin.deleteActorFromMovie(getDeleteActorIdMovieTextField() ,getDeleteActorIdActorTextField());
    }
    public void readMovieButtonClicked(){
        System.out.println("read movie");
        controllerAdmin.readMovie(getReadMovieIdMovieTextField());
    }

    public void updateMovieButtonClicked() throws InterruptedException {
        //try {
            System.out.println("update movie");
        //Platform.runLater(new Runnable()
       // {
           // @Override
           // public void run()
            //{

                controllerAdmin.updateMovie(getUpdateMovieIdMovieTextField(), getUpdateMovieNameTextField(), getUpdateMovieGenreTextField(),
                        getUpdateMovieInfoTextField(), getUpdateMovieRateTextField(),getUpdateMovieStatusTextField());
           // }
       // });

      //  }catch (Exception ex){
       //     JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
       // }
    }
    public void deleteMovieButtonClicked(){
        System.out.println("delete movie");
        controllerAdmin.deleteMovie(getDeleteMovieIdTextField());
    }



    public void addUserButtonClicked(){
        System.out.println("add user");
        controllerAdmin.addUser(getAddUserNameTextField(), getAddUserPasswordTextField(),getAddUserTypeAccTextField(),
                getAddUserIdTextField(), getAddUserEmailTextField(),getAddUserAgeTextField() );
    }

    public void readUserButtonClicked(){
        System.out.println("read user");
        controllerAdmin.readUser(getReadUserIdTextField());
    }
    public void updateUserButtonClicked(){
        System.out.println("update user");
        controllerAdmin.updateUser(getUpdateUserNameTextField(),getUpdateUserPasswordTextField(), getUpdateUserTypeAccTextField(),
                getUpdateUserIdTextField(), getUpdateUserEmailTextField(),getUpdateUserAgeTextField());
    }

    public void deleteUserButtonClicked(){
        System.out.println("delete user");
        controllerAdmin.deleteUser(getDeleteUserIdTextField());
    }
    public void generatePDFButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("generate pdf ");
        controllerAdmin.generatePDF(getRaportNamePDFTextField());
    }

    public void generateTXTButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("generate txt ");
        controllerAdmin.generateTXT(getRaportNameTXTTextField());

    }

    public void showAllMoviesButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("show all movies");
        controllerAdmin.showAllMovies();
        init();
        writeInGui();
        movieTableView.refresh();
    }
    public void showAllUsersButtonClicked()   {
        System.out.println("show all users");
            controllerAdmin.showAllUsers();

        initUser();
        //writeInGuiUser();
       // userTableView.refresh();

    }

    /*private ObservableList<String> actorsStringList = FXCollections.observableArrayList();

    public List<String> getActorsNameAsStringList() {
        ActorRepositoryI actorRepositoryI = new ActorRepository();
        List<Actor> actors = actorRepositoryI.listActor();
        if (!actors.isEmpty()) {
            System.out.println("actors are not empty");
            for (Actor actor : actors) {
                actorsStringList.add(actor.getName());
            }
        }
        //else{
        //     actorsStringList.add("Nu exista actori");
        //}
        return actorsStringList;
    }*/
    public void init(){

        movieIdTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
        movieNameTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        movieGenreTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        movieInfoTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        movieRateTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("rating"));
        movieStatusTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("status"));

        movieActorsTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Movie,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Movie, String> data) {
                //return new ReadOnlyStringWrapper(data.getValue().getActors().toString());
                return new ReadOnlyStringWrapper(data.getValue().getActorsNameAsStringList().toString());

            }
        });
       // dataT.clear();
        //movieTableView.refresh();
        writeInGui();
        movieTableView.refresh();

    }
    @Override
    public void initUser() {
        userdTableColumn.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        userAgeTableColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
        userEmailTableColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        userNameTableColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        userdTypeAccColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("typeAccount"));
        userPasswordTableColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        userRatingsTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> data) {
                //return new ReadOnlyStringWrapper(data.getValue().getActors().toString());
                return new ReadOnlyStringWrapper(data.getValue().getRatingsAsString().toString());
               // return new ReadOnlyMapWrapper(data.getValue().getRatings());

            }
        });

        userFavouriteMovieTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> data) {
                //return new ReadOnlyStringWrapper(data.getValue().getActors().toString());
                return new ReadOnlyStringWrapper(data.getValue().getFavouriteAsString().toString());
                // return new ReadOnlyMapWrapper(data.getValue().getRatings());

            }
        });

        //dataUserT.clear();
        //movieTableView.refresh();
        writeInGuiUser();
        userTableView.refresh();
    }
    public void writeInGui() {

            movieTableView.setItems(dataT);
            //movieTableView.refresh();

    }
    public void writeInGuiUser() {

        userTableView.setItems(dataUserT);


    }
    public void setReadData(Movie movie){
            dataT.clear();
            movieTableView.refresh();
            dataT.add(movie);


    }
    public void setReadDataUser(User user){
        dataUserT.clear();
        userTableView.refresh();
        dataUserT.add(user);
    }


    @Override
    public void setDataUserT(ObservableList<User> dataUserT) {
        this.dataUserT.clear();
        this.dataUserT = dataUserT;
    }

    public void setDataT(ObservableList<Movie> dataT){
        this.dataT.clear();
        this.dataT = dataT;
        //init();
    }

}
