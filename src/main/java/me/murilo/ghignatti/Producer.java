package me.murilo.ghignatti;

import me.murilo.ghignatti.channelfactories.ChannelFactory;
import me.murilo.ghignatti.data.OpenChannelDAO;

/**
 * Producer
 */
public class Producer extends Thread{

    private ChannelFactory factory;
    private OpenChannelDAO dao;

    public Producer(ChannelFactory factory, OpenChannelDAO dao){
        this.factory = factory;
        this.dao = dao;
    }

    @Override
    public void run() {
        dao.openChannel(factory.produceServiceChannel());
    }
    
}
