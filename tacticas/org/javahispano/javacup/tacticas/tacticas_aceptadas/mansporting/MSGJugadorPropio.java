/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

import java.util.List;
import org.javahispano.javacup.model.command.Command;

/**
 *
 * @author MaN
 */
public interface MSGJugadorPropio extends MSGJugadorDetalle {

    /**
     * Alineación del jugador.
     * @return alineación
     */
    MSGAlineacionPosicion getAlineacion();

    /**
     * Indica si el jugador es el mas cercano.
     * @return mas cercano.
     */
    boolean isJugadorMasCercano();

    /**
     * Ejecuta el comando seleccionado.
     * @return ejecuta el comando seleccionado.
     */
    List<Command> ejecuta();

    /**
     * Devuelvel as minimas iteraciones de ventaja para que el jugador intente el pase.
     * @return iteracioens de ventaja minimas para que el jugador intente el pase.
     */
    double getIteracionesVentajaPase();
}
