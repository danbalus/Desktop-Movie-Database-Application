package client.view.gui;

import client.model.entity.Review;
import client.model.entity.User;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import client.model.entity.Movie;

import java.io.IOException;

public interface RegularUserControllerI {
    public void searchMovieByButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;

    public void rateMovieButtonClicked();


    public void actorRadioButtonSelected();

    public void directorRadioButtonSelected();

    public void genreRadioButtonSelected();

    public void rateRadioButtonSelected();

    public TableColumn<Movie, String> getMovieGenreTableColumn();

    public TableColumn<Movie, String> getMovieNameTableColumn();

    public TableColumn<Movie, String> getMovieInfoTableColumn();

    public TableColumn<Movie, Integer> getMovieRateTableColumn();

    public TableColumn<Movie, String> getMovieActorsTableColumn();

    public TableView getRateTableView();

    public TableView getMovieTableView();

    public TableColumn<Movie, String> getRateNameTableColumn();

    // public TableColumn<Movie, String> getRateIdeTableColumn() ;

    public TableColumn<Movie, Integer> getRateRateeTableColumn();

    public String getRateNameMovieTextField();

    public String getRateRateMovieTextField();

    public String getSearchMovieTextField();

    public TableColumn<Movie, String> getFavouriteNameTableColumn() ;

    public TableView getFavouriteTableView() ;

    public String getSearchMovieByTextField() ;

    public String getAddNameFavouriteMovieTextField();

    public String getRemoveNameFavouriteMovieTextField();

    // public void setIdLoggedUser(String idLoggedUser);

    // public String getIdLoggedUser() ;

    public void initSearch();

    public void initRate();

    public void writeInGuiRate();

    public void writeInGuiSearch();

    public void setDataSearchT(ObservableList<Movie> dataSearchT);

    public void setDataRateT(ObservableList dataRateT);

    public void seeAllRatingsButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;

    public void writeInGuiFavourite() ;

    public void setDataFavouriteT(ObservableList<Movie> dataFavouriteT);

    public void addFavouriteMovieButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;


    public void removeFavouriteMovieButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;

    public void seeAllFavouriteMoviesButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;
    public void initFavourite();
    public String getAddNameReviewMovieTextField1() ;

    public String getRemoveNameReviewMovieTextField1() ;
    public void addReviewMovieButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;
    public void seeAllReviesMoviesButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;
    public void seeAllSuggestedMoviesButtonClicked();
    public void addWhatchedMovieButtonClicked();
    public void removeWhatchedMovieButtonClicked();
    public void seeAllWhatchedMoviesButtonClicked() throws InterruptedException, IOException, ClassNotFoundException;

    public void setDataWhatchedT(ObservableList<Movie> dataWhatchedT) ;
    public void writeInGuiWhatched();


    public void initWhatched() ;
    public void setDataSuggestedT(ObservableList<Movie> dataSuggestedT);
    public void writeInGuiSuggested() ;

    public void initSuggested();
    public void initReviews();
    public void setDataReviewT(ObservableList<Review> dataReviewT);
    public void writeInGuiReview();

}

