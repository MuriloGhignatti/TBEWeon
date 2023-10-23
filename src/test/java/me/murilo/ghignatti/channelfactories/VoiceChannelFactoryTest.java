package me.murilo.ghignatti.channelfactories;

import me.murilo.ghignatti.servicechannels.ServiceChannel;
import me.murilo.ghignatti.servicechannels.Voice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoiceChannelFactoryTest {

    private final VoiceChannelFactory factory = VoiceChannelFactory.getInstance();

    @Test
    void produceServiceChannel() {
        ServiceChannel voice = factory.produceServiceChannel();
        assertTrue(voice instanceof Voice);
    }
}