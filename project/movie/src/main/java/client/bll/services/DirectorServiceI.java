package client.bll.services;

import client.model.entity.Director;

import java.io.IOException;
import java.util.List;

public interface DirectorServiceI {
   // public void setDirectorDAOI(DirectorDAOI directorDAOI);
   // public void setCurrentSession(SessionFactory sessionFactory);
    public void addDirector(Director director);
    public void updateDirector(Director director);
    public List<Director> listDirector() throws InterruptedException, IOException, ClassNotFoundException;
    public Director getDirectorById(int id);
    public void removeDirector(int id);
}
