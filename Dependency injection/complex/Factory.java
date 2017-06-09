/**
 * Created by alber on 29/05/2017.
 */
public interface Factory<E> {
    E create(Object... parameters) throws DependencyException;
}
