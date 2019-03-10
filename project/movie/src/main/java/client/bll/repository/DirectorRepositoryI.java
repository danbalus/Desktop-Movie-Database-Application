package client.bll.repository;

import client.model.entity.Director;

import java.util.List;

public interface DirectorRepositoryI {
    //todo:nu cred ca am nev de clasa asta ca in lista de la movie stochez tot
    public void addDirector(Director director);
    public void updateDirector(Director director);
    public List<Director> listDirector();
    public Director getDirectorById(int id);
    public void removeDirector(int id);
}
