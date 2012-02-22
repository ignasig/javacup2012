/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javahispano.javacup.tacticas.tacticas_aceptadas.Sag;

import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;

/**
 *
 * @author pdsanchez
 */
public class JRival extends Jugador {
    private boolean portero = false;

    public JRival(int idx, PlayerDetail detalle, Position posRef) {
        super(idx, detalle, posRef);
        this.setTipo(Jugador.TIPO_RIVAL);
    }

    public JRival(int idx, PlayerDetail detalle) {
        super(idx, detalle);
        this.setTipo(Jugador.TIPO_RIVAL);
    }

    public JRival(int idx, PlayerDetail detalle, boolean portero) {
        super(idx, detalle);
        this.setTipo(Jugador.TIPO_RIVAL);
        this.portero = portero;
    }

    @Override
    public boolean isPortero() {
        return portero;
    }

    @Override
    public Command irAPosicionDestino() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Command GolpearBalon() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
