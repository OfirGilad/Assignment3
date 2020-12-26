package bgu.spl.net.api;

import bgu.spl.net.srv.Database;

public class CRSMessagingProtocol implements MessagingProtocol<Message> {

    private final Database database;
    private boolean shouldTerminate = false;
    private boolean isLoggedIn = false;
    private int opCode;
    private String username;
    private String password;
    private String userType;
    private int courseNum;
    private String studentUsername;

    public CRSMessagingProtocol (Database database) {
        this.database = database;
    }

    public Message process(Message msg) {
        opCode = msg.getOpCode();
        switch (opCode) {
            case 1:
                username = ((AdminRegister) msg).getUsername();
                password = ((AdminRegister) msg).getPassword();
                break;
            case 2:
                username = ((StudentRegister) msg).getUsername();
                password = ((StudentRegister) msg).getPassword();
                break;
            case 3:
                username = ((LoginRequest) msg).getUsername();
                password = ((LoginRequest) msg).getPassword();
                break;
            case 4:
                break;
            case 5:
                courseNum = ((RegisterToCourse) msg).getCourseNumber();
                break;
            case 6:
                courseNum = ((CheckKdamCourse) msg).getCourseNumber();
                break;
            case 7:
                courseNum = ((PrintCourseStatus) msg).getCourseNumber();
                break;
            case 8:
                studentUsername = ((PrintStudentStatus) msg).getStudentUsername();
                break;
            case 9:
                courseNum = ((CheckIfRegistered) msg).getCourseNumber();
                break;
            case 10:
                courseNum = ((UnregisterToCourse) msg).getCourseNumber();
                break;
            case 11:
                break;
        }
        return runCommand();
    }

    public boolean shouldTerminate() {
        return shouldTerminate;
    }


    private Message runCommand () {
        Message outPutMessage = null;
        boolean booleanResult;
        String stringResult;
        switch (opCode) {
            //ADMINREG
            case 1:
                booleanResult = database.registerUser(username, password, "Admin");
                if (booleanResult) {
                    outPutMessage = new Acknowledgement(opCode);
                }
                else {
                    outPutMessage = new Error(opCode);
                }
                break;
            //STUDENTREG
            case 2:
                booleanResult = database.registerUser(username, password, "User");
                if (booleanResult) {
                    outPutMessage = new Acknowledgement(opCode);
                }
                else {
                    outPutMessage = new Error(opCode);
                }
                break;
            //LOGIN
            case 3:
                booleanResult = database.login(username, password);
                if (booleanResult && !isLoggedIn) {
                    isLoggedIn = true;
                    userType = database.userType(username);
                    outPutMessage = new Acknowledgement(opCode);
                }
                else {
                    outPutMessage = new Error(opCode);
                }
                break;
            //LOGOUT
            case 4:
                if (isLoggedIn) {
                    isLoggedIn = false;
                    database.logout(username);
                    outPutMessage = new Acknowledgement(opCode);
                }
                else {
                    outPutMessage = new Error(opCode);
                }
                break;
            //COURSEREG
            case 5:
                booleanResult = database.registerToCourse(username, courseNum);
                if (booleanResult) {
                    outPutMessage = new Acknowledgement(opCode);
                }
                else {
                    outPutMessage = new Error(opCode);
                }
                break;
            //KDAMCHECK
            case 6:
                stringResult = database.KdamCheck(courseNum);
                if (!stringResult.equals("false")) {
                    outPutMessage = new Acknowledgement(opCode);
                    ((Acknowledgement) outPutMessage).setKdamCoursesList(stringResult); //Contains Kdam Courses List
                }
                else {
                    outPutMessage = new Error(opCode);
                }
                break;
            //COURSESTAT
            case 7:
                courseNum = ((PrintCourseStatus) msg).getCourseNumber();
                break;
            //STUDENTSTAT
            case 8:
                studentUsername = ((PrintStudentStatus) msg).getStudentUsername();
                break;
            //ISREGISTER
            case 9:
                courseNum = ((CheckIfRegistered) msg).getCourseNumber();
                break;
            //UNREGISTER
            case 10:
                courseNum = ((UnregisterToCourse) msg).getCourseNumber();
                break;
            //MYCOURSES
            case 11:
                break;
        }
        return outPutMessage;

        String outPutMessage = "";
        //boolean booleanResult;
        //String stringResult;

        //ADMINREG
        if (opCode == 1) {
            booleanResult = database.registerUser(username, password, "Admin");
            if (booleanResult) {
                outPutMessage += "12 1";
            }
            else {
                outPutMessage += "13 1";
            }
        }

        //STUDENTREG
        if (opCode == 2) {
            booleanResult = database.registerUser(username, password, "User");
            if (booleanResult) {
                outPutMessage += "12 2";
            }
            else {
                outPutMessage += "13 2";
            }
        }

        //LOGIN
        if (opCode == 3) {
            booleanResult = database.login(username, password);
            if (booleanResult && !isLoggedIn) {
                isLoggedIn = true;
                userType = database.userType(username);
                outPutMessage += "12 3";
            }
            else {
                outPutMessage += "13 3";
            }
        }

        //LOGOUT
        if (opCode == 4) {
            if (isLoggedIn) {
                isLoggedIn = false;
                database.logout(username);
                outPutMessage += "12 4";
            }
            else {
                outPutMessage += "13 4";
            }
        }

        //COURSEREG
        if (opCode == 5) {
            booleanResult = database.registerToCourse(username, courseNum);
            if (booleanResult) {
                outPutMessage += "12 5";
            }
            else {
                outPutMessage += "13 5";
            }
        }

        //KDAMCHECK
        if (opCode == 6) {
            stringResult = database.KdamCheck(courseNum);
            if (!stringResult.equals("false")) {
                outPutMessage += "12 6 ";
                outPutMessage += stringResult; //Contains Kdam Courses List
            }
            else {
                outPutMessage += "13 6";
            }
        }

        //COURSESTAT
        if (opCode == 7) {
            if (userType.equals("Admin")) {
                booleanResult = database.checkIfCourseExists(courseNum);
                if (booleanResult) {
                    outPutMessage += "12 7 ";
                    outPutMessage += database.courseStatsSeatsAvailable(courseNum) + " ";
                    outPutMessage += database.courseStatsSeatsAvailable(courseNum);
                }
                else {
                    outPutMessage += "13 7";
                }
            }
            else {
                outPutMessage += "13 7";
            }
        }

        //STUDENTSTAT
        if (opCode == 8) {
            if (isLoggedIn && userType.equals("Admin")) {
                booleanResult = database.checkIfStudentExists(studentUsername);
                if (booleanResult) {
                    outPutMessage += "12 8 ";
                    outPutMessage += database.studentStats(studentUsername);
                }
                else {
                    outPutMessage += "13 8";
                }
            }
            else {
                outPutMessage += "13 8";
            }
        }

        //ISREGISTER
        if (opCode == 9) {
            if (isLoggedIn && userType.equals("Student") & database.checkIfCourseExists(courseNum)) {
                outPutMessage += "12 9 ";
                outPutMessage += database.isRegistered(username, courseNum);
            }
            else {
                outPutMessage += "13 9";
            }
        }

        //UNREGISTER
        if (opCode == 10) {
            if (isLoggedIn && userType.equals("Student") & database.checkIfCourseExists(courseNum)) {
                booleanResult = database.unregisterToCourse(username, courseNum);
                if (booleanResult) {
                    outPutMessage += "12 10";
                }
            }
            else {
                outPutMessage += "13 10";
            }
        }

        //MYCOURSES
        if (opCode == 11) {
            if (isLoggedIn && userType.equals("Student")) {
                outPutMessage += "12 11 ";
                outPutMessage += database.studentStats(username);
            }
            else {
                outPutMessage += "13 11";
            }
        }

        opCode = -1;
        isMessageReady = false;
        //return outPutMessage;
        return null;
    }
}