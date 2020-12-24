package bgu.spl.net.srv;

import bgu.spl.net.api.CRSMessageEncoderDecoder;
import bgu.spl.net.api.CRSMessagingProtocol;

public class CRSServer {
    public static void main(String[] args)
    {
        //Get port
        //int port = Integer.parseInt(args[0]);
        //String type = args[1];
        Database serverDatabase = Database.getInstance();

        int port = 7777;
        String type = "tcp";

        if(type.equals("tpc"))
        {
            Server.threadPerClient(port, //port
                    () -> new CRSMessagingProtocol(serverDatabase), //protocol factory
                    CRSMessageEncoderDecoder::new //message encoder decoder factory
            ).serve();
        }
        else
        {
            Server.reactor(Runtime.getRuntime().availableProcessors(), port, //port
                    () -> new CRSMessagingProtocol(serverDatabase), //protocol factory
                    CRSMessageEncoderDecoder::new //message encoder decoder factory
            ).serve();
        }
    }
}