CC = g++ -Wall -Werror -Wextra

all : main

build: main

main : main.o main_funcs.o
	$(CC) -o main main.o main_funcs.o

main.o : main.cpp
	$(CC) -c -o main.o main.cpp

main_funcs.o : main_funcs.cpp
	$(CC) -c -o main_funcs.o main_funcs.cpp

clean :
	rm -rf *.o main