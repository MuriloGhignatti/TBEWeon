package me.murilo.ghignatti.channelfactories;

import java.nio.charset.Charset;
import java.util.Random;

import me.murilo.ghignatti.servicechannels.ServiceChannel;

/**
 * Producer
 */
public abstract class ChannelFactory {

    public abstract ServiceChannel produceServiceChannel();

    protected String genRandomString(int length){
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
