package bgu.spl.net.api.Messages;

public abstract class Message {
    protected int opCode;
    protected byte[] response;

    public Message(int opCode){
        this.opCode=opCode;
    }

    public int getOpCode() {
        return opCode;
    }

    public byte[] sendResponse() {
        return response;
    }

}

class AdminRegister extends Message {
    private String username;
    private String password;

    public AdminRegister(int opCode, String username, String password) {
        super(opCode);
        this.username = username;
        this.password = password;
    }
}

class StudentRegister extends Message {
    private String username;
    private String password;

    public StudentRegister(int opCode, String username, String password) {
        super(opCode);
        this.username = username;
        this.password = password;
    }
}

class LoginRequest extends Message {
    private String username;
    private String password;

    public LoginRequest(int opCode, String username, String password) {
        super(opCode);
        this.username = username;
        this.password = password;
    }
}

class LogoutRequest extends Message {

    public LogoutRequest(int opCode) {
        super(opCode);
    }
}