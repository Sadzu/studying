CC = g++ -Wall -Werror -Wextra -std=c++17

all : main

build: main

main : main.o set_test.o stack_test.o
	$(CC) main.o set_test.o stack_test.o -g -o main

main.o : main.cpp
	$(CC) -c -g main.cpp

set_test.o : set_test.cpp
	$(CC) -c -g set_test.cpp

stack_test.o : stack_test.cpp
	$(CC) -c -g stack_test.cpp

clean :
	rm -rf *.o main