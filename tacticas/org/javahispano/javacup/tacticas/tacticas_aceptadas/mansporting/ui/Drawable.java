package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.ui;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Rectangulo;

/**
 * <br>
 * Elemento que puede ser pintado.
 * <br>
 * <br>Copyright Fi2net S.A. 10-ene-2011
 */
public interface Drawable<E> {

    /**
     * Realiza el pintado del elemento.
     *
     * @param g gr�ficos para pintar.
     * @param rectangulo  rectangulo donde pintar.
     */
    void paint(VisualDebugGraphics g, Rectangulo canvas);
}
