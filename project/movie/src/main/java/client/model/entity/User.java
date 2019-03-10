package client.model.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.ElementCollection;


@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")
        })
public class User implements Serializable {

    //todo:map<integer, intetger> CARE ARE IN EA ID UL UNIC AL FILMULUI SI RATINGUL DAT DE EL
    @Id
    @Column(name = "id")
    private String id;//nickname

    @Column(name = "password")
    private String password;

    @Column(name = "name"/*, unique = true*/)
    private String name;


    @Column(name = "typeAccount")
    private int typeAccount;    // 0--> regular user  1--> VIP user


    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @ElementCollection
    @Column(name = "ratings")
    private Map<Integer, Integer> ratings;// = new HashMap<Integer,Integer>();

    @ElementCollection
    @Column(name = "favourite_movie" )
    private List<Movie> favouriteMovieList;//


    @ElementCollection
    @Column(name = "watched")
    private List<Integer> watched;
    //@NotNull
    //@ElementCollection
    //@Column(name = "watch_list"/*,insertable = false*/)
   // @OneToMany(mappedBy="userW", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Movie> watchList;


    //@OneToMany(mappedBy="reviews", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Movie> reviewList;


    //String name, String password, int typeAcc, String id, String email, int age
    public User(String name, String password, int typeAccount, String id, String email, int age) {
        this.name = name;
        this.password = password;
        this.typeAccount = typeAccount;
        this.id = id;
        this.email = email;
        this.age = age;
        ratings = new HashMap<Integer,Integer>();
        favouriteMovieList = new ArrayList<>();
        //watchList = new ArrayList<>();
       // ratings.put(999999,999999);
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        ratings = new HashMap<Integer,Integer>();
        favouriteMovieList = new ArrayList<>();
        //watchList = new ArrayList<>();
        //ratings.put(999999,999999);
    }

    public User(String name, String password, int typeAccount, String id, String email, int age,
                Map<Integer, Integer> ratings) {
        this.name = name;
        this.password = password;
        this.typeAccount = typeAccount;
        this.id = id;
        this.email = email;
        this.age = age;
        this.ratings = ratings;
        favouriteMovieList = new ArrayList<>();
       //watchList = new ArrayList<>();

       // ratings.put(999999,999999);
    }
    public User(String name, String password, int typeAccount, String id, String email, int age,
                Map<Integer, Integer> ratings, List<Movie> favouriteMovieList) {
        this.name = name;
        this.password = password;
        this.typeAccount = typeAccount;
        this.id = id;
        this.email = email;
        this.age = age;
        this.ratings = ratings;
        this.favouriteMovieList = favouriteMovieList;
        //watchList = new ArrayList<>();
        // ratings.put(999999,999999);
    }
    public User(String name, String password, int typeAccount, String id, String email, int age,
                Map<Integer, Integer> ratings, List<Movie> favouriteMovieList, List<Movie> watchList) {
        this.name = name;
        this.password = password;
        this.typeAccount = typeAccount;
        this.id = id;
        this.email = email;
        this.age = age;
        this.ratings = ratings;
        this.favouriteMovieList = favouriteMovieList;
       /// this.watchList = watchList;
        // ratings.put(999999,999999);
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(int typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<Integer, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(Map<Integer, Integer> ratings) {
        this.ratings = ratings;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Movie> getFavouriteMovieList() {
        return favouriteMovieList;
    }

    public void setFavouriteMovieList(List<Movie> favouriteMovieList) {
        this.favouriteMovieList = favouriteMovieList;
    }

    public List<Integer> getWatched() {
        return watched;
    }

    public void setWatched(List<Integer> watched) {
        this.watched = watched;
    }

    //public List<Movie> getReviewList() {
    //    return reviewList;
   // }

    //public void setReviewList(List<Movie> reviewList) {
    //    this.reviewList = reviewList;
   // }
    //public List<Movie> getWatchList() {
   //     return watchList;
  //  }

    //public void setWatchList(List<Movie> watchList) {
   //     this.watchList = watchList;
  // }
    public synchronized List<String> getRatingsAsString() {
        List<String> list = new ArrayList<>();
        //ratings.put(999999,999999);
        if (!ratings.isEmpty()/*size()!=0*/) {
            System.out.println("ratings are not empty");
            for (Map.Entry<Integer, Integer> pair : ratings.entrySet()) {
                list.add(("\nid: " + Integer.toString(pair.getKey()) + " " + ("rate: " + Integer.toString(pair.getValue()))) + " ");
            }
        }

        return list;
    }

    public synchronized List<String>  getFavouriteAsString() {
        List<String> favouriteStringList = new ArrayList<>();
        if (!favouriteMovieList.isEmpty()) {
            System.out.println("favourite are not empty");
            for (Movie movie : favouriteMovieList) {
                favouriteStringList.add("\n" + movie.getName() );
            }
        }
        return favouriteStringList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (typeAccount != user.typeAccount) return false;
        if (age != user.age) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (ratings != null ? !ratings.equals(user.ratings) : user.ratings != null) return false;
        if (favouriteMovieList != null ? !favouriteMovieList.equals(user.favouriteMovieList) : user.favouriteMovieList != null)
            return false;
        return watched != null ? watched.equals(user.watched) : user.watched == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + typeAccount;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0);
        result = 31 * result + (favouriteMovieList != null ? favouriteMovieList.hashCode() : 0);
        result = 31 * result + (watched != null ? watched.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", typeAccount=" + typeAccount +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", ratings=" + ratings +
                ", favouriteMovieList=" + favouriteMovieList +
                ", watched=" + watched +
                '}';
    }
/*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (typeAccount != user.typeAccount) return false;
        if (age != user.age) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (ratings != null ? !ratings.equals(user.ratings) : user.ratings != null) return false;
        if (favouriteMovieList != null ? !favouriteMovieList.equals(user.favouriteMovieList) : user.favouriteMovieList != null)
            return false;
        if (watched != null ? !watched.equals(user.watched) : user.watched != null) return false;
        return reviewList != null ? reviewList.equals(user.reviewList) : user.reviewList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + typeAccount;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0);
        result = 31 * result + (favouriteMovieList != null ? favouriteMovieList.hashCode() : 0);
        result = 31 * result + (watched != null ? watched.hashCode() : 0);
        result = 31 * result + (reviewList != null ? reviewList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", typeAccount=" + typeAccount +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", ratings=" + ratings +
                ", favouriteMovieList=" + favouriteMovieList +
                ", watched=" + watched +
                ", reviewList=" + reviewList +
                '}';
    }*/
/*

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (typeAccount != user.typeAccount) return false;
        if (age != user.age) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (ratings != null ? !ratings.equals(user.ratings) : user.ratings != null) return false;
        return favouriteMovieList != null ? favouriteMovieList.equals(user.favouriteMovieList) : user.favouriteMovieList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + typeAccount;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0);
        result = 31 * result + (favouriteMovieList != null ? favouriteMovieList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", typeAccount=" + typeAccount +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", ratings=" + ratings +
                ", favouriteMovieList=" + favouriteMovieList +
                '}';
    }
*/

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (typeAccount != user.typeAccount) return false;
        if (age != user.age) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (ratings != null ? !ratings.equals(user.ratings) : user.ratings != null) return false;
        if (favouriteMovieList != null ? !favouriteMovieList.equals(user.favouriteMovieList) : user.favouriteMovieList != null)
            return false;
        return watchList != null ? watchList.equals(user.watchList) : user.watchList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + typeAccount;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0);
        result = 31 * result + (favouriteMovieList != null ? favouriteMovieList.hashCode() : 0);
        result = 31 * result + (watchList != null ? watchList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", typeAccount=" + typeAccount +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", ratings=" + ratings +
                ", favouriteMovieList=" + favouriteMovieList +
                ", watchList=" + watchList +
                '}';
    }*/
}