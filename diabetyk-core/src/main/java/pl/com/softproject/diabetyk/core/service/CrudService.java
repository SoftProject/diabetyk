package pl.com.softproject.diabetyk.core.service;

import java.util.List;

/**
 * Interface CrudService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface CrudService<T, I> {

    T getElement(I id);

    List<T> getElements();

    void add(T element);

    void update(T element);

    void delete(T element);
}
