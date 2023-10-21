package me.murilo.ghignatti;

import me.murilo.ghignatti.data.OpenChannelDAO;

public class Consumer extends Thread {

    private OpenChannelDAO dao;

    public Consumer(OpenChannelDAO dao){
        this.dao = dao;
    }
    

    @Override
    public void run() {
        dao.closeChannel().consume();
    }
}
