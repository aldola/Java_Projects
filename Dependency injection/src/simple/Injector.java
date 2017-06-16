package simple;

import common.DependencyException;

/**
 * Created by alber on 29/05/2017.
 */
public interface Injector {

    void registerConstant(String name, Object value) throws DependencyException;

    void registerFactory(String name, simple.Factory creator, String... parameters) throws DependencyException;

    Object getObject(String name) throws DependencyException;
}