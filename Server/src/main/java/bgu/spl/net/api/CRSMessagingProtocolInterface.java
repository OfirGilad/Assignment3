package bgu.spl.net.api;

public interface CRSMessagingProtocolInterface<T> extends MessagingProtocol<T>{

    void start(String userName);
    T process(T msg);
    boolean shouldTerminate();
}