package DatabaseServer.Repositories;

import DatabaseServer.Specifiables.Specifiable;

import java.util.List;

public interface IRepository<T> {

    T findOne(Specifiable specifiable);

    void save(T item);

    List<T> findAll();
}
