package server.dao;

import client.model.entity.Actor;
import client.model.entity.EntityApp;

import java.util.List;

public interface ActorDAOI {//extends EntityAppDAOI {
    //public SessionFactory getSessionFactory();
    //public void setCurrentSession(SessionFactory sessionFactory);
    public void addActor(EntityApp actor);
    public void updateActor(Actor actor);
    public List<Actor> listActor();
    public Actor getActorById(int id);
    public void removeActor(int id);
}
