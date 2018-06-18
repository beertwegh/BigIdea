package databaseserver.datacontext;

import databaseserver.speicifiables.Specifiable;

import java.util.List;

public interface IDataContext<T> {

    T findOne(Specifiable specifiable);

    void save(T item);

    List<T> findAll();

}
