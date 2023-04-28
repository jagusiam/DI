
package relojdigital;

import java.awt.Component;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 *
 * @author aga
 */
//Clase Editor que hereda de PropertyEditorSupport
//para comunicar la existencia del editor de propiedad
public class AlarmaPropertyEditorSupport extends PropertyEditorSupport {
    private AlarmaPanel alarmaPanel = new AlarmaPanel();

    //true - personalizado
    @Override
    public boolean supportsCustomEditor() {
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    //devolver el panel
    @Override
    public Component getCustomEditor() {
        return alarmaPanel; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    //pasar el valor de la propiedad en el frame donde se vaya a utilizar
    @Override
    public String getJavaInitializationString() {
        Date horaAlarma = alarmaPanel.getSelectedValue().getHoraAlarma();
        boolean activa = alarmaPanel.getSelectedValue().isActiva();
        //un objeto de tipo alarma con dos parametros
        //1. Date en formato long, 2. y si esta activa o no
        return "new relojdigital.Alarma(new java.util.Date(" + horaAlarma.getTime() + "l),"+activa+")"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    //devolver el valor escogido por el usuario en el panel
    @Override
    public Object getValue() {
        return alarmaPanel.getSelectedValue(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}
