package me.murilo.ghignatti.data;

import me.murilo.ghignatti.channelfactories.ChatChannelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class OpenChannelDAOImplTest {

    private OpenChannelDAO dao;

    @BeforeEach
    void setup(){
        Constructor<OpenChannelDAOImpl> cons =
                (Constructor<OpenChannelDAOImpl>) OpenChannelDAOImpl.class.getDeclaredConstructors()[0];
        cons.setAccessible(true);
        try {
            dao = cons.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }

    @Test
    void addOpenChannel() {
        assertTrue(dao.isQueueEmpty());
        try {
            dao.addOpenChannel(ChatChannelFactory.getInstance().produceServiceChannel());
        } catch (InterruptedException e) {
            fail();
        }
        assertFalse(dao.isQueueEmpty());
    }

    @Test
    void getOpenChannel() {
        assertTrue(dao.isQueueEmpty());
        try {
            dao.addOpenChannel(ChatChannelFactory.getInstance().produceServiceChannel());
        } catch (InterruptedException e) {
            fail();
        }
        dao.getOpenChannel(1);
        assertTrue(dao.isQueueEmpty());
    }

    @Test
    void getProduced() {
        assertEquals(0, dao.getProduced());
        assertEquals(0, dao.getConsumed());
        try {
            dao.addOpenChannel(ChatChannelFactory.getInstance().produceServiceChannel());
        } catch (InterruptedException e) {
            fail();
        }
        assertEquals(1, dao.getProduced());
        assertEquals(0, dao.getConsumed());
    }

    @Test
    void getConsumed() {
        assertEquals(0, dao.getProduced());
        assertEquals(0, dao.getConsumed());
        try {
            dao.addOpenChannel(ChatChannelFactory.getInstance().produceServiceChannel());
        } catch (InterruptedException e) {
            fail();
        }
        dao.getOpenChannel(1);
        assertEquals(1, dao.getProduced());
        assertEquals(1, dao.getConsumed());
    }
}