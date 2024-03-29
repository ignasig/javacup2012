package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.ui;

import java.awt.Color;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Punto;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Recta;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Rectangulo;

/**
 * <br>
 * Drawable para una recta.
 * <br>
 * <br>Copyright Fi2net S.A. 10-ene-2011
 */
public class RectaDrawable extends AbstractDrawable<Recta> {

    /**
     * Constructor.
     *
     * @param canvas  canvas.
     * @param element elemento.
     */
    public RectaDrawable(Recta element, Color color) {
        super(element, color);
    }

    public void paint(VisualDebugGraphics g, Rectangulo canvas) {
        Color defaultColor = g.getColor();
        g.setColor(color);

        Punto puntoA;
        Punto puntoB;
        if (element.getM() == Double.POSITIVE_INFINITY) {
            puntoA = new Punto(element.getX(-canvas.getAncho()), -canvas.getAlto());
            puntoB = new Punto(element.getX(canvas.getAncho()), canvas.getAncho());
        } else {
            puntoA = new Punto(-canvas.getAncho(), element.getY(-canvas.getAncho()));
            puntoB = new Punto(canvas.getAncho(), element.getY(canvas.getAncho()));
        }

        g.drawLine(
                puntoA.getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2),
                -puntoA.getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2),
                puntoB.getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2),
                -puntoB.getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2));

        g.setColor(defaultColor);
    }
}
