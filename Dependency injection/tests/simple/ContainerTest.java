package simple;

import common.DependencyException;
import common.InterfaceB;
import common.InterfaceC;
import common.InterfaceD;
import implementations.ImplementationA1;
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
    private String s;

    @Before
    public void setUp() throws Exception {
        injector = new Container();
        i = 42;
        d = new FactoryD1();
        s = "patata";
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
        injector.registerConstant("S", s);
        injector.registerFactory("C", new FactoryC1(), "S");
        Object fc1 = injector.getObject("C");
        assertThat(fc1, is(instanceOf(ImplementationC1.class)));
        assertEquals(s, ((ImplementationC1) fc1).getS());
    }

    @Test
    public void getObjectFactoryB1() throws Exception {
        injector.registerConstant("I", i);
        injector.registerFactory("D", new FactoryD1(), "I");
        injector.registerFactory("B", new FactoryB1(), "D");
        Object fb1 = injector.getObject("B");
        assertThat(fb1, is(instanceOf(ImplementationB1.class)));
        InterfaceD d1 = ((ImplementationB1) fb1).getD();
        assertEquals(i, ((ImplementationD1) d1).getI());
    }

    @Test
    public void getObjectFactoryA1() throws Exception {
        injector.registerConstant("I", i);
        injector.registerConstant("S", s);
        injector.registerFactory("C", new FactoryC1(), "S");
        injector.registerFactory("D", new FactoryD1(), "I");
        injector.registerFactory("B", new FactoryB1(), "D");
        injector.registerFactory("A", new FactoryA1(), "B","C");
        Object fa1 = injector.getObject("A");
        assertThat(fa1, is(instanceOf(ImplementationA1.class)));
        InterfaceB b1 = ((ImplementationA1) fa1).getB();
        InterfaceC c1 = ((ImplementationA1) fa1).getC();
        InterfaceD d1 = ((ImplementationB1) b1).getD();
        assertEquals(s, ((ImplementationC1) c1).getS());
        assertEquals(i, ((ImplementationD1) d1).getI());
    }

}