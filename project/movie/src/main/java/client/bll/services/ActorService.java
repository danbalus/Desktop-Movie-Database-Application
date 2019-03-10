package client.bll.services;

import client.ClientConnection;
import client.model.constants.SingletonClass;
import client.model.entity.Movie;
import client.model.validators.IdValidators;
import client.model.validators.NumeValidator;
import client.model.validators.Validators;
import server.dao.ActorDAO;
import server.dao.ActorDAOI;
import client.model.entity.Actor;
import client.model.entity.EntityApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ActorService implements ActorServiceI {

    ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//WARNING --> AU FOST REALIZATE MODIFICARILE PE CLIENT-SERVER PENTRU METODELE CARE SE FOLOSESC
    //EXEMPLU -> addActor nu se foloseste nicaieri etc, pe cand listActor da.
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    private ActorDAOI actorDAOI;
    private List<Validators<EntityApp>> validators;
    private SessionFactory sessionFactory;
    private ClientConnection clientConnection;
    private Session session;
    private SingletonClass singletonClass;

    public ActorService(){
        validators = new ArrayList<Validators<EntityApp>>();
        validators.add(new IdValidators());
        validators.add(new NumeValidator());
        actorDAOI = new ActorDAO();
        singletonClass = SingletonClass.getInstance();

        clientConnection = singletonClass.clientConnection;
    }


    //@Transactional
    public void addActor(Actor actor) {
        for (Validators<EntityApp> v : validators) {
            v.validate(actor);
        }
        this.actorDAOI.addActor(actor);
    }

    //@Transactional
    public void updateActor(Actor actor) {
        for (Validators<EntityApp> v : validators) {
            v.validate(actor);
        }
        this.actorDAOI.updateActor(actor);
    }

    //@Transactional
    public List<Actor> listActor() throws InterruptedException, IOException, ClassNotFoundException {
        clientConnection.getAllActorsSendToServer("GET_ALL_ACTORS");
        List<Actor> actorList = clientConnection.getListOfActorsReceived();
        return actorList;
        //return this.actorDAOI.listActor();
    }

    //@Transactional
    public Actor getActorById(int id) {
        Actor actor = this.actorDAOI.getActorById(id);
        if (actor == null) {
            throw new NoSuchElementException("Clientul cu id-ul =" + id + " nu a fost gasit!");
        }
        return actor;
    }

    //@Transactional
    public void removeActor(int id) {
        this.actorDAOI.removeActor(id);
    }
}
