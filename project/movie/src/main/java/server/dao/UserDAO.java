package server.dao;

import client.model.config.ConfigurationFactory;
import client.model.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAO implements UserDAOI {

    private ConfigurationFactory configurationFactory;
    private SessionFactory sessionFactory;
    private Session session;

    public UserDAO() {
        configurationFactory = new ConfigurationFactory();
        configurationFactory.configuration();
        sessionFactory = configurationFactory.getSessionFactory();
    }

    @Override
    public void addUser(User user) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            session.save(user);
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
    public void updateUser(User user) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            session.update(user);
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
    public List<User> listUser() {
        Transaction transaction = null;
        List<User> userList = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root);
            Query<User> q = session.createQuery(query);
            userList = q.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public User getUserById(String id) {
        Transaction transaction = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            //user = (User) session.load(User.class, new String(id));
            Query query = session.createQuery("from User u  where u.id=:id");
            query.setParameter("id", id);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void removeUser(String id) {
        Transaction transaction = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            user = (User) session.load(User.class, (id));
            if (user != null) {
                session.remove(user);
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
