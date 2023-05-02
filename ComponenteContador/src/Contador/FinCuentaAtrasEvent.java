package Contador;

import java.util.EventObject;

/**
 *
 * @author aga
 * Clase que implement los eventos
 * Hereda de java.util.EventObject
 */
public class FinCuentaAtrasEvent extends EventObject {
    
    public FinCuentaAtrasEvent(Object source) {
        super(source);
    }
    
}

