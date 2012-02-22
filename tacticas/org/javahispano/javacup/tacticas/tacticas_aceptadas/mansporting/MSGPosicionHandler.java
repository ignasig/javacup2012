/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

import java.util.Map;
import org.javahispano.javacup.model.engine.GameSituations;

/**
 *
 * @author MaN
 */
public interface MSGPosicionHandler {

    Map<Integer, MSGAlineacionPosicion> getPosicion(MSGTactica tactica, GameSituations sp, Boolean saco);
}
