package implementations;

import common.InterfaceB;
import common.InterfaceD;

/**
 * Created by alber on 29/05/2017.
 */
public class ImplementationB1 implements InterfaceB {
    private InterfaceD d;

    public ImplementationB1(InterfaceD d) {
        this.d = d;
    }

    public InterfaceD getD() {
        return d;
    }
}
