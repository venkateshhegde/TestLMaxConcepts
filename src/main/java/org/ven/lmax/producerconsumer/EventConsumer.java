package org.ven.lmax.producerconsumer;

import com.lmax.disruptor.EventHandler;

/**
 * Consumer that consumes event from ring buffer.
 * @author Udai
 */
public interface EventConsumer {
    public void start();
    public void stop();
}
