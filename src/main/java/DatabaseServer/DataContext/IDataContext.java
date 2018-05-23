package DatabaseServer.DataContext;

import DatabaseServer.Specifiables.Specifiable;

import java.util.List;

public interface IDataContext {
    Object findOne(int id);

    Object findOne(Specifiable specifiable);

    void save(Object item);

    List<Object> findAll();
}
