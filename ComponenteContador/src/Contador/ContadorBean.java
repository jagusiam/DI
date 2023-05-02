/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package Contador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.io.Serializable;
import java.util.EventListener;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 *
 * @author aga
 */

//Un JavaBean - implementa Serializable y tiene cosntructor sin parametros
//Extiende JLabel que es la etiqueta donde se vaya a mostrar el contador
public class ContadorBean extends JLabel implements ActionListener, Serializable {
   
    private PropertyChangeSupport propertySupport;
    
    public int tiempo;
    private final Timer timer;
    public static final String PROP_TIEMPO = "tiempo";
    private FinCuentaAtrasListener receptor;

   

    /**
     * Se define una interfaz que defina los métodos a usar cuando se genere
     * el evento. Implementa java.util.EventListener.
     *  Aqui la gestión del evento - a través del método capturarFinCuentaAtras.
     */
    public interface FinCuentaAtrasListener extends EventListener {
        public void capturarFinCuentaAtras(FinCuentaAtrasEvent ev);
    }
    
    public int getTiempo() {
        return tiempo;
    }
            
    /**
     * Cada vez que se cambie el valor de tiempo será necesario reflejar el cambio
     * en el texto de la etiqueta, y repintarla para que surta efecto.
     */    
    public void setTiempo(int tiempo) {
        int oldTiempo = this.tiempo;
        this.tiempo = tiempo;
        setText(Integer.toString(tiempo));
        repaint();
    }
    
    /**
     * En el constructor creado sin argumentos: se define un objeto Timer 
     * programado cada segundo - cada 1000 milisegundos. 
     * En el constructor, se inicializa nuestro contador con el valor estipulado - aqui 5. 
     * Por lo tanto, la cuenta atrás comenzará en dicho valor. 
     * Además se activa el temporizador.
     */
    public ContadorBean() {
        tiempo = 5; //para que comience la cuenta a tras a los 5 sec
        timer = new Timer(1000, this); //inicializar el objeto Timer
        //timer = new Timer();
        setText(Integer.toString(tiempo)); //metodo que modifica el texto a visualizar por la etiqueta
        setActivo(true);        
    }
    

    
    /** Activo es en si mismo una propiedad (sin atributo subyacente).
     * Contiene el código para gestionar si el temporizador,
     * objeto Timer está funcionando o no.
     */
    public final void setActivo(boolean valor) {
        if (valor == true) 
            timer.start();
        else
            timer.stop();
    }
    
    /**
     * Contiene el código para que nos devuelva si el temporizador se está 
     * ejecutando o no.
     */
    public boolean getActivo() {
        return timer.isRunning();
    }
    
    /** Accion que se realiza cada vez que se cumplen los 1000 milisegudos establecidos
     * para timer - se modifica el valor del texto de la etiqueta, se repinta y se 
     * disminuye en un segundo el tiempo restante. Cuando el tiempo llega a 0 
     * se para el Timer y se muestra un mensaje de tiempo terminado.
     */
//    public void actionPerfomed(ActionEvent e) {
//        setText(Integer.toString(tiempo)); //se asigna el valor a mosrar en la etiqueta
//        repaint(); //se repinta la etiqueta 
//        tiempo--; //se disminuye el valor de la variable tiempo hasta 0
//        if (tiempo == 0) 
//            setActivo(false);
//        
//        JOptionPane.showMessageDialog(null, "Terminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);         
//    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        setText(Integer.toString(tiempo));
        repaint();
        tiempo --;
        if (tiempo == 0) {
            setActivo(false);
        receptor.capturarFinCuentaAtras(
        new FinCuentaAtrasEvent(this));
        }
    }
    

    /**
     * Se añaden dos métodos, addEventoListener y removeEventoListener que permitan
     * al componente añadir y eliminar los oyentes - para que se pueda admitir varios oyentes. 
     * En nuestro caso sólo vamos a tener un oyente, pero se suele implementar para admitir a varios. 
     * Estos métodos deben de añadirse dentro de la clase que de definir el componente.
     */
   
    public void addFinCuentaAtrasListener(FinCuentaAtrasListener receptor){
            this.receptor = receptor;
    }
    
    public void removeFinCuentaAtrasListener(FinCuentaAtrasListener receptor){
            this.receptor=null;
    }    
    
    
} //fin clase
