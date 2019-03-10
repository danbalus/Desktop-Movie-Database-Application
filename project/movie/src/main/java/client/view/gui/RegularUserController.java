package client.view.gui;

import client.controller.ControllerUser;
import client.model.entity.Review;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import client.model.entity.Movie;
import client.model.entity.User;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;


public class RegularUserController implements RegularUserControllerI {
    private ControllerUser controllerUser;
    private boolean genre, director, actor, rating;
    private ObservableList<Movie> dataSearchT;
    private ObservableList<User> dataRateT;
    private ObservableList <Movie> dataFavouriteT;
    private ObservableList  <Movie> dataWhatchedT;
    private ObservableList <Movie> dataSuggestedT;

    private String idLoggedUser;
    private ObservableList <Review> dataReviewT;


    public RegularUserController() {
        controllerUser = new ControllerUser(this);
        dataSearchT = FXCollections.observableArrayList();
        dataRateT = FXCollections.observableArrayList();
        dataFavouriteT = FXCollections.observableArrayList();
        dataWhatchedT = FXCollections.observableArrayList();
        dataSuggestedT = FXCollections.observableArrayList();
        dataReviewT = FXCollections.observableArrayList();
    }


    @FXML
    TableColumn<Movie, String> movieGenreTableColumn = new TableColumn();

    @FXML
    TableColumn<Movie, String> movieNameTableColumn = new TableColumn();

    @FXML
    TableColumn<Movie, String> movieInfoTableColumn = new TableColumn();

    @FXML
    TableColumn<Movie, Integer> movieRateTableColumn = new TableColumn();

    @FXML
    TableColumn<Movie, String> movieActorsTableColumn = new TableColumn();

    @FXML
    TableColumn<Movie, String> favouriteNameTableColumn = new TableColumn();

    @FXML
    TableColumn<Review, String> reviewNameTableColumn = new TableColumn();

    @FXML
    TableColumn<Review, String> reviewReviewTableColumn = new TableColumn();


    @FXML
    TableColumn<Movie, String> suggestionsMovieNameTableColumn1 = new TableColumn();

    @FXML
    TableColumn<Movie, String> suggestionsMovieGenreTableColumn1 = new TableColumn();

    @FXML
    TableColumn<Movie, String> suggestionsMovieInfoTableColumn1 = new TableColumn();

    @FXML
    TableColumn<Movie, Integer> suggestionsMovieRateTableColumn1 = new TableColumn();

    @FXML
    TableColumn<Movie, String> suggestionsMovieActorsTableColumn1 = new TableColumn();


    @FXML
    TableColumn<Movie, String> whatchedNameTableColumn11 = new TableColumn();

    @FXML
    TableColumn<Review, String> reviewUserNameTableColumn = new TableColumn();



    @FXML
    TableView suggeestionsTableView1 = new TableView();

    @FXML
    TableView whatchedTableView1 = new TableView();

    @FXML
    TableView rateTableView = new TableView();

    @FXML
    TableView movieTableView = new TableView();

    @FXML
    TableView favouriteTableView = new TableView();

    @FXML
    TableView reviewTableView = new TableView();

    @FXML
    TableColumn<Movie, String> rateNameTableColumn = new TableColumn();

    @FXML
    TableColumn<Review, Integer> reviewIdTableColumn = new TableColumn();

 @FXML
    TableColumn<Review,Integer> reviewUpTableColumn = new TableColumn();
    @FXML
    TableColumn<Review,Integer> reviewDownTableColumn = new TableColumn();

    @FXML
    TableColumn<Movie, Integer> rateRateeTableColumn = new TableColumn();
    @FXML
    TextField searchMovieByTextField = new TextField();


    @FXML
    TextField rateNameMovieTextField = new TextField();

    @FXML
    TextField rateRateMovieTextField = new TextField();

    @FXML
    TextField addNameFavouriteMovieTextField = new TextField();

    @FXML
    TextField removeNameFavouriteMovieTextField = new TextField();
    @FXML
    TextField addNameReviewMovieTextField1 = new TextField();

    @FXML
    TextField reviewMovieTextField1 = new TextField();


    @FXML
    TextField addNameWhatchedMovieTextField11 = new TextField();

    @FXML
    TextField removeNameWhatchedMovieTextField11 = new TextField();


    @FXML
    TextField idLikeReviewMovieTextField = new TextField();


    public TableColumn<Movie, String> getMovieGenreTableColumn() {
        return movieGenreTableColumn;
    }

    public TableColumn<Movie, String> getMovieNameTableColumn() {
        return movieNameTableColumn;
    }

    public TableColumn<Movie, String> getMovieInfoTableColumn() {
        return movieInfoTableColumn;
    }

    public TableColumn<Movie, Integer> getMovieRateTableColumn() {
        return movieRateTableColumn;
    }

    public TableColumn<Movie, String> getMovieActorsTableColumn() {
        return movieActorsTableColumn;
    }

    public TableView getRateTableView() {
        return rateTableView;
    }

    public TableView getMovieTableView() {
        return movieTableView;
    }

    public TableColumn<Movie, String> getRateNameTableColumn() {
        return rateNameTableColumn;
    }



    public TableColumn<Movie, Integer> getRateRateeTableColumn() {
        return rateRateeTableColumn;
    }

    public String getSearchMovieTextField() {
        return searchMovieByTextField.getText();
    }

    public String getRateNameMovieTextField() {
        return rateNameMovieTextField.getText();
    }

    public String getRateRateMovieTextField() {
        return rateRateMovieTextField.getText();
    }

    public TableColumn<Movie, String> getFavouriteNameTableColumn() {
        return favouriteNameTableColumn;
    }

    public TableView getFavouriteTableView() {
        return favouriteTableView;
    }

    public String getSearchMovieByTextField() {
        return searchMovieByTextField.getText();
    }

    public String getAddNameFavouriteMovieTextField() {
        return addNameFavouriteMovieTextField.getText();
    }

    public String getRemoveNameFavouriteMovieTextField() {
        return removeNameFavouriteMovieTextField.getText();
    }

    public String getAddNameReviewMovieTextField1() {
        return addNameReviewMovieTextField1.getText();
    }

    public String getRemoveNameReviewMovieTextField1() {
        return reviewMovieTextField1.getText();
    }
    public void actorRadioButtonSelected() {
        System.out.println("actor radio button ");
        genre = false;
        director = false;
        actor = true;
        rating = false;
        //controllerUser.searchByActor();
        //controllerUser.chooseSearchMethode(genre, director, actor, rating);
    }

    public void directorRadioButtonSelected() {
        System.out.println("director radio button ");
        genre = false;
        director = true;
        actor = false;
        rating = false;
        //controllerUser.searchByDirector();
        //controllerUser.chooseSearchMethode(genre, director, actor, rating);
    }

    public void genreRadioButtonSelected() {
        System.out.println("genre radio button ");
        genre = true;
        director = false;
        actor = false;
        rating = false;
        //controllerUser.searchByGenre();
        //controllerUser.chooseSearchMethode(genre, director, actor, rating);
    }

    public void rateRadioButtonSelected() {
        System.out.println("rate radio button ");
        genre = false;
        director = false;
        actor = false;
        rating = true;
        //controllerUser.searchByRate();
        //controllerUser.chooseSearchMethode(genre, director, actor, rating);
    }


    //public void setIdLoggedUser(String idLoggedUser) {
    //   System.out.println("\n\n\n\n"+ "idLoggedUser = " + idLoggedUser);
    //    this.idLoggedUser = idLoggedUser;

    // }

    // public String getIdLoggedUser() {
    //      return idLoggedUser;
    //  }
    public void searchMovieByButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("search movie ");
        controllerUser.chooseSearchMethode(genre, director, actor, rating);
        controllerUser.showAllSearch();
        initSearch();
    }

    public void seeAllRatingsButtonClicked()  {
        System.out.println("show all rate movie ");
        try {
            controllerUser.showAllRate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        initRate();
    }

    public void rateMovieButtonClicked() {
        System.out.println("rate movie ");
        controllerUser.rateMovie();
    }


    public void addFavouriteMovieButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("add fav  movie ");
        controllerUser.addFavouriteMovie(addNameFavouriteMovieTextField.getText());
    }


    public void removeFavouriteMovieButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("remove fav movie ");
        controllerUser.removeFavouriteMovie(removeNameFavouriteMovieTextField.getText());
    }

    public void seeAllFavouriteMoviesButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("show all favourite movie ");
        controllerUser.showAllFavouriteMovies();
        initFavourite();
    }

    public void addReviewMovieButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("add review movie ");
        controllerUser.addReviewMovie(reviewMovieTextField1.getText(), addNameReviewMovieTextField1.getText());
    }
    public void seeAllReviesMoviesButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("show all reviews movie ");
        controllerUser.showAllReviewsMovie();
        initReviews();
    }

    public void seeAllSuggestedMoviesButtonClicked(){
        System.out.println("show all suggested movie ");
        controllerUser.showAllSuggestionsMovie();
        initSuggested();
    }


    public void addWhatchedMovieButtonClicked(){
        System.out.println("add  whatched movie ");
        controllerUser.addWhatchedMovie(addNameWhatchedMovieTextField11.getText());
    }
    public void removeWhatchedMovieButtonClicked(){
        System.out.println("remove whatched movie ");
        controllerUser.removeWhatchedMovie(removeNameWhatchedMovieTextField11.getText());

    }
    public void seeAllWhatchedMoviesButtonClicked() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("show all whatched movie ");
        controllerUser.seeAllWhatchedMovies();
        initWhatched();
    }
    public void likeClick(){
        System.out.println("like");

        controllerUser.likeReview(idLikeReviewMovieTextField.getText());

    }
    public void dislikeClick(){
        System.out.println("dislike");

        controllerUser.unlikeReview(idLikeReviewMovieTextField.getText());
    }

    public void initReviews() {
        reviewUpTableColumn.setCellValueFactory(new PropertyValueFactory<Review, Integer>("likeUp"));
        reviewDownTableColumn.setCellValueFactory(new PropertyValueFactory<Review, Integer>("likeUDown"));
        reviewIdTableColumn.setCellValueFactory(new PropertyValueFactory<Review, Integer>("idReview"));
        //reviewNameTableColumn.setCellValueFactory(new PropertyValueFactory<Review, Integer>("movie"));
        reviewReviewTableColumn.setCellValueFactory(new PropertyValueFactory<Review, String>("reviewText"));
        reviewUserNameTableColumn.setCellValueFactory(new PropertyValueFactory<Review, String>("userReviewId"));
        reviewNameTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Review, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Review, String> data) {
                //return new ReadOnlyStringWrapper(data.getValue().getActors().toString());
                return new ReadOnlyStringWrapper(data.getValue().getMovie().getName());
            }
        });
            writeInGuiReview();
            reviewTableView.refresh();
    }


    public void initWhatched() {
        whatchedNameTableColumn11.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        writeInGuiWhatched();
        whatchedTableView1.refresh();
    }

    public void initFavourite() {
        favouriteNameTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        writeInGuiFavourite();
        favouriteTableView.refresh();
    }


    public void initSuggested() {
        suggestionsMovieNameTableColumn1.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        suggestionsMovieGenreTableColumn1.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        suggestionsMovieInfoTableColumn1.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        suggestionsMovieRateTableColumn1.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("rating"));
        suggestionsMovieActorsTableColumn1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Movie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Movie, String> data) {
                //return new ReadOnlyStringWrapper(data.getValue().getActors().toString());
                return new ReadOnlyStringWrapper(data.getValue().getActorsNameAsStringList().toString());
            }
        });
        writeInGuiSuggested();
        suggeestionsTableView1.refresh();
    }

    public void initSearch() {
        //movieIdTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
        movieNameTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        movieGenreTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        movieInfoTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        movieRateTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("rating"));
        movieActorsTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Movie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Movie, String> data) {
                //return new ReadOnlyStringWrapper(data.getValue().getActors().toString());
                return new ReadOnlyStringWrapper(data.getValue().getActorsNameAsStringList().toString());
            }
        });
        writeInGuiSearch();
        movieTableView.refresh();
    }


    @Override
    public void initRate() {
        rateNameTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        rateRateeTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("rating"));
        writeInGuiRate();
        rateTableView.refresh();
    }

    public void writeInGuiReview() {
        reviewTableView.setItems(dataReviewT);
    }

    public void writeInGuiSuggested() {
        suggeestionsTableView1.setItems(dataSuggestedT);
    }

    public void writeInGuiWhatched() {
        whatchedTableView1.setItems(dataWhatchedT);
    }


    public void writeInGuiRate() {
        rateTableView.setItems(dataRateT);
    }

    @Override
    public void writeInGuiSearch() {
        movieTableView.setItems(dataSearchT);
    }

    @Override
    public void writeInGuiFavourite() {
        favouriteTableView.setItems(dataFavouriteT);
    }

    @Override
    public void setDataReviewT(ObservableList<Review> dataReviewT) {
        this.dataReviewT.clear();
        this.dataReviewT = dataReviewT;
    }
    @Override
    public void setDataSuggestedT(ObservableList<Movie> dataSuggestedT) {
        this.dataSuggestedT.clear();
        this.dataSuggestedT = dataSuggestedT;
    }

    @Override
    public void setDataFavouriteT(ObservableList<Movie> dataFavouriteT) {
        this.dataFavouriteT.clear();
        this.dataFavouriteT = dataFavouriteT;
    }

    @Override
    public void setDataWhatchedT(ObservableList<Movie> dataWhatchedT) {
        this.dataWhatchedT.clear();
        this.dataWhatchedT = dataWhatchedT;
    }

    @Override
    public void setDataSearchT(ObservableList<Movie> dataSearchT) {
        this.dataSearchT.clear();
        this.dataSearchT = dataSearchT;
    }

    @Override
    public void setDataRateT(ObservableList dataRateT) {
        this.dataRateT.clear();
        this.dataRateT = dataRateT;
        //init();
    }


}
