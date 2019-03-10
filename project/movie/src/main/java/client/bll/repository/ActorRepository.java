package client.bll.repository;

import client.bll.services.ActorService;
import client.bll.services.ActorServiceI;
import client.model.entity.Actor;

import java.io.IOException;
import java.util.List;
//todo: return la metode pt a putea testa metodele +viit search uri sau features etc
public class ActorRepository implements ActorRepositoryI{
    private ActorServiceI actorServiceI;


    public ActorRepository() {
        actorServiceI = new ActorService();
    }

    @Override
    public void addActor(Actor actor) {
        actorServiceI.addActor(actor);
    }

    @Override
    public void updateActor(Actor actor) {
        actorServiceI.updateActor(actor);
    }

    @Override
    public List<Actor> listActor() throws InterruptedException, IOException, ClassNotFoundException {
        List<Actor> actorList = actorServiceI.listActor();
        return actorList;
    }

    @Override
    public Actor getActorById(int id) {
        Actor actor = actorServiceI.getActorById(id);
        return actor;
    }

    @Override
    public void removeActor(int id) {
        actorServiceI.removeActor(id);
    }
}
