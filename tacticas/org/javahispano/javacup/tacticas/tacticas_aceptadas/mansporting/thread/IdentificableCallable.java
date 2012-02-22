/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.thread;

import java.util.concurrent.Callable;

/**
 *
 * @author malvarez
 */
public interface IdentificableCallable<I, E> extends Callable<E> {

    I getId();
}
