package me.murilo.ghignatti.channelfactories;

import me.murilo.ghignatti.servicechannels.Chat;
import me.murilo.ghignatti.servicechannels.ServiceChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatChannelFactoryTest {

    private final ChatChannelFactory factory = ChatChannelFactory.getInstance();
    @Test
    void produceServiceChannel() {
        ServiceChannel chat = factory.produceServiceChannel();
        assertTrue(factory.produceServiceChannel() instanceof Chat);
    }
}