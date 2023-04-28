
package relojdigital;

import java.util.Date;

/**
 *
 * @author aga
 */
public class Alarma {
    private Date horaAlarma;
    private boolean activa;
    

    public Alarma() {
    }

    public Alarma(Date horaAlarma, boolean activa) {
        this.horaAlarma = horaAlarma;
        this.activa = activa;
        
    }

    public Date getHoraAlarma() {
        return horaAlarma;
    }

    public void setHoraAlarma(Date horaAlarma) {
        this.horaAlarma = horaAlarma;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    
    
    
}
