package org.ven.lmax.producerconsumer;

import com.lmax.disruptor.RingBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ven.lmax.producerconsumer.events.ValueEvent;

public class Producer {

    Logger logger = LoggerFactory.getLogger(Producer.class);
    RingBuffer<ValueEvent>  bus;

    public Producer(RingBuffer r){
        this.bus = r;
    }

    public void publish(String message){
        long seq = bus.next();
        ValueEvent valueEvent = bus.get(seq);
        valueEvent.setValue(message);
        bus.publish(seq);
        logger.info("Produced: " + seq + " | " + message);
    }
}
