import java.util.HashMap;
import java.util.Map;

/**
 * Created by alber on 29/05/2017.
 */
public class Container implements Injector {
    private Map<String, Object> objdict;
    private Map<String, Factory> objfactory;

    public Container() {
        objdict = new HashMap<String, Object>();
        objfactory = new HashMap<String, Factory>();
    }

    @Override
    public void registerConstant(String name, Object value) throws DependencyException {
        if (objfactory.containsKey(name)){
            throw new DependencyException("A factory exist");
        }else if(objdict.containsKey(name)) {
            throw new DependencyException("A object exist");
        } else {
            objdict.put(name,value);
        }
    }

    @Override
    public void registerFactory(String name, Factory creator, String... parameters) throws DependencyException {
        if (objfactory.containsKey(name)){
            throw new DependencyException("A factory exist");
        }else if(objdict.containsKey(name)) {
            throw new DependencyException("A object exist");
        } else {
            objfactory.put(name,creator.create(parameters));//TODO Falta veure com guardem els parametres i la factoria en un unic nom.
        }
    }

    @Override
    public Object getObject(String name) throws DependencyException {
        return null; //TODO Aqui simplement retornarem el objecte si es tal cosa o la factoria amb tota la ristra de items si e factory.
    }
}
