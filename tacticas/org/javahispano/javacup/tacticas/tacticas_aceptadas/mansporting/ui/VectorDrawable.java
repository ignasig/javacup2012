package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.ui;


import java.awt.*;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Rectangulo;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Segmento;

/**
 * <br>
 * Clase que representa un element.
 * <br>
 * <br>Copyright Fi2net S.A. 10-ene-2011
 */
public class VectorDrawable extends AbstractDrawable<Segmento> {

    /**
     * Radio.
     */
    private static final double RADIUS = 1;

    /**
     * Constructor.
     *
     * @param element element.
     * @param canvas canvas.
     */
    public VectorDrawable(Segmento element, Color color) {
        super(element, color);
    }

    /**
     * Realiza el pintado del elemento.
     *
     * @param g gr�ficos para pintar.
     */
    public void paint(VisualDebugGraphics g, Rectangulo canvas) {
        Color defaultColor = g.getColor();
        g.setColor(color);

        g.drawLine(
                element.getPuntoA().getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2),
                -element.getPuntoA().getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2),
                element.getPuntoB().getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2),
                -element.getPuntoB().getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2)
        );
        double x = element.getPuntoB().getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2) - RADIUS;
        double y = -element.getPuntoB().getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2) - RADIUS;
        g.fillOval(x, y, RADIUS * 2, RADIUS * 2);
        g.setColor(defaultColor);
    }
}
