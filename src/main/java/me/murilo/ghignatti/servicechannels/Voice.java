package me.murilo.ghignatti.servicechannels;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Voice
 */
public class Voice extends ServiceChannel{

    public Voice(UUID id, String source, String target, Timestamp timestamp) {
        super(id, source, target, timestamp);
    }

    public Voice(String source, String target)  {
        super(source, target);
    }
}
