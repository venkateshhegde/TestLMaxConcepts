package org.ven.lmax.producerconsumer;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ven.lmax.producerconsumer.events.ValueEvent;

public class Consumer implements  EventConsumer, EventHandler<ValueEvent> {

    Logger logger = LoggerFactory.getLogger(Consumer.class);
    public void onEvent(final ValueEvent event, final long sequence, final boolean endOfBatch) throws Exception {
        logger.info("Consumer Sequence: " + sequence + " ValueEvent: " + event.getValue());
        if(sequence%64 == 0) {
            Thread.sleep(1000);
            logger.info("Pausing to emulate slow consumer");
        }
    }

    @Override
    public void start() {
        logger.info("Starting consumer");
    }

    @Override
    public void stop() {
        logger.info("Stopping consumer");
    }

}
