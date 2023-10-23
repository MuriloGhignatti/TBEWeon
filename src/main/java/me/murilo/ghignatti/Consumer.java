package me.murilo.ghignatti;

import me.murilo.ghignatti.data.OpenChannelDAO;
import me.murilo.ghignatti.servicechannels.ServiceChannel;

import java.util.concurrent.Callable;

public class Consumer implements Callable<Void> {

    private final int timeout;
    private final OpenChannelDAO dao;

    public Consumer(OpenChannelDAO dao, int timeout) {
        this.timeout = timeout;
        this.dao = dao;
    }

    @Override
    public Void call() {
        ServiceChannel channel = null;
        while (true) {
            channel = dao.getOpenChannel(this.timeout);
            if (channel != null)
                channel.consume();
            else
                return null;
        }
    }
}
