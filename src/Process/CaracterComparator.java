
package Process;

import java.util.Comparator;

/**
 *
 * @author The Worst One
 */
public class CaracterComparator implements Comparator<Nodo> {

    @Override
    public int compare(Nodo o1, Nodo o2) {
        return o1.getFrecuencia().compareTo(o2.getFrecuencia());
    }
    
}
