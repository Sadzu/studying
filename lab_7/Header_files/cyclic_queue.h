#pragma once
#include <iostream>

using namespace std;

template <typename T>
class cyclic_queue {
 private:
    T **queue;
    int size;
    int front;
    int rear;
 public:
   cyclic_queue();
   cyclic_queue(size_t Size);

    void deQueue();
    void enQueue(T item);
    bool isFull();
    bool isEmpty();
    size_t getSize();
    T **getQueue();
    void print();

    ~cyclic_queue();
};