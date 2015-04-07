package pl.com.softproject.diabetyk.core.service;

import java.util.List;

/**
 * Interface CrudService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface CrudService<T, I> {

    public T getElement(I id);

    public List<T> getElements();

    public void add(T element);

    public void update(T element);

    public void delete(T element);
}
