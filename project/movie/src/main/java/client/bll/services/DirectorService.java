package client.bll.services;

import client.ClientConnection;
import client.model.constants.SingletonClass;
import client.model.entity.Actor;
import server.dao.DirectorDAO;
import server.dao.DirectorDAOI;
import client.model.entity.Director;
import client.model.entity.EntityApp;
import client.model.validators.IdValidators;
import client.model.validators.NumeValidator;
import client.model.validators.Validators;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DirectorService implements DirectorServiceI {

    private DirectorDAOI directorDAOI;
    private List<Validators<EntityApp>> validators;
    private SessionFactory sessionFactory;
    private Session session;
    private SingletonClass singletonClass;
    private ClientConnection clientConnection;


    public DirectorService(){
        validators = new ArrayList<Validators<EntityApp>>();
        validators.add(new IdValidators());
        validators.add(new NumeValidator());
        directorDAOI = new DirectorDAO();
        singletonClass = SingletonClass.getInstance();

        clientConnection = singletonClass.clientConnection;
    }

    ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//WARNING --> AU FOST REALIZATE MODIFICARILE PE CLIENT-SERVER PENTRU METODELE CARE SE FOLOSESC
    //EXEMPLU -> addDirector nu se foloseste nicaieri etc, pe cand listDirector da.
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //@Transactional

    public void addDirector(Director director) {
        for (Validators<EntityApp> v : validators) {
            v.validate(director);
        }
        this.directorDAOI.addDirector(director);
    }

    //@Transactional
    public void updateDirector(Director director) {
        for (Validators<EntityApp> v : validators) {
            v.validate(director);
        }
        this.directorDAOI.updateDirector(director);
    }

    //@Transactional
    public List<Director> listDirector() throws InterruptedException, IOException, ClassNotFoundException {
        clientConnection.getAllDirectorsSendToServer("GET_ALL_DIRECTORS");
        List<Director> directorList = clientConnection.getListOfDirectorsReceived();
        return directorList;
       // return this.directorDAOI.listDirector();
    }

    //@Transactional
    public Director getDirectorById(int id) {
        Director director = this.directorDAOI.getDirectorById(id);
        if (director == null) {
            throw new NoSuchElementException("Regizorul cu id-ul =" + id + " nu a fost gasit!");
        }
        return director;
    }

    //@Transactional
    public void removeDirector(int id) {
        this.directorDAOI.removeDirector(id);
    }
}
