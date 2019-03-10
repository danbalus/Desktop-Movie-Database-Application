package server.dao;

import client.model.entity.Review;

import java.util.List;

public interface ReviewDAOI {
    public void addReview(Review review) ;

    public void updateReview(Review review) ;

    public List<Review> listReview() ;

    //@Transactional
    public Review getReviewById(int id) ;

    public void removeReview(int id);
}
