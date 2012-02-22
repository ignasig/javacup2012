/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javahispano.javacup.tacticas.tacticas_aceptadas.ifox.estado;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.ifox.base.Estado;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.ifox.futbol.Jugador;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.ifox.util.Parametros;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.util.Position;

/**
 *
 * @author Usuario
 */
public class ManejandoBalon extends Estado<Jugador> {

    private ManejandoBalon() {
    }

    public static ManejandoBalon getInstance() {
        return ManejandoBalonHolder.INSTANCE;
    }

    @Override
    public void ejecutar(Jugador jugador) {
        jugador.golpearBalon(Constants.centroArcoSup, true);

        jugador.regatear();

        Position posicionPase = jugador.getEquipo().buscarPase(jugador,
            Parametros.Jugador.DISTANCIA_MINIMA_PASE);
        if (posicionPase != null && posicionPase.isInsideGameField(0)) {
            jugador.golpearBalon(posicionPase, false);
        }

        jugador.getMaquinaEstado().cambiarEstado(Esperando.getInstance());
    }

    private static class ManejandoBalonHolder {
        private static final ManejandoBalon INSTANCE = new ManejandoBalon();
    }
 }
