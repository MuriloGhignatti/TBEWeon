package me.murilo.ghignatti.channelfactories;

import me.murilo.ghignatti.servicechannels.ServiceChannel;
import me.murilo.ghignatti.servicechannels.Voice;

/**
 * VoiceChannelProducer
 */
public class VoiceChannelFactory extends ChannelFactory{

    @Override
    public ServiceChannel produceServiceChannel() {
        return new Voice(genName(), genName());
    }

    private String genName(){
        return genRandomString(5) + " " + genRandomString(8);
    }
}
