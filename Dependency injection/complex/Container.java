import javax.print.DocFlavor;
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
    private Map<Class<?>, Class<?>[]> objlink;

    public Container() {
        objdict = new HashMap<Class<?>, Object>();
        objfactory = new HashMap<Class<?>, Factory<?>>();
        objlink = new HashMap<Class<?>, Class<?>[]>();
    }

    @Override
    public <E> void registerConstant(Class<E> name, E value) throws DependencyException {
        if (objfactory.containsKey(name)) {
            throw new DependencyException("A factory exist");
        } else if (objdict.containsKey(name)) {
            throw new DependencyException("A object exist");
        } else {
            objdict.put(name, value);
        }
    }

    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependencyException {
        if (objfactory.containsKey(name)) {
            throw new DependencyException("A factory exist");
        } else if (objdict.containsKey(name)) {
            throw new DependencyException("A object exist");
        } else {
            for (Class<?> c : parameters) {
                if (!objdict.containsKey(c) && !objfactory.containsKey(c)) {
                    throw new DependencyException("registerFactory: The object doesn't exist");
                }
            }
            objfactory.put(name, creator);
            objlink.put(name, parameters);
        }
    }

    @Override
    public <E> E getObject(Class<E> name) throws DependencyException {
        if (objfactory.containsKey(name) && objlink.containsKey(name)) {
            ArrayList<Object> list = new ArrayList<>();
            for (Class<?> c : objlink.get(name)) {
                list.add(objdict.get(c));
            }
            return (E) objfactory.get(name).create(list.toArray());
        } else if (objdict.containsKey(name)) {
            return (E) objdict.get(name);
        } else {
            throw new DependencyException("getObject: The object doesn't exist");
        }
    }

    public static void main(String[] args) {
        Injector injector = new Container();
        try {
            injector.registerConstant(Integer.class, 42);
            injector.registerFactory(InterfaceD.class,
                    new FactoryD1(),

                    Integer.class);
            InterfaceD d = injector.getObject(InterfaceD.class);
            ImplementationD1 d1 = (ImplementationD1) d;
        } catch (Exception e) {

        }
    }
}
