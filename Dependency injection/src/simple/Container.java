package simple;

import common.DependencyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alber on 29/05/2017.
 */
public class Container implements Injector {
    private Map<String, Object> objdict;
    private Map<String, Factory> objfactory;
    private Map<String, String[]> objlink;

    public Container() {
        objdict = new HashMap<String, Object>();
        objfactory = new HashMap<String, Factory>();
        objlink = new HashMap<String, String[]>();
    }

    @Override
    public void registerConstant(String name, Object value) throws DependencyException {
        if (objfactory.containsKey(name)) {
            throw new DependencyException("registerConstant: A factory exist");
        } else if (objdict.containsKey(name)) {
            throw new DependencyException("registerConstant: A object exist");
        } else {
            objdict.put(name, value);
        }
    }

    @Override
    public void registerFactory(String name, Factory creator, String... parameters) throws DependencyException {
        if (objfactory.containsKey(name)) {
            throw new DependencyException("registerFactory: A factory exist");
        } else if (objdict.containsKey(name)) {
            throw new DependencyException("registerFactory: A object exist");
        } else {
            for (String s : parameters) {
                if (!objdict.containsKey(s) && !objfactory.containsKey(s)) {
                    throw new DependencyException("registerFactory: The object doesn't exist");
                }
            }
            objfactory.put(name, creator);
            objlink.put(name, parameters);
        }
    }

    @Override
    public Object getObject(String name) throws DependencyException {
        if (objfactory.containsKey(name) && objlink.containsKey(name)) {
            ArrayList<Object> list = new ArrayList<>();
            for (String s : objlink.get(name)) {
                list.add(objdict.get(s));
            }
            return objfactory.get(name).create(list.toArray());
        } else if (objdict.containsKey(name)) {
            return objdict.get(name);
        } else {
            throw new DependencyException("getObject: The object doesn't exist");
        }
    }
}
