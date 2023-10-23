package me.murilo.ghignatti.servicechannels;

public class Voice extends ServiceChannel {

    public Voice(String source, String target)  {
        super(source, target);
    }

    @Override
    public void consume() {
        System.out.println(this.toString());
    }

    @Override
    String getChannelType() {
        return "Voice";
    }
}
