package me.murilo.ghignatti.channelfactories;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import me.murilo.ghignatti.servicechannels.ServiceChannel;

/**
 * Producer
 */
public abstract class ChannelFactory {

    private final Random random;

    protected ChannelFactory(){
        this.random = new Random();
    }

    public abstract ServiceChannel produceServiceChannel();

    protected String genRandomString(int length){
        byte[] array = new byte[length];
        random.nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
