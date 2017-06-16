package simple;

import common.DependencyException;

/**
 * Created by alber on 29/05/2017.
 */
public interface Factory {
    Object create(Object... parameters) throws DependencyException;
}
