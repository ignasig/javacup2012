/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Punto;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Rectangulo;

/**
 *
 * @author MaN
 */
public final class MSGAlineacionPosicion {

    private final Punto posicion;
    private final Rectangulo area;

    private MSGAlineacionPosicion(Rectangulo area) {
        this.posicion = area.getCentro();
        this.area = area;
    }

    public Punto getPosicion() {
        return posicion;
    }

    public Rectangulo getArea() {
        return area;
    }

    public static MSGAlineacionPosicion getPointInstance(Punto posicion) {
        return new MSGAlineacionPosicion(
                new Rectangulo(
                posicion,
                0,
                0));
    }

    public static MSGAlineacionPosicion getAreaInstance(Punto posicion, double width, double height) {
        return new MSGAlineacionPosicion(
                new Rectangulo(
                posicion,
                width,
                height));
    }
}
