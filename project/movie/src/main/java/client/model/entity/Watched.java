package client.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "watched",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id_watched")
        })
public class Watched {
    @Id
    @Column(name = "id_watched", unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer idWatched= 0;


    @Column(name = "id_movie", unique = true, nullable = false)
    private Integer idMovie= 0;

    @ManyToOne
   // @JoinColumn(nullable=false)
    private User userW;
}

