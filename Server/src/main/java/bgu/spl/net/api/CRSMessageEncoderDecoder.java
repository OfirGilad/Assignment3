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
        if (nextByte=='\0'){
            zerosIndex[numberOfZeroCounter]=messageLength;
            numberOfZeroCounter++;
        }

        if (numberOfZeroCounter == numberOfZerosNeeded) {
            //return createAMessage();
        }
        //pushByte(nextByte);
        if (opCode==3 || opCode==7)
            //return createAMessage();
        return null; //not a line yet
        return null;
    }

    @Override
    public byte[] encode(String message) {
        //return ((ServerToClientMsg)message).getByteMsg();
        return null;
    }
}
