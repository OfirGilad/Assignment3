package bgu.spl.net.api;

import bgu.spl.net.srv.Database;

public class CRSMessagingProtocol implements MessagingProtocol <String>{

    private final Database database;
    private boolean shouldTerminate = false;

    public CRSMessagingProtocol (Database database) {
        this.database = database;
    }

    public String process(String msg) {
        return "Hi";
    }

    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}