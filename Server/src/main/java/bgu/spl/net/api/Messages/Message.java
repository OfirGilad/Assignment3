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
    public String getUsername(){return username;}
    public String getPassword(){return password;}
}

class StudentRegister extends Message {
    private String username;
    private String password;

    public StudentRegister(int opCode, String username, String password) {
        super(opCode);
        this.username = username;
        this.password = password;
    }
    public String getUsername(){return username;}
    public String getPassword(){return password;}
}

class LoginRequest extends Message {
    private String username;
    private String password;

    public LoginRequest(int opCode, String username, String password) {
        super(opCode);
        this.username = username;
        this.password = password;
    }
    public String getUsername(){return username;}
    public String getPassword(){return password;}
}

class RegisterCourse extends Message {
    private int  courseNumber;

    public RegisterCourse(int opCode, int courseNumber) {
        super(opCode);
        this.courseNumber = courseNumber;
    }
    public int getCouseNum(){return courseNumber;}
}


class KdamCheck extends Message {
    private int  courseNumber;

    public KdamCheck(int opCode, int courseNumber) {
        super(opCode);
        this.courseNumber = courseNumber;
    }
    public int getCouseNum(){return courseNumber;}
}


class CourseStatus extends Message {
    private int  courseNumber;

    public CourseStatus(int opCode, int courseNumber) {
        super(opCode);
        this.courseNumber = courseNumber;
    }
    public int getCouseNum(){return courseNumber;}
}

class StudentStatus extends Message {
    private String username;


    public StudentStatus(int opCode, String username) {
        super(opCode);
        this.username = username;
    }
    public String getUsername(){return username;}
}


class IsRegistered extends Message {
    private int  courseNumber;

    public IsRegistered(int opCode, int courseNumber) {
        super(opCode);
        this.courseNumber = courseNumber;
    }
    public int getCouseNum(){return courseNumber;}
}

class UnregisterCourse extends Message {
    private int  courseNumber;

    public UnregisterCourse(int opCode, int courseNumber) {
        super(opCode);
        this.courseNumber = courseNumber;
    }
    public int getCouseNum(){return courseNumber;}
}

class MyCourses extends Message {

    public MyCourses(int opCode) {
        super(opCode);
    }
}


class LogoutRequest extends Message {

    public LogoutRequest(int opCode) {
        super(opCode);
    }
}