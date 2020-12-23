package bgu.spl.net.api;

import bgu.spl.net.srv.Database;

public class CRSMessagingProtocol implements CRSMessagingProtocolInterface <String>{

    private final Database database;
    private String username;
    private boolean shouldTerminate = false;

    public CRSMessagingProtocol (Database database) {
        this.database = database;
    }

    public void start(String username) {
        this.username = username;
    }

    public String process(String msg) {
        return "Hi";
    }

    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}