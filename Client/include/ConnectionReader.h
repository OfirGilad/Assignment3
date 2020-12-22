//
// Created by spl211 on 22/12/2020.
//

#ifndef SPL_NET_CONNECTIONREADER_H
#define SPL_NET_CONNECTIONREADER_H

sing namespace std;

class ConnectionReader {

public:
    ConnectionReader(ConnectionHandler* connectionHandler, bool* toLogout, bool* toTerminate);

    void run();
    void shortToBytes(short num, char* bytesArray);
private:
    ConnectionHandler* connectionHandler;
    bool* toLogout;
    bool* toTerminate;
};

#endif //SPL_NET_CONNECTIONREADER_H
