CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system

all: Client
	g++ -o bin/client bin/connectionHandler.o bin/ConnectionReader.o bin/CRSClient.o bin/KeyBoardReader.o $(LDFLAGS)

Client: bin/connectionHandler.o bin/ConnectionReader.o bin/CRSClient.o bin/KeyBoardReader.o

bin/connectionHandler.o: src/connectionHandler.cpp
	g++ $(CFLAGS) -o bin/connectionHandler.o src/connectionHandler.cpp

bin/ConnectionReader.o: src/ConnectionReader.cpp
	g++ $(CFLAGS) -o bin/ConnectionReader.o src/ConnectionReader.cpp

bin/CRSClient.o: src/echoClient.cpp
	g++ $(CFLAGS) -o bin/CRSClient.o src/CRSClient.cpp

bin/KeyBoardReader.o: src/KeyBoardReader.cpp
	g++ $(CFLAGS) -o bin/KeyBoardReader.o src/KeyBoardReader.cpp

.PHONY: clean
clean:
	rm -f bin/*
