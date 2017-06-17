package complex;

import common.DependencyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alber on 29/05/2017.
 */
public class Container implements Injector {
    private Map<Class<?>, Object> objdict;
    private Map<Class<?>, Factory<?>> objfactory;
    private Map<Class<?>, Object[]> objlink;

    public Container() {
        objdict = new HashMap<Class<?>, Object>();
        objfactory = new HashMap<Class<?>, Factory<?>>();
        objlink = new HashMap<Class<?>, Object[]>();
    }

    @Override
    public <E> void registerConstant(Class<E> name, E value) throws DependencyException {
        if (objdict.containsKey(name)) {
            throw new DependencyException("registerConstant: A object exist");
        } else {
            objdict.put(name, value);
        }
    }

    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependencyException {
        List<Object> objectList = new ArrayList<Object>();
        if (objfactory.containsKey(name)) {
            throw new DependencyException("registerFactory: A factory exist");
        } else {
            for (Class<?> c : parameters) {
                if (!objdict.containsKey(c) && !objfactory.containsKey(c)) {
                    throw new DependencyException("registerFactory: The object doesn't exist");
                }
            }
            objfactory.put(name, creator);
            for (Class<?> c : parameters) {
                objectList.add(this.getObject(c));
            }
            objlink.put(name, objectList.toArray());
        }
    }

    @Override
    public <E> E getObject(Class<E> name) throws DependencyException {
        if (objfactory.containsKey(name) && objlink.containsKey(name)) {
            ArrayList<Object> list = new ArrayList<>();
            for (Object o : objlink.get(name)) {
                list.add(o);
            }
            return (E) objfactory.get(name).create(list.toArray());
        } else if (objdict.containsKey(name)) {
            return (E) objdict.get(name);
        } else {
            throw new DependencyException("getObject: The object doesn't exist");
        }
    }

}
