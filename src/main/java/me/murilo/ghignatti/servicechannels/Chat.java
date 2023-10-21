package me.murilo.ghignatti.servicechannels;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Chat
 */
public class Chat extends ServiceChannel {

    public Chat(UUID id, String source, String target, Timestamp timestamp) {
        super(id, source, target, timestamp);
    }


    public Chat(String source, String target){
        super(source, target);
    }
    
}
