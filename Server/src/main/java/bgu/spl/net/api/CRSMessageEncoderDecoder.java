package bgu.spl.net.api;

public class CRSMessageEncoderDecoder implements MessageEncoderDecoder <String> {
    private byte[] bytesArray;
    private short opCode;
    private int messageLength;
    private int numberOfZerosNeeded;
    private int [] zerosIndex;
    private int numberOfZeroCounter;
    private int numberOfZerosToPrintCounter;

    public CRSMessageEncoderDecoder(){
        bytesArray = new byte[1 << 10]; //start with 1k
        zerosIndex = new int [1 << 10];
        opCode = -1;
        messageLength = 0;
        numberOfZerosNeeded = -1;
        numberOfZeroCounter = 0;
        numberOfZerosToPrintCounter = 0;
    }

    public String decodeNextByte(byte nextByte) {
        return null;
    }

    @Override
    public byte[] encode(String message) {
        return null;
    }
}
