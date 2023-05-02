
package jpanelimagenconeditor;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author aga
 */
public class JPanelImagenConEditor extends JPanel implements Serializable {
    private ImagenFondo imagenFondo;

    public JPanelImagenConEditor() {
    }

    public ImagenFondo getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(ImagenFondo imagenFondo) {
        this.imagenFondo = imagenFondo;
    }

    
    //metodo setComposite - pasamos canal Alpha, recogemos opacidad
    //una vez pintada la imagen, volvemos a establecer la opacidad a 1 - 100%
    //
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //para evitar el nullpointerexception: 
        //que solo se ejecute si distinto:
        
        if (imagenFondo != null) 
        {
        if (imagenFondo.getRutaImagen()!=null && imagenFondo.getRutaImagen().exists()) {
            ImageIcon imageIcon = new ImageIcon(imagenFondo.getRutaImagen().getAbsolutePath());
            
            //para cambiar la opacidad con la que dibujamos
            //convertir a 2 d
            
            
            Graphics2D g2d = (Graphics2D)g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imagenFondo.getOpacidad()));
            g.drawImage(imageIcon.getImage(), 0, 0, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        }
    }
     
}
