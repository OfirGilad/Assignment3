CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system

all: Client
	g++ -o bin/client bin/connectionHandler.o bin/ConnectionReader.o bin/echoClient.o bin/KeyboardReader.o $(LDFLAGS)

Client: bin/connectionHandler.o bin/ConnectionReader.o bin/echoClient.o bin/KeyboardReader.o

bin/connectionHandler.o: src/connectionHandler.cpp
	g++ $(CFLAGS) -o bin/connectionHandler.o src/connectionHandler.cpp

bin/ConnectionReader.o: src/ConnectionReader.cpp
	g++ $(CFLAGS) -o bin/ConnectionReader.o src/ConnectionReader.cpp

bin/echoClient.o: src/echoClient.cpp
	g++ $(CFLAGS) -o bin/echoClient.o src/echoClient.cpp

bin/KeyboardReader.o: src/KeyboardReader.cpp
	g++ $(CFLAGS) -o bin/KeyboardReader.o src/KeyboardReader.cpp

.PHONY: clean
clean:
	rm -f bin/*