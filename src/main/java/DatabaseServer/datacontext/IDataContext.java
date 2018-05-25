package DatabaseServer.datacontext;

import DatabaseServer.speicifiables.Specifiable;

import java.util.List;

public interface IDataContext<T> {

    T findOne(Specifiable specifiable);

    void save(T item);

    List<T> findAll();

}
