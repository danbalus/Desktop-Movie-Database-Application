package client.model.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "actor")
public class Actor implements EntityApp, Serializable {

    @Id
    @Column(name = "id_actor", unique = true)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idActor = 0;

    @Column(name = "name")
    private String name = "";


    @Column(name = "info")
    private String info = "";

    //@ManyToOne
    //@JoinColumn(name = "movie")
    //@NotNull
    // private Movie movie;

    //@Fetch(FetchMode.SELECT)
    //@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "actors", cascade = {CascadeType.ALL})
    private List<Movie> movies = new ArrayList<Movie>();


    public Actor(int idActor, String name, String info, List<Movie> movies) {
        this.idActor = idActor;
        this.name = name;
        this.info = info;
        this.movies = movies;
    }

    public Actor(String name, String info, List<Movie> movies) {
        this.idActor = idActor;
        this.name = name;
        this.info = info;
        this.movies = movies;
    }

    public Actor(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public Actor(int idActor, String name, String info) {
        this.idActor = idActor;
        this.name = name;
        this.info = info;
    }

    //public Actor(String idActor, String name, String info ) {//BAD CONSTRUCTOR, ONLY FOR TESTE VALIDATOR
    //   this.idActor = idActor;
    //     this.name = name;
    //    this.info = info;
    // }
    public Actor() {
    }

    public int getId() {
        return idActor;
    }

    public void setId(int idActor) {
        this.idActor = idActor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (idActor != actor.idActor) return false;
        if (name != null ? !name.equals(actor.name) : actor.name != null) return false;
        if (info != null ? !info.equals(actor.info) : actor.info != null) return false;
        return movies != null ? movies.equals(actor.movies) : actor.movies == null;
    }

    @Override
    public int hashCode() {
        int result = idActor;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (movies != null ? movies.hashCode() : 0);
        return result;
    }

    public String toString() {
        return ("|name|: " + name +
                "    |info|: " + "\t" + info +
    //NEVER DELETE this COMMENT          // "    |movie|: " + "\t" + Arrays.asList(movies) +
                // NEVER DELETE this COMMENT            //"movie: " + movies +
                "    |id|: " + "\t" + idActor
        );
    }
}
