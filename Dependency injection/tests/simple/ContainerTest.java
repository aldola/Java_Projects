package simple;

import common.DependencyException;
import org.junit.Before;
import org.junit.Test;
import simple.factory.FactoryA1;
import simple.factory.FactoryB1;
import simple.factory.FactoryC1;
import simple.factory.FactoryD1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by alber on 16/06/2017.
 */
public class ContainerTest {
    private Injector injector;
    private int i;
    private Factory d;

    @Before
    public void setUp() throws Exception {
        injector = new Container();
        i = 42;
        d = new FactoryD1();
    }

    @Test
    public void registerConstant() throws Exception {
        injector.registerConstant("D", i);
    }

    @Test
    public void registerFactory() throws Exception {
        injector.registerConstant("D", i);
        injector.registerFactory("FD1", d, "D");
    }

    @Test(expected = DependencyException.class)
    public void registerConstantFactoryExist() throws Exception {
        injector.registerConstant("D", i);
        injector.registerFactory("FD1", d, "D");
        injector.registerConstant("FD1", i);
    }

    @Test(expected = DependencyException.class)
    public void registerConstantRepeated() throws Exception {
        injector.registerConstant("D", i);
        injector.registerConstant("D", i);
    }

    @Test(expected = DependencyException.class)
    public void registerFactoryConstantExist() throws Exception {
        injector.registerConstant("D", i);
        injector.registerFactory("D", d, "D");
    }

    @Test(expected = DependencyException.class)
    public void registerFactoryRepeated() throws Exception {
        injector.registerConstant("D", i);
        injector.registerFactory("FD1", d, "D");
        injector.registerFactory("FD1", d, "D");
    }

    @Test(expected = DependencyException.class)
    public void getObjectDoesNotExist() throws Exception {
        injector.getObject("D");
    }

    @Test
    public void getObjectConstant() throws Exception {
        int i = 42;
        injector.registerConstant("D", i);
        assertEquals(i, injector.getObject("D"));
    }

    @Test
    public void getObjectFactoryOneParameter() throws Exception {

    }

    @Test
    public void getObjectFactoryMultipleParameters() throws Exception {

    }

}