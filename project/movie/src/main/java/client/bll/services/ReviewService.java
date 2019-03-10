package client.bll.services;

import client.ClientConnection;
import client.model.constants.SingletonClass;
import client.model.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import server.dao.ReviewDAO;
import server.dao.ReviewDAOI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ReviewService implements ReviewServiceI {
    private ReviewDAOI reviewDAOI;
    //private List<Validators<EntityApp>> validators;
    private SessionFactory sessionFactory;
    private ClientConnection clientConnection;
    private Session session;
    private SingletonClass singletonClass;

    public ReviewService(){
        //validators = new ArrayList<Validators<EntityApp>>();
       // validators.add(new IdValidators());
        //validators.add(new NumeValidator());
        reviewDAOI = new ReviewDAO();
        singletonClass = SingletonClass.getInstance();

        clientConnection = singletonClass.clientConnection;
    }


    //@Transactional
    public void addReview(Review review) {
        //for (Validators<EntityApp> v : validators) {
       //     v.validate(actor);
        //}
        this.reviewDAOI.addReview(review);
    }

    //@Transactional
    public void updateReview(Review review) {
        //for (Validators<EntityApp> v : validators) {
        //    v.validate(actor);
       // }
        this.reviewDAOI.updateReview(review);
    }

    //@Transactional
    public List<Review> listReview()  {
       // clientConnection.getAllActorsSendToServer("GET_ALL_ACTORS");
       // List<Review> actorList = clientConnection.getListOfActorsReceived();
       // return actorList;
        return this.reviewDAOI.listReview();
    }

    //@Transactional
    public Review getReviewById(int id) {
        Review review = this.reviewDAOI.getReviewById(id);
        if (review == null) {
            throw new NoSuchElementException("Clientul cu id-ul =" + id + " nu a fost gasit!");
        }
        return review;
    }

    //@Transactional
    public void removeReview(int id) {
        this.reviewDAOI.removeReview(id);
    }
}
