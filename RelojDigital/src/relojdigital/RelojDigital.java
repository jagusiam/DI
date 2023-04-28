
package relojdigital;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author aga
 */

//La clase del componente RELOJ DIGITAL
//Una clase: que muestre la hora 
//Que se pueda establecer el alarma
//que cuando sonase - ejecutase un evento - que permitiera al usuario ejecutar un codigo que quiera
//extiende de JLabel - para mostrar en pantalla la hora
//para que sea JAVABEANS - implementa interfaz Serializable y tiene constructor sin parametros
public class RelojDigital extends JLabel implements Serializable {
    //VARIABLES
    private boolean formato24; //propiedad que permite determinar el formato 12-24
    private Alarma alarma; //establecimiento alarma
    //dos variables para determinar formato 12-24
    private SimpleDateFormat sdf24h = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat sdf12h = new SimpleDateFormat("hh:mm:ss a");
    
    private AlarmaListener alarmaListener;
    
    public RelojDigital() {
        //Clase Timer - para ejecutar algo repetidas veces con un periodo de tiempo de separacion
        //
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Date horaActual = new Date();
                if(formato24) 
                    setText(sdf24h.format(horaActual));
                else 
                    setText(sdf12h.format(horaActual));
                
                //aqui programamos la funcionalidad de la funcion alarma
                //se comparan la hora del alarma y la hora actual
                if (alarma!= null) 
                {
                 //Para depurar
                    System.out.println(horaActual);
                    System.out.println(alarma.getHoraAlarma());
                    
                    //si la alarma está activa y las horas coinciden
                    if (alarma.isActiva() && horasCoinciden(horaActual, alarma.getHoraAlarma()))
                    {
                        //se dispara el evento
                        if (alarmaListener!=null) {
                            //llamamos al metodo de la interfaz AlarmaListener
                            //donde se implementa la accion que se ejecute cuando la alarma suena
                            alarmaListener.suenaAlarma();
                        }
                    }    
                }
            }
        }, 0, 1000); //valores: 0 - desde ya, 1000 de milisec - cada sec
    }

    public boolean isFormato24() {
        return formato24;
    }

    public void setFormato24(boolean formato24) {
        this.formato24 = formato24;
    }

    public Alarma getAlarma() {
        return alarma;
    }

    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
    }

    public SimpleDateFormat getSdf24h() {
        return sdf24h;
    }

    public void setSdf24h(SimpleDateFormat sdf24h) {
        this.sdf24h = sdf24h;
    }

    public SimpleDateFormat getSdf12h() {
        return sdf12h;
    }

    public void setSdf12h(SimpleDateFormat sdf12h) {
        this.sdf12h = sdf12h;
    }

    public AlarmaListener getAlarmaListener() {
        return alarmaListener;
    }

    public void setAlarmaListener(AlarmaListener alarmaListener) {
        this.alarmaListener = alarmaListener;
    }
    
    //para recoger una implementación de AlarmaListner
    public void addAlarmaListener(AlarmaListener alarmaListener) {
        this.alarmaListener = alarmaListener;
    }
    
    //metodo privado (solo se a a usar en componente) para comparar la hora actual con la hora del alarma
    private boolean horasCoinciden(Date horaActual, Date horaAlarma) {
        
        //Date tiene precision hasta milisec, por ello no se pueden
        //comparar aqui directamente dos instancias Date
        //con Calendar se sacan los campos hora min sec por separado 
        //y se comparan cada uno de elementos, hasta la precision de sec.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaActual);
        int horasActual,minActual,segActual,horasAlarma,minAlarma,segAlarma;
        horasActual = calendar.get(Calendar.HOUR_OF_DAY);
        minActual = calendar.get(Calendar.MINUTE);
        segActual = calendar.get(Calendar.SECOND);
        calendar.setTime(horaAlarma);
        horasAlarma = calendar.get(Calendar.HOUR_OF_DAY);
        minAlarma = calendar.get(Calendar.MINUTE);
        segAlarma = calendar.get(Calendar.SECOND);
        if (horasAlarma == horasAlarma && minActual == minAlarma && segActual == segAlarma)
            return true;
        else
            return false;
    }
            
    


    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
