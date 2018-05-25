package DatabaseServer.DataContext;

import DatabaseServer.Specifiables.Specifiable;

import java.util.List;

public interface IDataContext<T> {

    T findOne(Specifiable specifiable);

    void save(T item);

    List<T> findAll();

    void delete(Specifiable specifiable);

}
