/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

/**
 *Listener para notificar updates en la situación del partido.
 * @author malvarez
 */
public interface MSGSituacionPartidoListener {

    /**
     * Método llamado tras el update del contexto.
     * @param context contexto.
     */
    void afterUpdate(MSGSituacionPartidoContext context);

}
