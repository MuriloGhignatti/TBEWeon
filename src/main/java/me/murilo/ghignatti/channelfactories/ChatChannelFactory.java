package me.murilo.ghignatti.channelfactories;

import me.murilo.ghignatti.servicechannels.Chat;
import me.murilo.ghignatti.servicechannels.ServiceChannel;

/**
 * ChatChannelProducer
 */
public class ChatChannelFactory extends ChannelFactory{

    @Override
    public ServiceChannel produceServiceChannel() {
        return new Chat(genNick(), genNick());
    }

    private String genNick(){
        return genRandomString(6);
    }   
}
