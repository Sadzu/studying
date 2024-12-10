CC = g++ -Wall -Werror -Wextra

all : main main_debug

build : main

debug : main_debug

main : main.o methods.o
	$(CC) main.o methods.o -o main

main_debug : main.o methods.o
	$(CC) main.o methods.o -g -o main_debug

main.o : Source_files/main.cpp
	$(CC) -c -g Source_files/main.cpp

methods.o : Source_files/methods.cpp
	$(CC) -c -g Source_files/methods.cpp

clean :
	rm -rf *.o main main_debug