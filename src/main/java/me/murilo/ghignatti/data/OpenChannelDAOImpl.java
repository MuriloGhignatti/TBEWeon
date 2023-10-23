package me.murilo.ghignatti.data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import me.murilo.ghignatti.servicechannels.ServiceChannel;

public class OpenChannelDAOImpl implements OpenChannelDAO {

    private static OpenChannelDAOImpl instance;

    private BlockingQueue<ServiceChannel> createdServicesChannels;
    private int produced;
    private int consumed;

    public static OpenChannelDAOImpl getInstance() {
        if (instance == null) {
            instance = new OpenChannelDAOImpl();
        }

        return instance;
    }

    private OpenChannelDAOImpl() {
        this.createdServicesChannels = new LinkedBlockingQueue<>();
        this.produced = 0;
        this.consumed = 0;
    }

    @Override
    public void addOpenChannel(ServiceChannel channel) {
        try {
            createdServicesChannels.put(channel);
            ++produced;
        } catch (InterruptedException e) {
            System.err.println("Could not insert channel{" + channel.getId() +
                    "} at the serviceChannelQueue");
        }
    }

    @Override
    public ServiceChannel getOpenChannel(int timeout) {
        try {
            ServiceChannel retrievedChannel = createdServicesChannels.poll(timeout, TimeUnit.MILLISECONDS);
            if (retrievedChannel != null)
                ++consumed;
            return retrievedChannel;
        } catch (InterruptedException e) {
            return null;
        }
    }

    public int getProduced() {
        return produced;
    }

    public void setProduced(int produced) {
        this.produced = produced;
    }

    public int getConsumed() {
        return consumed;
    }

    public void setConsumed(int consumed) {
        this.consumed = consumed;
    }
}
