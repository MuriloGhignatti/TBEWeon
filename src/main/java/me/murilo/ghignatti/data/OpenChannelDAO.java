package me.murilo.ghignatti.data;

import me.murilo.ghignatti.servicechannels.ServiceChannel;

public interface OpenChannelDAO {

    void addOpenChannel(ServiceChannel channel) throws InterruptedException;

    ServiceChannel getOpenChannel(int timeout);

    int getConsumed();

    int getProduced();

    boolean isQueueEmpty();
}
