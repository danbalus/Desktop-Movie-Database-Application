package server.dao;

import client.model.config.ConfigurationFactory;
import client.model.entity.Actor;
import client.model.entity.EntityApp;
import org.hibernate.*;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ActorDAO implements ActorDAOI {
    private ConfigurationFactory configurationFactory;
    private SessionFactory sessionFactory;
    private Session session;

    public ActorDAO() {
        configurationFactory = new ConfigurationFactory();
        configurationFactory.configuration();
        sessionFactory = configurationFactory.getSessionFactory();
    }

    public void addActor(EntityApp actor) {
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            session.save(actor);
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

    public void updateActor(Actor actor) {
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            session.update(actor);
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

    public List<Actor> listActor() {

        Transaction transaction = null;
        List<Actor> actorsList = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            // actorsList = session.createQuery("from Actor").list();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Actor> query = builder.createQuery(Actor.class);
            Root<Actor> root = query.from(Actor.class);
            query.select(root);
            Query<Actor> q = session.createQuery(query);
            actorsList = q.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return actorsList;
    }

    //@Transactional
    public Actor getActorById(int id) {
        Transaction transaction = null;
        Actor actor = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            //actor = (Actor) session.load(Actor.class,  (id));
            Query query = session.createQuery("from Actor u  where u.id=:id");
            query.setParameter("id", id);
            actor = (Actor) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return actor;
    }

    public void removeActor(int id) {

        Transaction transaction = null;
        Actor actor = null;
        try {
            session = sessionFactory.openSession();
            ;
            transaction = session.beginTransaction();
            actor = (Actor) session.load(Actor.class, (id));
            if (actor != null) {
                session.remove(actor);
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
