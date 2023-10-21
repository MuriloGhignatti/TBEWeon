package me.murilo.ghignatti.servicechannels;

public class Email extends ServiceChannel {

    public Email(String source, String target){
        super(source, target);
    }

    @Override
    void consume() {
        System.out.println(this.toString());
    }

    @Override
    String getChannelType() {
        return "Email";
    }
    
}
