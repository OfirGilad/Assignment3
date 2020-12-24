package bgu.spl.net.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CRSMessageEncoderDecoder implements MessageEncoderDecoder <String> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    private int byteIndex = 0;
    private int[] opCodeGetter = new int[2];
    private int index = 0;

    @Override
    public String decodeNextByte(byte nextByte) {
        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
        //this allow us to do the following comparison
        if (nextByte == '\n') {
            byteIndex = 0;
            return popString();
        }

        if (byteIndex == 0) {
            opCodeGetter[0] = index;
            byteIndex++;
        }
        if (byteIndex == 1) {
            opCodeGetter[1] = index;
            Short opCode = bytesToOpCodeOrShort(opCodeGetter[0], opCodeGetter[1]);
            pushByte(Byte.parseByte(opCode.toString()));
            byteIndex = 0;
        }
        else {
            pushByte(nextByte);
        }
        return null; //not a line yet
    }

    @Override
    public byte[] encode(String message) {
        String[] messageToEncode = message.split(" ");
        byte[] opCode1 = opCodeToBytes(Short.parseShort(messageToEncode[0]));
        byte[] opCode2 = opCodeToBytes(Short.parseShort(messageToEncode[1]));
        byte[] data1 = null;
        byte[] data2 = null;
        ByteArrayOutputStream messageToReturn = new ByteArrayOutputStream();
        if (messageToEncode.length == 3) {
            data1 = (messageToEncode[2] + '\0' + '\n').getBytes();
            try {
                messageToReturn.write(opCode1);
                messageToReturn.write(opCode2);
                messageToReturn.write(data1);
            }
            catch (IOException ignored) { }
        }
        if (messageToEncode.length == 4) {
            data1 = (messageToEncode[2] + '\0').getBytes();
            data2 = (messageToEncode[3] + '\0' + '\n').getBytes();
            try {
                messageToReturn.write(opCode1);
                messageToReturn.write(opCode2);
                messageToReturn.write(data1);
                messageToReturn.write(data2);
            }
            catch (IOException ignored) { }
        }
        return messageToReturn.toByteArray(); //uses utf8 by default
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    private String popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        return result;
    }

    private byte[] opCodeToBytes (short opCode) {
        byte[] opCodeBytes = new byte[2];
        opCodeBytes[0] = (byte) ((opCode >> 8) & 0xFF);
        opCodeBytes[1] = (byte) (opCode & 0xFF);
        return opCodeBytes;
    }

    private short bytesToOpCodeOrShort(int index1, int index2){
        byte[]byteArr=new byte[2];
        byteArr[0] = bytes[index1];
        byteArr[1] = bytes[index2];
        short answer = (short)((byteArr[0] & 0xff) << 8);
        answer += (short)(byteArr[1] & 0xff);
        return answer;
    }
}
