/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

import org.javahispano.javacup.model.PlayerDetail;

/**
 *
 * @author MaN
 */
public class MSGJugadorRival extends MSGAbstractJugadorDetalle {

    public MSGJugadorRival(int indice, PlayerDetail jugador) {
        super(
                indice,
                jugador.getHairColor(),
                jugador.getSkinColor(),
                jugador.getPlayerName(),
                jugador.getNumber(),
                new MSGEstadisticas.Builder().setPrecision(jugador.getPrecision()).setRemate(jugador.getPower()).setVelocidad(jugador.getSpeed()).getMSGEstadisticas(),
                true);
    }
}
