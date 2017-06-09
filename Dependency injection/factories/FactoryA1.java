/**
 * Created by alber on 29/05/2017.
 */
public class FactoryA1 implements Factory {
    @Override
    public ImplementationA1 create(Object... parameters) throws DependencyException {
         try {
             return new ImplementationA1((ImplementationB1)parameters[0],(ImplementationC1)parameters[1]);
         } catch (ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
    }
}