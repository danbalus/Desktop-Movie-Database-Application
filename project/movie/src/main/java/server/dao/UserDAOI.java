package server.dao;

import client.model.entity.User;

import java.util.List;

public interface UserDAOI {
    //public SessionFactory getSessionFactory();
   // public void setCurrentSession(SessionFactory sessionFactory);
    public void addUser(User user);
    public void updateUser(User user);
    public List<User> listUser();
    public User getUserById(String id);
    public void removeUser(String id);
}
