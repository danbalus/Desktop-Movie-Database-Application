package server.dao;

import client.model.entity.Director;

import java.util.List;

public interface DirectorDAOI   {
   // public SessionFactory getSessionFactory();
    //public void setCurrentSession(SessionFactory sessionFactory);
    public void addDirector(Director director);
    public void updateDirector(Director director);
    public List<Director> listDirector();
    public Director getDirectorById(int id);
    public void removeDirector(int id);
}
