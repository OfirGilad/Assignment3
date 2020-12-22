//
// Created by spl211 on 22/12/2020.
//

#include <KeyBoardReader.h>

#include "KeyBoardReader.h"
#include "connectionHandler.h"
#include <boost/algorithm/string.hpp>

using namespace std;

KeyBoardReader::KeyBoardReader(ConnectionHandler* connectionHandler, bool* toLogout, bool* toTerminate): connectionHandler(connectionHandler), toLogout(toLogout), toTerminate(toTerminate)  {}

void KeyBoardReader::run() {
    *toLogout = false;
    *toTerminate = false;

    while (!(*toTerminate)) {
        bool isOut = false;
        while (*toLogout == true) {
            if (*toTerminate == true) {
                isOut = true;
                break;
            }
        }
        if (isOut)
            break;

        const short bufSize = 1024;
        char buf[bufSize];
        cin.getline(buf, bufSize);
        string line(buf);

        int length = line.length();
        vector <string> onScreenText;

        boost::split(onScreenText, line, boost::is_any_of(" "));

        char opCode[2];

        if (onScreenText[0] == "ADMINREG") {
            shortToBytes(1, opCode);
            connectionHandler->sendBytes(opCode, 2);
            connectionHandler->sendLine(onScreenText[1]);
            connectionHandler->sendLine(onScreenText[2]);

        }

        if (onScreenText[0] == "STUDENTREG") {
            shortToBytes(2, opCode);
            connectionHandler->sendBytes(opCode, 2);
            connectionHandler->sendLine(onScreenText[1]);
            connectionHandler->sendLine(onScreenText[2]);
        }

        if (onScreenText[0] == "LOGIN") {
            shortToBytes(3, opCode);
            connectionHandler->sendBytes(opCode, 3);
            connectionHandler->sendLine(onScreenText[1]);
            connectionHandler->sendLine(onScreenText[2]);
        }

        if (onScreenText[0] == "LOGOUT") {
            shortToBytes(4, opCode);
            connectionHandler->sendBytes(opCode, 4);
            *toLogout = true;
        }

        if (onScreenText[0] == "COURSEREG") {
            shortToBytes(5, opCode);
            connectionHandler->sendBytes(opCode, 5);
            connectionHandler->sendLine(onScreenText[1]);
        }

        if (onScreenText[0] == "KDAMCHECK") {
            shortToBytes(6, opCode);
            connectionHandler->sendBytes(opCode, 6);
            connectionHandler->sendLine(onScreenText[1]);
        }

        if (onScreenText[0] == "COURSESTAT") {
            shortToBytes(7, opCode);
            connectionHandler->sendBytes(opCode, 7);
            connectionHandler->sendLine(onScreenText[1]);
        }

        if (onScreenText[0] == "STUDENTSTAT") {
            shortToBytes(8, opCode);
            connectionHandler->sendBytes(opCode, 8);
            connectionHandler->sendLine(onScreenText[1]);
        }

        if (onScreenText[0] == "ISREGISTERED") {
            shortToBytes(9, opCode);
            connectionHandler->sendBytes(opCode, 9);
            connectionHandler->sendLine(onScreenText[1]);
        }

        if (onScreenText[0] == "UNREGISTER") {
            shortToBytes(10, opCode);
            connectionHandler->sendBytes(opCode, 10);
            connectionHandler->sendLine(onScreenText[1]);
        }

        if (onScreenText[0] == "MYCOURSES") {
            shortToBytes(11, opCode);
            connectionHandler->sendBytes(opCode, 11);
        }
    }
}

void KeyBoardReader::shortToBytes(short num, char* bytesArray) {
    bytesArray[0] = ((num >> 8) & 0xFF);
    bytesArray[1] = (num & 0xFF);
}