/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.ui;

import java.awt.Color;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Rectangulo;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Segmento;

/**
 *
 * @author MaN
 */
public class SegmentoDrawable extends AbstractDrawable<Segmento> {

    /**
     * Constructor.
     *
     * @param canvas  canvas.
     * @param element elemento.
     */
    public SegmentoDrawable(Segmento element, Color color) {
        super(element, color);
    }

    public void paint(VisualDebugGraphics g, Rectangulo canvas) {
        Color defaultColor = g.getColor();
        g.setColor(color);

        g.drawLine(
                element.getPuntoA().getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2),
                -element.getPuntoA().getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2),
                element.getPuntoB().getX() + (canvas.getCentro().getX() + canvas.getAncho() / 2),
                -element.getPuntoB().getY() + (canvas.getCentro().getY() + canvas.getAlto() / 2));

        g.setColor(defaultColor);
    }
}
