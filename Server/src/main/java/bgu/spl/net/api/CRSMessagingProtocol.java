package bgu.spl.net.api;

import bgu.spl.net.srv.Database;

public class CRSMessagingProtocol implements CRSMessagingProtocolInterface<String> {

    private final Database database;
    private boolean shouldTerminate = false;
    private boolean isLoggedIn = false;
    private int opCode = -1;
    private boolean isDataLeft = false;
    private String username;
    private String password;
    private int courseNum;
    private String studentUsername;
    private boolean isMessageReady = false;

    public CRSMessagingProtocol (Database database) {
        this.database = database;
    }

    @Override
    public void start(String userName) {
        //need to check if required
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
        boolean booleanResult;
        String stringResult;

        if (opCode == 1) {
            booleanResult = database.registerUser(username, password, "Admin");
            if (booleanResult) {
                return "ACT 1";
            }
            else {
                return "ERR 1";
            }
        }

        if (opCode == 2) {
            booleanResult = database.registerUser(username, password, "User");
            if (booleanResult) {
                return "ACT 2";
            }
            else {
                return "ERR 2";
            }
        }

        if (opCode == 3) {
            booleanResult = database.checkLoginInfo(username, password);
            if (booleanResult && !isLoggedIn) {
                isLoggedIn = true;
                return "ACT 3";
            }
            else {
                return "ERR 3";
            }
        }



        isMessageReady = false;
        return "Ans";
    }

}