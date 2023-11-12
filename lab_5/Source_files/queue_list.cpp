#include "../Header_files/headers.h"
#include "../Header_files/queue_list.h"

queue_list::queue_list() {
    head = nullptr;
    last = nullptr;
}

queue_list::~queue_list() {
    Node *tmp = head->next->next;
    while (1) {
        delete tmp->prev;
        tmp = tmp->next;
        if (tmp == head) {
            break;
        }
    }
    delete tmp;
    delete head;
}

void queue_list::Add(Matrix &matrix) {
    if (head == nullptr) {
        Node *new_node = new Node;
        new_node->data = &matrix;
        new_node->next = new_node;
        new_node->prev = new_node;
        new_node->number = 1;
    } else {
        Node *new_node = new Node;
        new_node->data = &matrix;
        new_node->next = head;
        new_node->prev = last;
        new_node->number = new_node->prev->number + 1;
        last = new_node;
    }
}

void queue_list::Add_by_number(int number) {
    
}

void queue_list::Delete() {
    last = last->prev;
    delete last->next;
    last->next = head;
}

void queue_list::print() {
    Node *tmp = head;
    while (1) {
        cout << "Node #" << tmp->number;
        tmp->data->print();

        tmp = tmp->next;
        
        if (tmp == head) {
            break;
        }
    }
}