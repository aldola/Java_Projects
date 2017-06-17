package complex;

import common.*;
import complex.factory.FactoryA1;
import complex.factory.FactoryB1;
import complex.factory.FactoryC1;
import complex.factory.FactoryD1;
import implementations.ImplementationB1;
import implementations.ImplementationC1;
import implementations.ImplementationD1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
        assertEquals((Integer) i, injector.getObject(Integer.class));
    }

    @Test
    public void getObjectFactoryD1() throws Exception {
        int i = 42;
        injector.registerConstant(Integer.class, i);
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        Object fd1 = injector.getObject(InterfaceD.class);
        assertThat(fd1, is(instanceOf(ImplementationD1.class)));
        assertEquals((Integer) i, (Integer) ((ImplementationD1) fd1).getI());
    }

    @Test
    public void getObjectFactoryC1() throws Exception {
        String s = "patata";
        injector.registerConstant(String.class, s);
        injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        Object fc1 = injector.getObject(InterfaceC.class);
        assertThat(fc1, is(instanceOf(ImplementationC1.class)));
        assertEquals((String) s, (String) ((ImplementationC1) fc1).getS());
    }

    @Test
    public void getObjectFactoryB1() throws Exception {
        int i = 42;
        injector.registerConstant(Integer.class, i);
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        injector.registerFactory(InterfaceB.class, new FactoryB1(), InterfaceD.class);
        Object fb1 = injector.getObject(InterfaceB.class);
        assertThat(fb1, is(instanceOf(ImplementationB1.class)));
        System.out.println(((ImplementationB1) fb1).getD());
    }

    @Test
    public void getObjectFactoryA1() throws Exception {
        int i = 42;
        injector.registerConstant(Integer.class, i);
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        Object f1 = injector.getObject(InterfaceD.class);
        assertThat(f1, is(instanceOf(ImplementationD1.class)));
        assertEquals((Integer) i, (Integer) ((ImplementationD1) f1).getI());
    }


}