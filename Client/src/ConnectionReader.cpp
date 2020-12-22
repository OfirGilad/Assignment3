//
// Created by spl211 on 22/12/2020.
//

#include <stdlib.h>
#include "../include/ConnectionReader.h"

using namespace std;

ConnectionReader::ConnectionReader(ConnectionHandler* connectionHandler, bool* toLogout, bool* toTerminate): connectionHandler(connectionHandler), toLogout(toLogout), toTerminate(toTerminate)  {}

void ConnectionReader::run() {
    *toLogout = false;
    *toTerminate = false;

    while (!(*toTerminate)) {
        char* opCodeArray = new char[2];
        connectionHandler->getBytes(opCodeArray, 2);
        short opCode = bytesToShort(opCodeArray);
        string outPut;

        //if (opCode==9){

        //    outPut="NOTIFICATION";
        //    char * temp = new char[1];
         //   connectionHandler->getBytes(temp, 1);
         //   if (temp[0]==0)
         //       outPut= outPut+ " PM";
         //   else
          //      outPut= outPut+ " PUBLIC";
         //   string postingUser = "";
         //   connectionHandler->getLine(postingUser);
          //  outPut= outPut +" "+(postingUser.substr(0, (postingUser.size()-1)));
          //  string content = "";
         //   connectionHandler->getLine(content);
         //   outPut= outPut+ " " + (content.substr(0, (content.size()-1)));
        //   delete[] temp;
        //}

        //TODO: update code
        if (opCode==12){

            outPut = "ACK";
            connectionHandler->getBytes(opCodeArray, 2);
            short msgOpCode = bytesToShort(opCodeArray);
            outPut = outPut + " " + to_string(msgOpCode);

            //5-11
            if(msgOpCode == 6 | msgOpCode == 9 | msgOpCode == 11){
                String msgData;
                connectionHandler->getLine(msgData);
                outPut= outPut + '\n' + to_string(msgData);
            }

            if (msgOpCode == 7) {
                String msgData;
                connectionHandler->getLine(msgData);
                outPut = outPut + '\n' + "Course: " + to_string(msgData);
                connectionHandler->getLine(msgData);
                outPut = outPut + '\n' + "Seats Available: " + to_string(msgData);
                connectionHandler->getLine(msgData);
                outPut = outPut + '\n' + "Student Registered: " + to_string(msgData);

            }

            if (msgOpCode == 8) {
                String msgData;
                connectionHandler->getLine(msgData);
                outPut = outPut + '\n' + "Student: " + to_string(msgData);
                connectionHandler->getLine(msgData);
                outPut = outPut + '\n' + "Courses: " + to_string(msgData);
            }

            if(msgOpCode == 4){
                *toTerminate=true;
            }
        }

        if (opCode==13){
            outPut="ERROR";

            connectionHandler->getBytes(opCodeArray, 2);
            short errorCheck = bytesToShort(opCodeArray);
            outPut = outPut + " " + to_string(errorCheck);

            if (errorCheck == 4){
                *toLogout = false;
            }
        }
        if (outPut != "")
            cout << outPut << endl;

        delete[] opCodeArray;

    }
}

short ConnectionReader::bytesToShort(char* bytesArray) {
    short result = (short)((bytesArray[0] & 0xff) << 8);
    result += (short)(bytesArray[1] & 0xff);
    return result;
}