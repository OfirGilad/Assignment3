package bgu.spl.net.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CRSMessageEncoderDecoder implements MessageEncoderDecoder <Message> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private short opCode = -1;
    private short courseNum = -1;
    private String username = null;
    private String password = null;
    private int len = 0;
    private int byteIndex = 0;
    private final int[] dataGetter = new int[2];
    private boolean isEndOfMessage = false;

    @Override
    public Message decodeNextByte(byte nextByte) {
        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
        //this allow us to do the following comparison
        //if (nextByte == '\n') {
        //    byteIndex = 0;
        //    return popString();
        //}
        if (byteIndex == 0) {
            dataGetter[0] = byteIndex;
        }
        if (byteIndex == 1) {
            dataGetter[1] = byteIndex;
            opCode = bytesToShort(dataGetter[0], dataGetter[1]);
        }
        if (opCode != -1) {
            if (opCode == 1 || opCode == 2 || opCode == 3) {
                if (username == null && nextByte == '\0') {
                    username = popString();
                }
                else if (password == null && nextByte == '\0') {
                    password = popString();
                    isEndOfMessage = true;
                }
                else {
                    pushByte(nextByte);
                }
            }
            if (opCode == 4 || opCode == 11) {
                isEndOfMessage = true;
            }
            if (opCode == 5 || opCode == 6 || opCode == 7 || opCode == 9 || opCode == 10) {
                if (byteIndex == 2) {
                    dataGetter[0] = byteIndex;
                }
                if (byteIndex == 3) {
                    dataGetter[1] = byteIndex;
                    courseNum = bytesToShort(dataGetter[0], dataGetter[1]);
                    isEndOfMessage = true;
                }
            }
            if (opCode == 8) {
                if (username == null && nextByte == '\0') {
                    username = popString();
                    isEndOfMessage = true;
                }
                else {
                    pushByte(nextByte);
                }
            }
        }
        byteIndex++;
        if (isEndOfMessage) {
            //Get and Reset all data
            int opCodeToSend = opCode;
            int courseNumToSend = courseNum;
            String usernameToSend = username;
            String passwordToSend = password;

            opCode = -1;
            courseNum = -1;
            username = null;
            password = null;
            byteIndex = 0;
            isEndOfMessage = false;

            switch (opCodeToSend) {
                case 1:
                    return new AdminRegister(usernameToSend, passwordToSend);
                case 2:
                    return new StudentRegister(usernameToSend, passwordToSend);
                case 3:
                    return new LoginRequest(usernameToSend, passwordToSend);
                case 4:
                    return new LogoutRequest();
                case 5:
                    return new RegisterToCourse(courseNumToSend);
                case 6:
                    return new CheckKdamCourse(courseNumToSend);
                case 7:
                    return new PrintCourseStatus(courseNumToSend);
                case 8:
                    return new PrintStudentStatus(usernameToSend);
                case 9:
                    return new CheckIfRegistered(courseNumToSend);
                case 10:
                    return new UnregisterToCourse(courseNumToSend);
                case 11:
                    return new CheckMyCurrentCourses();
            }
        }
        return null; //not a line yet
    }

    @Override
    public byte[] encode(Message message) {
        String[] messageToEncode = new String[1]; //message.split(" ");
        byte[] opCode1 = shortToBytes(Short.parseShort(messageToEncode[0]));
        byte[] opCode2 = shortToBytes(Short.parseShort(messageToEncode[1]));
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

    private byte[] shortToBytes(short opCode) {
        byte[] opCodeBytes = new byte[2];
        opCodeBytes[0] = (byte) ((opCode >> 8) & 0xFF);
        opCodeBytes[1] = (byte) (opCode & 0xFF);
        return opCodeBytes;
    }

    private short bytesToShort(int index1, int index2){
        byte[]byteArr=new byte[2];
        byteArr[0] = bytes[index1];
        byteArr[1] = bytes[index2];
        short answer = (short)((byteArr[0] & 0xff) << 8);
        answer += (short)(byteArr[1] & 0xff);
        return answer;
    }
}
