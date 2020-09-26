package org.otus.education.cachehw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class MyCacheTest {

    private HwCache<Integer,Integer> myCache;
    private static final Logger logger = LoggerFactory.getLogger(MyCacheTest.class);

    @BeforeEach
    void init(){
        myCache = new MyCache<>();
    }


    @Test
    void put() {
        HwListener<Integer, Integer> listener = getHwListener();
        myCache.addListener(listener);
        myCache.put(1,1);
        assertEquals(myCache.size(),1);
        myCache.removeListener(listener);

    }

    @Test
    void remove() {
        HwListener<Integer, Integer> listener = getHwListener();
        myCache.addListener(listener);
        myCache.put(1,1);
        myCache.remove(1);
        assertEquals(myCache.size(),0);
        myCache.removeListener(listener);
    }

    @Test
    void get() {
        HwListener<Integer, Integer> listener = getHwListener();
        myCache.addListener(listener);
        myCache.put(1,100);
        Integer expected = myCache.get(1);
        assertEquals(expected,100);

        Integer expected2 = myCache.get(2);
        assertNull(expected2);
        myCache.removeListener(listener);
    }


    private HwListener<Integer, Integer> getHwListener() {
        return new HwListener<Integer, Integer>() {
            @Override
            public void notify(Integer key, Integer value, String action) {
                logger.info("key:{}, value:{}, action: {}", key, value, action);
            }
        };
    }
}