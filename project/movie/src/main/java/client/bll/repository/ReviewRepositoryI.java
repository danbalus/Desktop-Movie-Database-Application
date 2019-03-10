package client.bll.repository;

import client.model.entity.Review;

import java.util.List;

public interface ReviewRepositoryI {
    public void addReview(Review review);
    public void updateReview(Review review);
    public List<Review> listReview() ;
    public Review getReviewById(int id) ;
    public void removeReview(int id);
}
