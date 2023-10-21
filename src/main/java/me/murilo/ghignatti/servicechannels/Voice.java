package me.murilo.ghignatti.servicechannels;

public class Voice extends ServiceChannel {

    public Voice(String source, String target)  {
        super(source, target);
    }

    @Override
    void consume() {
        System.out.println(this.toString());
    }

    @Override
    String getChannelType() {
        return "Voice";
    }
}
