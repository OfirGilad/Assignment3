#include "../include/connectionHandler.h"
#include "../include/KeyboardReader.h"
#include <thread>

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/

using namespace std;

//This class is used to convert data from Bytes to Short
short bytesToShort(char* bytesArray) {
    short result = (short)((bytesArray[0] & 0xff) << 8);
    result += (short)(bytesArray[1] & 0xff);
    return result;
}

int main (int argc, char *argv[]) {
    //if (argc < 3) {
    //    std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
    //    return -1;
    //}
    //std::string host = argv[1];
    //short port = atoi(argv[2]);

    string host = "127.0.0.1";
    short port = 7777;
    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }

    bool *toTerminate = new bool;
    *toTerminate = false;

    KeyboardReader keyboardReader(&connectionHandler,toTerminate);
    thread keyboardThread(&KeyboardReader::run, &keyboardReader);

    while (!(*toTerminate)) {
        char* opCodeArray = new char[2];
        connectionHandler.getBytes(opCodeArray, 2);
        short opCode = bytesToShort(opCodeArray);
        string outPut;

        //TODO: update code
        if (opCode==12){

            outPut = "ACK";
            connectionHandler.getBytes(opCodeArray, 2);
            short msgOpCode = bytesToShort(opCodeArray);
            outPut = outPut + " " + to_string(msgOpCode);
            string msgData;

            if(msgOpCode == 6 | msgOpCode == 9 | msgOpCode == 11){
                connectionHandler.getLine(msgData);
                outPut= outPut + '\n' + msgData;
            }

            if (msgOpCode == 7) {
                connectionHandler.getLine(msgData);
                outPut = outPut + '\n' + "Course:" + msgData;
                connectionHandler.getLine(msgData);
                outPut = outPut + '\n' + "Seats Available:" + msgData;
                connectionHandler.getLine(msgData);
                outPut = outPut + '\n' + "Student Registered:" + msgData;

            }

            if (msgOpCode == 8) {
                connectionHandler.getLine(msgData);
                outPut = outPut + '\n' + "Student:" + msgData;
                connectionHandler.getLine(msgData);
                outPut = outPut + '\n' + "Courses:" + msgData;
            }

            if(msgOpCode == 4){
                *toTerminate=true;
            }
        }

        if (opCode==13){
            outPut="ERROR";

            connectionHandler.getBytes(opCodeArray, 2);
            short errorCode = bytesToShort(opCodeArray);
            outPut = outPut + " " + to_string(errorCode);
        }
        if (outPut != "")
            cout << outPut << endl;

        delete[] opCodeArray;
    }

    keyboardThread.join();
    delete toTerminate;
    return 0;
}

