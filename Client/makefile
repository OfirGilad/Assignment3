CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system

all: EchoClient
	g++ -o bin/echoExample bin/connectionHandler.o bin/echoClient.o bin/KeyBoardReader.o $(LDFLAGS)

EchoClient: bin/connectionHandler.o bin/echoClient.o bin/KeyBoardReader.o

bin/connectionHandler.o: src/connectionHandler.cpp
	g++ $(CFLAGS) -o bin/connectionHandler.o src/connectionHandler.cpp

bin/echoClient.o: src/echoClient.cpp
	g++ $(CFLAGS) -o bin/echoClient.o src/echoClient.cpp

bin/KeyBoardReader.o: src/KeyBoardReader.cpp
	g++ $(CFLAGS) -o bin/KeyBoardReader.o src/KeyBoardReader.cpp

.PHONY: clean
clean:
	rm -f bin/*
