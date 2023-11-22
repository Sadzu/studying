CC = g++ -Wall -Werror -Wextra -std=c++17

all : main

build: main

main : main.o
	$(CC) main.o -g -o main

main.o : main.cpp
	$(CC) -c -g main.cpp

clean :
	rm -rf *.o main