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

        //Test of short Convertor
        short x = 500;
        byte[] arr = opCodeToBytes(x);
        short high = (1 & 0x00ff);
        short low = -12;
        short val = (short) (((high & 0xFF) << 8) | (low & 0xFF));
        System.out.println(arr[0] + " " + arr[1]);
        System.out.println(val);

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

    public static byte[] opCodeToBytes (short opCode) {
        byte[] opCodeBytes = new byte[2];
        opCodeBytes[0] = (byte) ((opCode >> 8) & 0xFF);
        opCodeBytes[1] = (byte) (opCode & 0xFF);
        return opCodeBytes;
    }
}