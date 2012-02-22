/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.thread;

/**
 *
 * @author malvarez
 */
public class ThreadOperationData<I, D> implements IdentificableData<I, D> {

    private final I id;
    private final D data;

    public ThreadOperationData(I id, D data) {
        this.id = id;
        this.data = data;
    }

    public D getData() {
        return data;
    }

    public I getId() {
        return id;
    }
}
