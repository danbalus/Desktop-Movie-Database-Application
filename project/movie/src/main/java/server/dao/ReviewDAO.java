package server.dao;

import client.model.config.ConfigurationFactory;
import client.model.entity.EntityApp;
import client.model.entity.Review;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ReviewDAO implements ReviewDAOI{
    private ConfigurationFactory configurationFactory;
    private SessionFactory sessionFactory;
    private Session session;

    public ReviewDAO() {
        configurationFactory = new ConfigurationFactory();
        configurationFactory.configuration();
        sessionFactory = configurationFactory.getSessionFactory();
    }

    public void addReview(Review review) {
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            session.save(review);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateReview(Review review) {
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            session.update(review);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Review> listReview() {

        Transaction transaction = null;
        List<Review> reviewList = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            // actorsList = session.createQuery("from Actor").list();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Review> query = builder.createQuery(Review.class);
            Root<Review> root = query.from(Review.class);
            query.select(root);
            Query<Review> q = session.createQuery(query);
            reviewList = q.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return reviewList;
    }

    //@Transactional
    public Review getReviewById(int id) {
        Transaction transaction = null;
        Review review = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            //actor = (Actor) session.load(Actor.class,  (id));
            Query query = session.createQuery("from Review u  where u.id=:id");
            query.setParameter("id", id);
            review = (Review) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return review;
    }

    public void removeReview(int id) {

        Transaction transaction = null;
        Review review = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            review = (Review) session.load(Review.class, (id));
            if (review != null) {
                session.remove(review);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
