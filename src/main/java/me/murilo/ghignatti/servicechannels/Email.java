package me.murilo.ghignatti.servicechannels;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Email
 */
public class Email extends ServiceChannel {

    public Email(UUID id, String source, String target, Timestamp timestamp) {
        super(id, source, target, timestamp);
    }

    public Email(String source, String target){
        super(source, target);
    }
    
}
