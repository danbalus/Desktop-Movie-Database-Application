package server.dao;

import client.model.config.ConfigurationFactory;
import client.model.entity.Director;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DirectorDAO implements DirectorDAOI {
    private ConfigurationFactory configurationFactory;

    private SessionFactory sessionFactory;
    private Session session;

    public DirectorDAO() {
        configurationFactory = new ConfigurationFactory();
        configurationFactory.configuration();
        sessionFactory = configurationFactory.getSessionFactory();
    }

    public void addDirector(Director director) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(director);
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

    public void updateDirector(Director director) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(director);
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

    public List<Director> listDirector() {
        Transaction transaction = null;
        List<Director> directorsList = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //directorsList = session.createQuery("from Director").list();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Director> query = builder.createQuery(Director.class);
            Root<Director> root = query.from(Director.class);
            query.select(root);
            Query<Director> q = session.createQuery(query);
            directorsList = q.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return directorsList;
    }

    //@Transactional
    public Director getDirectorById(int id) {
        Transaction transaction = null;
        Director director = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //director = (Director) session.load(Director.class,  (id));
            Query query = session.createQuery("from Director u  where u.id=:id");
            query.setParameter("id", id);
            director = (Director) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return director;
    }

    public void removeDirector(int id) {

        Transaction transaction = null;
        Director director = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            director = (Director) session.load(Director.class, (id));
            if (director != null) {
                session.remove(director);
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
