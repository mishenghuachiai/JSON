package com.jsong.disruptor;

import com.lmax.disruptor.RingBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Function:
 *
 * @author jsong
 *         Date: 2018/8/29 01:43
 * @since JDK 1.8
 */
public class LongEventProducer {

    private final static Logger LOGGER = LoggerFactory.getLogger(LongEventProducer.class);

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(long bb) {
        ringBuffer.getCursor();
        // Grab the next sequence
        long sequence = ringBuffer.next();

        try {
            // Get the entry in the Disruptor
            LongEvent event = ringBuffer.get(sequence);
            // for the sequence
            //LOGGER.info("product=[{}]",bb);
            event.set(bb);  // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}