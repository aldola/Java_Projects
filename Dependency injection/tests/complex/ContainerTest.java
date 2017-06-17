package complex;

import common.DependencyException;
import common.InterfaceA;
import common.InterfaceD;
import complex.factory.FactoryA1;
import complex.factory.FactoryB1;
import complex.factory.FactoryC1;
import complex.factory.FactoryD1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by alber on 16/06/2017.
 */
public class ContainerTest {
    private Injector injector;


    @Before
    public void setUp() throws Exception {
        injector = new Container();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void registerConstant() throws Exception {
        injector.registerConstant(Integer.class, 42);
    }

    @Test
    public void registerFactory() throws Exception {
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
    }

    @Test(expected = DependencyException.class)
    public void registerConstantRepeated() throws Exception {
        injector.registerConstant(Integer.class, 42);
    }

    @Test(expected = DependencyException.class)
    public void registerFactoryRepeated() throws Exception {
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
    }

    @Test(expected = DependencyException.class)
    public void getObjectDoesNotExist() throws Exception {
        injector.getObject(InterfaceA.class);
    }

    @Test
    public void getObjectConstant() throws Exception {
        int i = 42;
        injector.registerConstant(Integer.class, i);
        assertEquals((Integer)i, injector.getObject(Integer.class));
    }

    @Test
    public void getObjectFactoryD1() throws Exception {

    }

    @Test
    public void getObjectFactoryC1() throws Exception {

    }

    @Test
    public void getObjectFactoryB1() throws Exception {

    }

    @Test
    public void getObjectFactoryA1() throws Exception {

    }


}