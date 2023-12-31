package me.murilo.ghignatti;

import me.murilo.ghignatti.channelfactories.ChatChannelFactory;
import me.murilo.ghignatti.data.OpenChannelDAO;
import me.murilo.ghignatti.data.OpenChannelDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.FutureTask;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerTest {

    private OpenChannelDAO dao;

    void setupDao(){
        Constructor<OpenChannelDAOImpl> cons =
                (Constructor<OpenChannelDAOImpl>) OpenChannelDAOImpl.class.getDeclaredConstructors()[0];
        cons.setAccessible(true);
        try {
            dao = cons.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }

    @BeforeEach
    void setup(){
        setupDao();
        Producer producer = new Producer(ChatChannelFactory.getInstance(), dao, 1);
        FutureTask<Void> futureTask = new FutureTask<>(producer);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            while(dao.isQueueEmpty()){
                //Wait for producer to produce
                Thread.sleep(2);
            }
            thread.interrupt();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void call() {
        Consumer consumer = new Consumer(dao, 1);
        FutureTask<Void> futureTask = new FutureTask<>(consumer);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            futureTask.get();
            //Even with 1 ms delay and waiting for only 2 ms we can still have more than 1 channel produced,
            //  we could make an implementation limiting the amount of channels produced by thread, but I think this
            //  goes against the idea.
            assertTrue(dao.getConsumed() >= 1);
        } catch (Exception e) {
            fail();
        }
    }
}