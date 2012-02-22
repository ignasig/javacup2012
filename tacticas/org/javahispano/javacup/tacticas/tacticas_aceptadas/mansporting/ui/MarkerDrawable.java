package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.ui;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Punto;

import java.awt.*;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Rectangulo;

/**
 * <br>
 * Marcado para marcar determinados sitios.
 * <br>
 * <br>Copyright Fi2net S.A. 10-ene-2011
 */
public class MarkerDrawable extends AbstractDrawable<Punto> {

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
    public MarkerDrawable(Punto element, Color color) {
        super(element, color);
    }

    /**
     * Realiza el pintado.
     *
     * @param g gr�ficos.
     */
    public void paint(VisualDebugGraphics g, Rectangulo canvas) {
        Color oldColor = g.getColor();
        g.setColor(color);

        double x = element.getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2) - RADIUS;
        double y = -element.getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2) - RADIUS;
        g.drawOval(x, y, RADIUS * 2, RADIUS * 2);

        g.setColor(oldColor);
    }
}
