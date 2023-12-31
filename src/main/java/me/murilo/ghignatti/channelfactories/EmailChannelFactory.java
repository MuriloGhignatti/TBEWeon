package me.murilo.ghignatti.channelfactories;

import me.murilo.ghignatti.servicechannels.Email;
import me.murilo.ghignatti.servicechannels.ServiceChannel;

/**
 * EmailChannelProducer
 */
public class EmailChannelFactory extends ChannelFactory{

    private static EmailChannelFactory instance;

    private EmailChannelFactory(){}

    public static EmailChannelFactory getInstance(){
        if (instance == null){
            instance = new EmailChannelFactory();
        }
        return instance;
    }

    @Override
    public ServiceChannel produceServiceChannel() {
        return new Email(genEmail(), genEmail());
    }

    private String genEmail(){
        return genRandomString(6) + '@' + genRandomString(5) + ".com";
    }
}
