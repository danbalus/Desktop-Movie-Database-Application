package client.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "director",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id_director")
        })
public class Director implements EntityApp, Serializable {
    @Id
    @Column(name = "id_director", unique = true)
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int idDirector=0;

    @Column(name = "name")
    private String name="";


    @Column(name = "info")
    private String info="";

    @ManyToMany(mappedBy = "directors", cascade = { CascadeType.ALL})
    private List<Movie> movies = new ArrayList<Movie>();

    public Director(int idDirector, String name, String info, List<Movie> movie) {
        this.idDirector = idDirector;
        this.name = name;
        this.info = info;
        this.movies = movies;
    }
    public Director(int idDirector, String name, String info) {
        this.idDirector = idDirector;
        this.name = name;
        this.info = info;
    }
    public Director(){}

    public int getId() {
        return idDirector;
    }

    public void setId(int idDirector) {
        this.idDirector = idDirector;
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

    public List<Movie> getMovie() {
        return movies;
    }

    public void setMovie(List<Movie> movies) {
        this.movies = movies;
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

        Director director = (Director) o;

        if (idDirector != director.idDirector) return false;
        if (name != null ? !name.equals(director.name) : director.name != null) return false;
        if (info != null ? !info.equals(director.info) : director.info != null) return false;
        return movies != null ? movies.equals(director.movies) : director.movies == null;
    }

    @Override
    public int hashCode() {
        int result = idDirector;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (movies != null ? movies.hashCode() : 0);
        return result;
    }
    public String toString(){
        return ("|name|: " + name +
                "    |info|: "  + "\t" + info +
  //              "    |movie|: " + "\t" + Arrays.toString(movies.toArray())+
                "    |id|: " +  "\t"+  idDirector
                // + "    |accounts|: " + "\t" + Arrays.toString(accounts.toArray())

        );
    }
}
