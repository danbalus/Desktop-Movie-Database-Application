package client.bll.repository;

import client.bll.services.ActorService;
import client.bll.services.ActorServiceI;
import client.model.entity.Director;

import java.util.List;

public class DirectorRepository implements DirectorRepositoryI {
    //todo:return la met pt teste + search etc
    private DirectorRepository directorRepository;


    public DirectorRepository() {
        directorRepository = new DirectorRepository();
    }
    @Override
    public void addDirector(Director director) {
        directorRepository.addDirector(director);

    }

    @Override
    public void updateDirector(Director director) {
        directorRepository.updateDirector(director);
    }

    @Override
    public List<Director> listDirector() {
        List<Director> directorList = directorRepository.listDirector();
        return directorList;
    }

    @Override
    public Director getDirectorById(int id) {
        Director director = directorRepository.getDirectorById(id);
        return director;
    }

    @Override
    public void removeDirector(int id) {
        directorRepository.removeDirector(id);
    }


}
