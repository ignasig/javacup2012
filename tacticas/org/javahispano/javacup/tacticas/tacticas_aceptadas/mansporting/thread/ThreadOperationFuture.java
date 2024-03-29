/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author malvarez
 */
class ThreadOperationFuture<I, E> implements IdentificableFuture<I, E> {

    private final I id;
    private final Future<E> future;

    public ThreadOperationFuture(I id, Future future) {
        this.id = id;
        this.future = future;
    }

    public I getId() {
        return id;
    }

    public boolean isDone() {
        return future.isDone();
    }

    public boolean isCancelled() {
        return future.isCancelled();
    }

    public E get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }

    public E get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
    }
}
