package org.ven.lmax.producerconsumer;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ven.lmax.producerconsumer.events.ValueEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {

    static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();

        Consumer cons[] = new Consumer[3];
        for (int i = 0; i < 3; i++) {
            cons[i] = new Consumer();
        }
        final Disruptor<ValueEvent> disruptor = new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY,
                16,
                new ThreadFactory() {

                    int count = 0;

                    @Override
                    public Thread newThread(Runnable runnable) {
                        logger.info("Creating Thread..." + count);
                        return new Thread(runnable, "ConsumerThread" + (count++));
                    }
                });

        disruptor.handleEventsWith(cons);

        logger.info("Starting disruptor");


      //  new Thread(new Runnable() {
           // @Override
           // public void run() {

                RingBuffer<ValueEvent> ringBuffer = disruptor.start();
                Producer prod = new Producer(ringBuffer);

                try {
                    for (int i = 0; i < 1000; i++) {
                        logger.info("Producing 1 .." + i);
                        try {
                            prod.publish(String.format("%5d", i));
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }catch(Exception e){e.printStackTrace();}
           // }
      //  }).start();

//        exec.execute(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 1000; i++) {
//                    logger.info("Producing 2 .." + i);
//                    prod2.publish(String.format("%5d", i));
//                }
//            }
//        });


        logger.info("Shutting down...");
        disruptor.shutdown();

    }
}
