
package javaapplication3.DAO;

import java.util.List;

public interface CrudDAO <T, ID> {
    T findById(ID id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(Long id);
}
