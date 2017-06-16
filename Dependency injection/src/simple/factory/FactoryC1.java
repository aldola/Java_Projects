package simple.factory;

import common.DependencyException;
import common.ImplementationC1;
import simple.Factory;

/**
 * Created by alber on 29/05/2017.
 */
public class FactoryC1 implements Factory {
    @Override
    public ImplementationC1 create(Object... parameters) throws DependencyException {
        String s;
        try {
            s = (String) parameters[0];
        } catch (ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationC1(s);
    }
}