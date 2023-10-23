package me.murilo.ghignatti.data;

import me.murilo.ghignatti.servicechannels.ServiceChannel;

public interface OpenChannelDAO {

    public void addOpenChannel(ServiceChannel channel);

    public ServiceChannel getOpenChannel(int timeout);

}
