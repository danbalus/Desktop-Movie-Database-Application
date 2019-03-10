package client.bll.repository;

import client.bll.services.ReviewService;
import client.bll.services.ReviewServiceI;
import client.model.entity.Review;

import java.util.List;

public class ReviewRepository implements ReviewRepositoryI{
    private ReviewServiceI reviewServiceI;


    public ReviewRepository() {
        reviewServiceI = new ReviewService();
    }

    @Override
    public void addReview(Review review) {
        reviewServiceI.addReview(review);
    }

    @Override
    public void updateReview(Review review) {
        reviewServiceI.updateReview(review);
    }

    @Override
    public List<Review> listReview()  {
        List<Review> reviewList = reviewServiceI.listReview();
        return reviewList;
    }

    @Override
    public Review getReviewById(int id) {
        Review actor = reviewServiceI.getReviewById(id);
        return actor;
    }

    @Override
    public void removeReview(int id) {
        reviewServiceI.removeReview(id);
    }
}
