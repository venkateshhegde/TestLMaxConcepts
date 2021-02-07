package org.ven.lmax.producerconsumer;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ven.lmax.producerconsumer.events.ValueEvent;

public class Consumer implements EventHandler<ValueEvent>{

    Logger logger = LoggerFactory.getLogger(Consumer.class);
    public void onEvent(final ValueEvent event, final long sequence, final boolean endOfBatch) throws Exception {
        logger.info("Sequence: " + sequence + " ValueEvent: " + event.getValue());
    }
}
