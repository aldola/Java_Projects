package implementations;

import common.InterfaceC;

/**
 * Created by alber on 29/05/2017.
 */
public class ImplementationC1 implements InterfaceC {
    private String s;

    public ImplementationC1(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}