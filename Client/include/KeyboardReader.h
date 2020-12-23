//
// Created by spl211 on 22/12/2020.
//

#ifndef SPL_NET_KEYBOARDREADER_H
#define SPL_NET_KEYBOARDREADER_H

using namespace std;

class KeyBoardReader {

public:
    KeyBoardReader(ConnectionHandler* connectionHandler, bool* toLogout, bool* toTerminate);

    void run();
    void shortToBytes(short num, char* bytesArray);
private:
    ConnectionHandler* connectionHandler;
    bool* toLogout;
    bool* toTerminate;
};

#endif //SPL_NET_KEYBOARDREADER_H
