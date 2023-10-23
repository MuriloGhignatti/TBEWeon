package me.murilo.ghignatti.channelfactories;

import me.murilo.ghignatti.servicechannels.Chat;
import me.murilo.ghignatti.servicechannels.ServiceChannel;

/**
 * ChatChannelProducer
 */
public class ChatChannelFactory extends ChannelFactory{

    private static ChatChannelFactory instance;

    private ChatChannelFactory(){}

    public static ChatChannelFactory getInstance(){
        if (instance == null){
            instance = new ChatChannelFactory();
        }
        return instance;
    }

    @Override
    public ServiceChannel produceServiceChannel() {
        return new Chat(genNick(), genNick());
    }

    private String genNick(){
        return genRandomString(6);
    }   
}
