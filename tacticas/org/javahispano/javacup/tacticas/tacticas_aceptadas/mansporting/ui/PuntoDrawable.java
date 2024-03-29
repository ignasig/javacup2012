package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.ui;

import java.awt.Color;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Punto;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Rectangulo;

/**
 * <br>
 * Drawable para un punto.
 * <br>
 * <br>Copyright Fi2net S.A. 10-ene-2011
 */
public class PuntoDrawable extends AbstractDrawable<Punto> {

    /**
     * Radio.
     */
    private static final double RADIUS = 1;

    /**
     * Constructor.
     *
     * @param canvas  canvas.
     * @param element elemento.
     */
    public PuntoDrawable(Punto element, Color color) {
        super(element, color);
    }

    public void paint(VisualDebugGraphics g, Rectangulo canvas) {
        Color defaultColor = g.getColor();
        g.setColor(color);
        g.fillOval(
                element.getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2) - RADIUS,
                -element.getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2) - RADIUS,
                RADIUS * 2,
                RADIUS * 2);
        g.setColor(defaultColor);
    }


}
