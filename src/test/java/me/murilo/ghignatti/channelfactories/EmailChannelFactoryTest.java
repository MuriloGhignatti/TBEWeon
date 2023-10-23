package me.murilo.ghignatti.channelfactories;

import me.murilo.ghignatti.servicechannels.Email;
import me.murilo.ghignatti.servicechannels.ServiceChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailChannelFactoryTest {

    private final EmailChannelFactory factory = EmailChannelFactory.getInstance();
    @Test
    void produceServiceChannel() {
        ServiceChannel email = factory.produceServiceChannel();
        assertTrue(factory.produceServiceChannel() instanceof  Email);
        assertTrue(email.getSource().contains("@"));
    }
}