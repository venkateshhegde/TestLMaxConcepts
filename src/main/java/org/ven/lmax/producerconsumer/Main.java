package org.ven.lmax.producerconsumer;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.ven.lmax.producerconsumer.events.ValueEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        // Preallocate RingBuffer with 1024 ValueEvents
        Disruptor<ValueEvent> disruptor = new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, 1024, exec);
        Consumer cons = new Consumer();
        disruptor.handleEventsWith(cons);
        RingBuffer<ValueEvent> ringBuffer = disruptor.start();

        Producer prod = new Producer(ringBuffer);

        for (int i = 0; i < 1000; i++) {
            prod.publish(String.format("%5d", i));
        }

        disruptor.shutdown();
        exec.shutdown();

    }
}
