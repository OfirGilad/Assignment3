package bgu.spl.net.srv;

import bgu.spl.net.api.CRSMessageEncoderDecoder;
import bgu.spl.net.api.CRSMessagingProtocol;
import bgu.spl.net.impl.echo.LineMessageEncoderDecoder;
import bgu.spl.net.srv.BaseServer;
import bgu.spl.net.srv.BlockingConnectionHandler;
import bgu.spl.net.srv.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CRSServer {
    public static void main(String[] args)
    {
        //Get port
        int port = Integer.parseInt(args[0]);
        String type = args[1];
        Database serverDatabase = Database.getInstance();

        if(type.equals("tpc"))
        {
            Server.threadPerClient(port, //port
                    () -> new CRSMessagingProtocol(serverDatabase), //protocol factory
                    () -> new CRSMessageEncoderDecoder() //message encoder decoder factory
            ).serve();
        }
        else
        {
            Server.reactor(Runtime.getRuntime().availableProcessors(), port, //port
                    () -> new CRSMessagingProtocol(serverDatabase), //protocol factory
                    () -> new CRSMessageEncoderDecoder() //message encoder decoder factory
            ).serve();
        }
    }
}
