package client.bll.repository;

import client.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserRepositoryI {

   // public void setActorDAOI(ActorDAOI actorDAOI) ;

   // public void setDirectorDAOI(DirectorDAOI directorDAOI) ;

   // public void setMovieDAOI(MovieDAOI movieDAOI);

   // public void setCurrentSession(SessionFactory sessionFactory);
    public void rateMovie(String idUser,String nameMovie, int rateMovie) throws InterruptedException, IOException, ClassNotFoundException;

    //public void setUserDAOI(UserDAOI userDAOI);
   // public void setUserServiceI(UserServiceI userServiceI);
   // public void setMovieServiceI(MovieServiceI movieServiceI);
    public User addUser(User user) throws IOException;
    public User updateUser(User user) throws IOException, InterruptedException, ClassNotFoundException;
    public List<User> listUser() throws InterruptedException, IOException, ClassNotFoundException;
    public User getUserById(String id) throws InterruptedException, IOException, ClassNotFoundException;//by nickname
    public void removeUser(String id) throws IOException;


}
