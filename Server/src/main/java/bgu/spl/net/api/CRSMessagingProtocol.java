package bgu.spl.net.api;

import bgu.spl.net.srv.Database;

public class CRSMessagingProtocol implements MessagingProtocol<String> {

    private final Database database;
    private boolean shouldTerminate = false;
    private boolean isLoggedIn = false;
    private int opCode = -1;
    private boolean isDataLeft = false;
    private String username;
    private String password;
    private String userType;
    private int courseNum;
    private String studentUsername;
    private boolean isMessageReady = false;

    public CRSMessagingProtocol (Database database) {
        this.database = database;
    }

    public String process(String msg) {

        if (opCode == -1) {
            opCode = Integer.parseInt(msg);

            if (opCode == 4 | opCode == 11) {
                isMessageReady = true;
                return runCommand();
            }
        }
        else {
            if (opCode == 1 | opCode == 2 | opCode == 3) {
                if (!isDataLeft) {
                    username = msg;
                    isDataLeft = true;
                }
                else {
                    password = msg;
                    isDataLeft = false;
                    opCode = -1;
                    isMessageReady = true;
                }
            }

            if (opCode == 5 | opCode == 6 | opCode == 7 | opCode == 9 | opCode == 10) {
                courseNum = Integer.parseInt(msg);
                isMessageReady = true;
            }

            if (opCode == 8) {
                studentUsername = msg;
                isMessageReady = true;
            }

            if (isMessageReady) {
                return runCommand();

            }
        }
        return null;
    }

    public boolean shouldTerminate() {
        return shouldTerminate;
    }


    private String runCommand () {
        String outPutMessage = "";
        boolean booleanResult;
        String stringResult;

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
        return outPutMessage;
    }
}