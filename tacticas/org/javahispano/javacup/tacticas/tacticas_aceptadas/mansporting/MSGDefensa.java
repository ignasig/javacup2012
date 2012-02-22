/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

import java.awt.Color;

/**
 *
 * @author MaN
 */
public class MSGDefensa extends MSGAbstractJugadorPropio {

    public MSGDefensa(int index, Color colorPelo, Color colorPiel, String nombre, int numero, MSGEstadisticas estadisticas) {
        super(index, colorPelo, colorPiel, nombre, numero, estadisticas);
    }

    public double getIteracionesVentajaPase() {
        return MSGConstants.ITERACIONES_MIMIMAS_VENTAJA_DEFENSA;
    }
}
