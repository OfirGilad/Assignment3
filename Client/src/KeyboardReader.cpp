#include <stdlib.h>
#include "../include/connectionHandler.h"
#include "../include/ConnectionReader.h"
#include "../include/KeyboardReader.h"

#include <boost/algorithm/string.hpp>

using namespace std;

KeyBoardReader::KeyBoardReader(ConnectionHandler* connectionHandler, bool* toLogout, bool* toTerminate): connectionHandler(connectionHandler), toLogout(toLogout), toTerminate(toTerminate)  {}

void KeyBoardReader::run() {
    *toLogout = false;
    *toTerminate = false;

    while (!(*toTerminate)) {
        bool isOut = false;
        while (*toLogout) {
            if (*toTerminate) {
                isOut = true;
                break;
            }
        }
        if (isOut)
            break;

        const short bufsize = 1024;
        char buf[bufsize];
        cin.getline(buf, bufsize);
        string line(buf);
        vector <string> onScreenText;

        boost::split(onScreenText, line, boost::is_any_of(" "));

        char dataToBytes[2];

        if (onScreenText[0] == "ADMINREG") {
            shortToBytes(1, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
            connectionHandler->sendLine(onScreenText[1]);
            connectionHandler->sendLine(onScreenText[2]);

        }

        if (onScreenText[0] == "STUDENTREG") {
            shortToBytes(2, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
            connectionHandler->sendLine(onScreenText[1]);
            connectionHandler->sendLine(onScreenText[2]);
        }

        if (onScreenText[0] == "LOGIN") {
            shortToBytes(3, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
            connectionHandler->sendLine(onScreenText[1]);
            connectionHandler->sendLine(onScreenText[2]);
        }

        if (onScreenText[0] == "LOGOUT") {
            shortToBytes(4, dataToBytes);
            connectionHandler->sendBytes(opCode, 2);
            *toLogout = true;
        }

        if (onScreenText[0] == "COURSEREG") {
            shortToBytes(5, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
            short courseNum;
            try {
                courseNum = boost::lexical_cast<short>(onScreenText[1]);
            }
            catch(bad_lexical_cast &) {
                //Do nothing
            }
            shortToBytes(courseNum, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
        }

        if (onScreenText[0] == "KDAMCHECK") {
            shortToBytes(6, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
            short courseNum;
            try {
                courseNum = boost::lexical_cast<short>(onScreenText[1]);
            }
            catch(bad_lexical_cast &) {
                //Do nothing
            }
            shortToBytes(courseNum, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
        }

        if (onScreenText[0] == "COURSESTAT") {
            shortToBytes(7, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
            short courseNum;
            try {
                courseNum = boost::lexical_cast<short>(onScreenText[1]);
            }
            catch(bad_lexical_cast &) {
                //Do nothing
            }
            shortToBytes(courseNum, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
        }

        if (onScreenText[0] == "STUDENTSTAT") {
            shortToBytes(8, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
            connectionHandler->sendLine(onScreenText[1]);
        }

        if (onScreenText[0] == "ISREGISTERED") {
            shortToBytes(9, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
            short courseNum;
            try {
                courseNum = boost::lexical_cast<short>(onScreenText[1]);
            }
            catch(bad_lexical_cast &) {
                //Do nothing
            }
            shortToBytes(courseNum, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
        }

        if (onScreenText[0] == "UNREGISTER") {
            shortToBytes(10, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
            short courseNum;
            try {
                courseNum = boost::lexical_cast<short>(onScreenText[1]);
            }
            catch(bad_lexical_cast &) {
                //Do nothing
            }
            shortToBytes(courseNum, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
        }

        if (onScreenText[0] == "MYCOURSES") {
            shortToBytes(11, dataToBytes);
            connectionHandler->sendBytes(dataToBytes, 2);
        }
    }
}

//This class is used to convert data from Short to Bytes
void KeyBoardReader::shortToBytes(short num, char* bytesArray) {
    bytesArray[0] = ((num >> 8) & 0xFF);
    bytesArray[1] = (num & 0xFF);
}