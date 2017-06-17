package simple.factory;

import common.DependencyException;
import implementations.ImplementationB1;
import implementations.ImplementationD1;
import simple.Factory;

/**
 * Created by alber on 29/05/2017.
 */
public class FactoryB1 implements Factory {
    @Override
    public ImplementationB1 create(Object... parameters) throws DependencyException {
        try {
            return new ImplementationB1((ImplementationD1) parameters[0]);
        } catch (ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
    }
}