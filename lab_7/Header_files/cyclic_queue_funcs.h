#pragma once
#include "../Header_files/cyclic_queue.h"

template <typename T>
cyclic_queue<T>::cyclic_queue() {
    queue = nullptr;
    size = 0;
    front = -1;
    rear = -1;
}

template <typename T>
cyclic_queue<T>::cyclic_queue(size_t Size) {
    queue = new T*[Size];
    size = Size;
    front = -1;
    rear = -1;
}

template <typename T>
cyclic_queue<T>::~cyclic_queue() {
    if (isEmpty()) {
        delete[] queue;
        return;
    }
    for (int i = front; i <= rear; i++) {
        delete queue[i];
    }

    delete[] queue;
}

template <typename T>
bool cyclic_queue<T>::isFull() {
    if (front == 0 && rear == size-1) {
        return true;
    }

    return false;
}

template <typename T>
bool cyclic_queue<T>::isEmpty() {
    if (front == -1) {
        return true;
    }

    return false;
}

template <typename T>
void cyclic_queue<T>::enQueue(T *item) {
    if (isEmpty()) {
        queue[0] = item;
        front = 0;
        rear = 0;

        return;
    }

    if (isFull()) {
        cout << "Queue is full, unable to add element\n";
        return;
    }

    rear = (rear + 1) % size;
    queue[rear] = item;
}

template <typename T>
void cyclic_queue<T>::deQueue() {
    if (isEmpty()) {
        cout << "Queue is already empty\n";
        return;
    }

    if (front == rear) {
        front = -1;
        rear = -1;
        return;
    }

    front = (front + 1) % size;
}

template <typename T>
size_t cyclic_queue<T>::getSize() {
    return size;
}

template <typename T>
T** cyclic_queue<T>::getQueue() {
    return queue;
}

template <typename T>
void cyclic_queue<T>::print() {
    if (isEmpty()) {
        cout << "Queue is clear\n";
        return;
    }

    for (int i = front; i <= rear; i++) {
        cout << queue[i] << endl;
    }
}