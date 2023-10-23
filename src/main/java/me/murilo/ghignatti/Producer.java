package me.murilo.ghignatti;

import me.murilo.ghignatti.channelfactories.ChannelFactory;
import me.murilo.ghignatti.data.OpenChannelDAO;

import java.util.concurrent.Callable;

/**
 * Producer
 */
public class Producer implements Callable<Void> {

    private final int delay;
    private final ChannelFactory factory;
    private final OpenChannelDAO dao;

    public Producer(ChannelFactory factory, OpenChannelDAO dao, int delay) {
        this.factory = factory;
        this.dao = dao;
        this.delay = delay;
    }

    @Override
    public Void call() throws Exception {
        while (true) {
            dao.addOpenChannel(factory.produceServiceChannel());
            Thread.sleep(delay);
        }
    }
}
