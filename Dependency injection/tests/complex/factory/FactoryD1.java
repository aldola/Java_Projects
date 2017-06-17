package complex.factory;

import common.DependencyException;
import complex.Factory;
import implementations.ImplementationD1;

/**
 * Created by alber on 29/05/2017.
 */
public class FactoryD1 implements Factory<ImplementationD1> {
    @Override
    public ImplementationD1 create(Object... parameters) throws DependencyException {
        int i;
        try {
            i = (int) parameters[0];
        } catch (ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationD1(i);
    }
}