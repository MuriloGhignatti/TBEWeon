package me.murilo.ghignatti.servicechannels;

public class Chat extends ServiceChannel {

    public Chat(String source, String target){
        super(source, target);
    }

    @Override
    public void consume() {
        System.out.println(this.toString());
    }

    @Override
    String getChannelType() {
        return "Chat";
    }
    
}
