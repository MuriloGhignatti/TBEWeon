package me.murilo.ghignatti.data;

import me.murilo.ghignatti.servicechannels.ServiceChannel;

public interface OpenChannelDAO {

    public void openChannel(ServiceChannel channel);

    public ServiceChannel closeChannel();

}
