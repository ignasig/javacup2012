/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javahispano.javacup.tacticas.tacticas_aceptadas.ifox.estado;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.ifox.base.Estado;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.ifox.base.Mensaje;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.ifox.base.Telegrama;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.ifox.futbol.Jugador;
import org.javahispano.javacup.model.util.Position;

/**
 *
 * @author Usuario
 */
public class Global extends Estado<Jugador> {

    private Global() {
    }

    public static Global getInstance() {
        return GlobalHolder.INSTANCE;
    }

    @Override
    public void ejecutar(Jugador jugador) {
    }

    @Override
    public boolean mensajeRecibido(Jugador jugador, Telegrama telegrama) {
        Mensaje mensaje = telegrama.getMensaje();
        switch (mensaje) {
            case PASAR_BALON:
                Jugador solicitante = (Jugador) telegrama.getExtra();
                //Posicion posicionPase = jugador.getEquipo().calcularMejorPaseAReceptor(jugador, solicitante);
                //if (posicionPase != null) {
                    jugador.golpearBalon(solicitante.getPosicion(), false);
                //}
                return true;
            case RECIBIR_BALON:
                jugador.setPosicionPase((Position) telegrama.getExtra());
                return true;
            default:
                return false;
        }
    }

    private static class GlobalHolder {
        private static final Global INSTANCE = new Global();
    }
 }
