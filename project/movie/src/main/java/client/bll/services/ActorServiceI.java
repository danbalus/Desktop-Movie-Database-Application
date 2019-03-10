package client.bll.services;

import client.model.entity.Actor;

import java.io.IOException;
import java.util.List;

public interface ActorServiceI {
    //public void setActorDAOI(ActorDAOI actorDAOI);
    //public void setCurrentSession(SessionFactory sessionFactory);
    public void addActor(Actor actor);
    public void updateActor(Actor actor);
    public List<Actor> listActor() throws InterruptedException, IOException, ClassNotFoundException;
    public Actor getActorById(int id);
    public void removeActor(int id);
}
