package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.ui;

import java.awt.Color;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Rectangulo;

/**
 * <br>
 * Drawable para un rectangulo.
 * <br>
 * <br>Copyright Fi2net S.A. 10-ene-2011
 */
public class RectanguloDrawable extends AbstractDrawable<Rectangulo> {

    /**
     * Constructor.
     *
     * @param canvas  canvas.
     * @param element elemento.
     */
    public RectanguloDrawable(Rectangulo element, Color color) {
        super(element, color);
    }

    public void paint(VisualDebugGraphics g, Rectangulo canvas) {
        Color defaultColor = g.getColor();
        g.setColor(color);

        g.drawRect(
                element.getCentro().getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2) - element.getAncho() / 2,
                -element.getCentro().getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2) - element.getAlto() / 2,
                element.getAncho(),
                element.getAlto());

        g.setColor(defaultColor);
    }


}
