package server.dao;

import client.model.config.ConfigurationFactory;
import client.model.entity.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MovieDAO implements MovieDAOI {

    private ConfigurationFactory configurationFactory;
    private SessionFactory sessionFactory;
    private Session session;

    public MovieDAO() {
        configurationFactory = new ConfigurationFactory();
        configurationFactory.configuration();
        sessionFactory = configurationFactory.getSessionFactory();
    }

    public Movie addMovie(Movie movie) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            return movie;
        }
    }

    public void updateMovie(Movie movie) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            session.update(movie);
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

    public List<Movie> listMovie() {
        Transaction transaction = null;
        List<Movie> moviesList = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
            Root<Movie> root = query.from(Movie.class);
            query.select(root);
            Query<Movie> q = session.createQuery(query);
            moviesList = q.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return moviesList;
    }

    //@Transactional
    public Movie getMovieById(int id) {
        Transaction transaction = null;
        Movie movie = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Movie u  where u.id=:id");
            query.setParameter("id", id);
            movie = (Movie) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return movie;
    }

    public void removeMovie(int id) {

        Transaction transaction = null;
        Movie movie = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            movie = (Movie) session.load(Movie.class, (id));
            if (movie != null) {
                session.remove(movie);
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

    @Override
    public Movie getMovieByName(String name) {

        Transaction transaction = null;
        Movie movie = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Movie u  where u.name=:name");
            query.setParameter("name", name);
            movie = (Movie) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return movie;
    }
}
