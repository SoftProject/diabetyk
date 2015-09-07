package pl.com.softproject.diabetyk.web.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Piotr Szwed on 04.09.15.
 */
public class CollectionDTO<E> implements Serializable {

    private final Collection<E> collection;

    public CollectionDTO(Collection<E> collection) {
        this.collection = collection;
    }

    public Collection<E> getCollection() {
        return collection;
    }

    @Override
    public String toString() {
        return "CollectionDTO{" +
                "collection=" + collection +
                '}';
    }
}
