package me.murilo.ghignatti.data;

import me.murilo.ghignatti.channelfactories.ChatChannelFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OpenChannelDAOImplTest {

    private final OpenChannelDAO dao = OpenChannelDAOImpl.getInstance();

    @Test
    @Order(1)
    void addOpenChannel() {
        assertTrue(dao.isQueueEmpty());
        dao.addOpenChannel(ChatChannelFactory.getInstance().produceServiceChannel());
        assertFalse(dao.isQueueEmpty());
    }

    @Test
    @Order(2)
    void getOpenChannel() {
        assertFalse(dao.isQueueEmpty());
        dao.getOpenChannel(1);
        assertTrue(dao.isQueueEmpty());
    }

    @Test
    @Order(3)
    void getProduced() {
        assertEquals(1, dao.getProduced());
        assertEquals(1, dao.getConsumed());
        dao.addOpenChannel(ChatChannelFactory.getInstance().produceServiceChannel());
        assertEquals(2, dao.getProduced());
        assertEquals(1, dao.getConsumed());
    }

    @Test
    @Order(4)
    void getConsumed() {
        assertEquals(2, dao.getProduced());
        assertEquals(1, dao.getConsumed());
        dao.addOpenChannel(ChatChannelFactory.getInstance().produceServiceChannel());
        dao.getOpenChannel(1);
        assertEquals(3, dao.getProduced());
        assertEquals(2, dao.getConsumed());
    }
}