package client.model.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;




@Entity
@Table(name = "movie",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id_movie")
        })

public class Movie implements EntityApp, Serializable{

    @Transient //ca sa ignore lista asta
     private List<String> actorsStringList = new ArrayList<>();
    //private ObservableList<String> actorsStringList = FXCollections.observableArrayList();

    public Movie(int idMovie, String name, String genre, String title, List<Actor> actors, List<Director> directors, int rating) {
        this.idMovie = idMovie;
        this.name = name;
        this.genre = genre;
        this.title = title;
        this.actors = actors;
        this.directors = directors;
        this.rating = rating;
        //this.reviews = new HashMap<>();
    }

    public Movie(int idMovie, String name, String genre, String title, int rating) {
        this.idMovie = idMovie;
        this.name = name;
        this.genre = genre;
        this.title = title;
        this.rating = rating;
       // this.reviews = new HashMap<>();
    }
    public Movie(int idMovie, String name, String genre, String title, int rating, String status) {
        this.idMovie = idMovie;
        this.name = name;
        this.genre = genre;
        this.title = title;
        this.rating = rating;
        this.status = status;
        //this.reviews = new HashMap<>();
    }

    public Movie(String name, String genre, String title, int rating) {
        this.name = name;
        this.genre = genre;
        this.title = title;
        this.rating = rating;
        //this.reviews = new HashMap<>();
    }
    public Movie(String name, String genre, String title, int rating, String status) {
        this.name = name;
        this.genre = genre;
        this.title = title;
        this.rating = rating;
        this.status = status;
        //this.reviews = new HashMap<>();
    }

    public Movie(int idMovie, String name, String genre, String title, int rating, List<Actor> actors) {
        this.idMovie = idMovie;
        this.name = name;
        this.genre = genre;
        this.title = title;
        this.rating = rating;
        this.actors = actors;
    }

    public Movie(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public Movie(String name, String genre, String title, int rating, String status, List<Review> reviews) {
        this.name = name;
        this.genre = genre;
        this.title = title;
        this.rating = rating;
        this.status = status;
        this.reviews = reviews;
        //this.reviews = new HashMap<>();
    }

    public Movie() { }


    @Id
    @Column(name = "id_movie", unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer idMovie = 0;

    @Column(name = "name")
    private String name = "";

    @Column(name = "genre")
    private String genre = "";

    @Column(name = "title")//INFORMATION
    private String title = "";

    @Column(name = "rating")
    private int rating = 0;



    @Column(name = "status")
    private String status;

    //@Fetch(FetchMode.SELECT)
    //@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    // @OrderBy("id_movie")

    @JoinTable(
            name = "filme_actori",
            joinColumns = {@JoinColumn(name = "id_movie")},
            inverseJoinColumns = {@JoinColumn(name = "id_actor")}
    )
    //@SortNatural
    private List<Actor> actors;


    //@Fetch(FetchMode.SELECT)
    //@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    //@OrderBy("id_movie")
    @JoinTable(
            name = "filme_director",
            joinColumns = {@JoinColumn(name = "id_movie")},
            inverseJoinColumns = {@JoinColumn(name = "id_director")}
    )
    //@SortNatural
    private List<Director> directors;



    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY)
    private List<Review> reviews;
   // @JoinTable(
   //         name = "filme_review",
    //        joinColumns = {@JoinColumn(name = "id_movie")},
   //         inverseJoinColumns = {@JoinColumn(name = "id_review")}
    //)


    //@ElementCollection
    //@Column(name = "reviews")
    //private Map<String, String> reviews;//id user, review text
   // private  List<Review> reviews;
    //@ManyToOne
    //@JoinColumn(name = "actor")
    //@NotNull
    //private Actor actors;

    //@Column(name = "actors")
    //private  String actors;

    // @GeneratedValue(generator = "incrementator")
    //@GenericGenerator(name = "incrementator", strategy = "increment")


    public int getId() {
        return idMovie;
    }

    public void setId(int idMovie) {
        this.idMovie = idMovie;
    }

    // genre, title, actors


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List< Review> reviews) {
        this.reviews = reviews;
    }
    //public void addReviews(Review review) {
     //   this.reviews.add(review);
    //}
    public List<String> getActorsNameAsStringList() {
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
    }

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (rating != movie.rating) return false;
        if (actorsStringList != null ? !actorsStringList.equals(movie.actorsStringList) : movie.actorsStringList != null)
            return false;
        if (idMovie != null ? !idMovie.equals(movie.idMovie) : movie.idMovie != null) return false;
        if (name != null ? !name.equals(movie.name) : movie.name != null) return false;
        if (genre != null ? !genre.equals(movie.genre) : movie.genre != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (status != null ? !status.equals(movie.status) : movie.status != null) return false;
        if (actors != null ? !actors.equals(movie.actors) : movie.actors != null) return false;
        return directors != null ? directors.equals(movie.directors) : movie.directors == null;
    }

    @Override
    public int hashCode() {
        int result = actorsStringList != null ? actorsStringList.hashCode() : 0;
        result = 31 * result + (idMovie != null ? idMovie.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        result = 31 * result + (directors != null ? directors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "actorsStringList=" + actorsStringList +
                ", idMovie=" + idMovie +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", status='" + status + '\'' +
                ", actors=" + actors +
                ", directors=" + directors +
                '}';
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (rating != movie.rating) return false;
        if (actorsStringList != null ? !actorsStringList.equals(movie.actorsStringList) : movie.actorsStringList != null)
            return false;
        if (idMovie != null ? !idMovie.equals(movie.idMovie) : movie.idMovie != null) return false;
        if (name != null ? !name.equals(movie.name) : movie.name != null) return false;
        if (genre != null ? !genre.equals(movie.genre) : movie.genre != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (status != null ? !status.equals(movie.status) : movie.status != null) return false;
        if (actors != null ? !actors.equals(movie.actors) : movie.actors != null) return false;
        if (directors != null ? !directors.equals(movie.directors) : movie.directors != null) return false;
        return reviews != null ? reviews.equals(movie.reviews) : movie.reviews == null;
    }

    @Override
    public int hashCode() {
        int result = actorsStringList != null ? actorsStringList.hashCode() : 0;
        result = 31 * result + (idMovie != null ? idMovie.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        result = 31 * result + (directors != null ? directors.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "actorsStringList=" + actorsStringList +
                ", idMovie=" + idMovie +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", status='" + status + '\'' +
                ", actors=" + actors +
                ", directors=" + directors +
                ", reviews=" + reviews +
                '}';
    }
}
