package simple;

import common.DependencyException;
import common.InterfaceD;
import implementations.ImplementationB1;
import implementations.ImplementationC1;
import implementations.ImplementationD1;
import org.junit.Before;
import org.junit.Test;
import simple.factory.FactoryA1;
import simple.factory.FactoryB1;
import simple.factory.FactoryC1;
import simple.factory.FactoryD1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

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
    public void getObjectFactoryD1() throws Exception {
        injector.registerConstant("I", i);
        injector.registerFactory("D", new FactoryD1(), "I");
        Object fd1 = injector.getObject("D");
        assertThat(fd1, is(instanceOf(ImplementationD1.class)));
        assertEquals((Integer) i, (Integer) ((ImplementationD1) fd1).getI());
    }

    @Test
    public void getObjectFactoryC1() throws Exception {
        String s = "patata";
        injector.registerConstant("S", s);
        injector.registerFactory("C", new FactoryC1(), "S");
        Object fc1 = injector.getObject("C");
        assertThat(fc1, is(instanceOf(ImplementationC1.class)));
        assertEquals((String) s, (String) ((ImplementationC1) fc1).getS());
    }

    @Test
    public void getObjectFactoryB1() throws Exception {
        int i = 42;
        injector.registerConstant("I", i);
        injector.registerFactory("D", new FactoryD1(), "I");
        injector.registerFactory("B", new FactoryB1(), "D");
        Object fb1 = injector.getObject("B");
        assertThat(fb1, is(instanceOf(ImplementationB1.class)));
        System.out.println(((ImplementationB1) fb1).getD());
    }

    @Test
    public void getObjectFactoryA1() throws Exception {
//        int i = 42;
//        injector.registerConstant(Integer.class, i);
//        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
//        Object f1 = injector.getObject(InterfaceD.class);
//        assertThat(f1, is(instanceOf(ImplementationD1.class)));
//        assertEquals((Integer) i, (Integer) ((ImplementationD1) f1).getI());
    }

}