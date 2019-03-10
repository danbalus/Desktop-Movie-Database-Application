package client.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id_review")
        })
public class Review implements Serializable {

    @Id
    @Column(name = "id_review", unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer idReview = 0;

    @Column(name = "review_Text")
    private String reviewText;


    public String getUserReviewId() {
        return userReviewId;
    }

    public void setUserReviewId(String userReviewId) {
        this.userReviewId = userReviewId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Column(name = "user_review_id")
    private String userReviewId;

    //@ManyToOne
    //@JoinColumn(nullable=false)
    //private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_movie" )
    private Movie movie;



    @Column(name = "like_up", nullable = false)
    private int likeUp = 0;

    @Column(name = "like_down", nullable = false)
    private int likeUDown = 0;


    public Integer getIdReview() {
        return idReview;
    }

    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
    }

    public void setReview(String reviewText) {
        this.reviewText = reviewText;
    }
    public int getLikeUp() {
        return likeUp;
    }

    public void setLikeUp(int likeUp) {
        this.likeUp = likeUp;
    }

    public int getLikeUDown() {
        return likeUDown;
    }

    public void setLikeUDown(int likeUDown) {
        this.likeUDown = likeUDown;
    }

    @Override
    public String toString() {
        return "Review{" +
                "idReview=" + idReview +
                ", reviewText='" + reviewText + '\'' +
                ", userReviewId='" + userReviewId + '\'' +
                ", likeUp=" + likeUp +
                ", likeUDown=" + likeUDown +
                '}';
    }
// public User getUser() {
    //    return user;
   // }

   // public void setUser(User user) {
    //    this.user = user;
    //}

}
