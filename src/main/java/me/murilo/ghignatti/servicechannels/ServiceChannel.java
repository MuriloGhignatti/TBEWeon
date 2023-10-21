package me.murilo.ghignatti.servicechannels;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Abstract ServiceChannel
 */
public abstract class ServiceChannel {
    
    private UUID id;
    private String source;
    private String target;
    private Timestamp timestamp;

    public ServiceChannel(UUID id, String source, String target,
            Timestamp timestamp){
        this.id = id;
        this.source = source;
        this.target = target;
        this.timestamp = timestamp;
    }

    public ServiceChannel(String source, String target){
        this(UUID.randomUUID(), source, target,
                new Timestamp(System.currentTimeMillis()));
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
