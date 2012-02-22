/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.ui;

import java.awt.Color;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.thread.JugadoresBalonData;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Rectangulo;

/**
 *
 * @author MaN
 */
public class JugadorBalonDrawable extends AbstractDrawable<JugadoresBalonData> {

    public JugadorBalonDrawable(JugadoresBalonData element) {
        super(element, null);
    }

    public void paint(VisualDebugGraphics g, Rectangulo canvas) {
        if (element != null) {

            if (element.getContrario() != null) {
                new IteracionesBalonDrawable(element.getContrario(), new Color(255, 0, 0, 128)).paint(g, canvas);
            }
            if (element.getPropio() != null) {
                new IteracionesBalonDrawable(element.getPropio(), new Color(0, 0, 255, 128)).paint(g, canvas);
            }

        }
    }
}
