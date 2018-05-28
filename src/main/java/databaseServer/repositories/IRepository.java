package databaseServer.repositories;

import databaseServer.speicifiables.Specifiable;

import java.util.List;

public interface IRepository<T> {

    T findOne(Specifiable specifiable);

    void save(T item);

    List<T> findAll();
}
