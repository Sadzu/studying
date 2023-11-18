CC = g++ -Wall -Werror -Wextra

all : main main_debug

build : main

debug : main_debug

main : main.o methods.o queue_list.o
	$(CC) main.o methods.o queue_list.o -o main

main_debug : main.o methods.o queue_list.o
	$(CC) main.o methods.o queue_list.o -g -o main_debug

main.o : Source_files/main.cpp
	$(CC) -c -g Source_files/main.cpp

methods.o : Source_files/methods.cpp
	$(CC) -c -g Source_files/methods.cpp

queue_list.o : Source_files/queue_list.cpp
	$(CC) -c -g Source_files/queue_list.cpp

clean :
	rm -rf *.o main main_debug