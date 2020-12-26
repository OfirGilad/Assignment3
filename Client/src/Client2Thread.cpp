#include <stdlib.h>
#include "../include/connectionHandler.h"
#include "../include/ConnectionReader.h"
#include "../include/KeyboardReader.h"
#include <thread>

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/

using namespace std;

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
    ConnectionReader connectionReader(&connectionHandler,toTerminate);
    thread connectionThread(&ConnectionReader::run, &connectionReader);
    thread keyboardThread(&KeyboardReader::run, &keyboardReader);
    connectionThread.join();
    keyboardThread.join();

    delete toTerminate;

    return 0;
}