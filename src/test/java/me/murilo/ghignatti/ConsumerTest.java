package me.murilo.ghignatti;

import me.murilo.ghignatti.channelfactories.ChatChannelFactory;
import me.murilo.ghignatti.data.OpenChannelDAO;
import me.murilo.ghignatti.data.OpenChannelDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.FutureTask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ConsumerTest {

    private final OpenChannelDAO dao = OpenChannelDAOImpl.getInstance();

    @BeforeEach
    void setup(){
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
            assertEquals(1, dao.getConsumed());
        } catch (Exception e) {
            fail();
        }
    }
}