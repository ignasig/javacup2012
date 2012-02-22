/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.javahispano.javacup.model.engine.GameSituations;

/**
 *
 * @author MaN
 */
public abstract class MSGPosiciontHandlerManager {

    private static final List<MSGPosicionHandler> HANDLERS;
    private static final MSGPosicionHandler INICIAL_HANDLER;

    static {
        HANDLERS = new LinkedList<MSGPosicionHandler>();
        HANDLERS.add(new MSGDefaultPositionHandler());
        INICIAL_HANDLER = new MSGPosicionInicialHandler();
    }

    private MSGPosiciontHandlerManager() {
    }

    public static Map<Integer, MSGAlineacionPosicion> getPosicion(MSGTactica tactica, GameSituations sp, Boolean saco) {
        if (saco != null) {
            return INICIAL_HANDLER.getPosicion(tactica, sp, saco);
        } else {
            Map<Integer, MSGAlineacionPosicion> result = null;
            for (MSGPosicionHandler handler : HANDLERS) {
                result = handler.getPosicion(tactica, sp, null);
                if (result != null) {
                    break;
                }
            }
            return result;
        }
    }
}
