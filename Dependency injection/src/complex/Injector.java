package complex;

import common.DependencyException;

/**
 * Created by alber on 29/05/2017.
 */
public interface Injector {
    <E> void registerConstant(Class<E> name, E value) throws DependencyException;

    <E> void registerFactory(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependencyException;

    <E> E getObject(Class<E> name) throws DependencyException;
}