
package Ascii;

import java.util.Comparator;

public class CaracterComparator implements Comparator<Nodo> {

    @Override
    public int compare(Nodo o1, Nodo o2) {
        return o1.getFrecuencia().compareTo(o2.getFrecuencia());
    }
    
}
