package DatabaseServer.DataContext;

import DatabaseServer.Specifiables.Specifiable;

import java.util.List;

public interface IDataContext<T> {
    Object findOne(int id);

    Object findOne(Specifiable specifiable);

    void save(T item);

    List findAll();

}
