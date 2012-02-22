/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.thread;

import java.util.concurrent.Future;

/**
 *
 * @author malvarez
 */
public interface IdentificableFuture<I, E> extends Future<E> {

    I getId();
}
