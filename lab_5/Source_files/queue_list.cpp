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
        if (tmp == head) {
            break;
        }
        tmp = tmp->next;
    }
    delete tmp;
}

void queue_list::Add(Matrix &matrix) {
    if (head == nullptr) {
        Node *new_node = new Node;
        new_node->data = &matrix;
        new_node->next = new_node;
        new_node->prev = new_node;
        new_node->number = 1;
        head = new_node;
        last = new_node;
    } else {
        Node *new_node = new Node;
        new_node->data = &matrix;
        new_node->next = head;
        new_node->prev = last;
        new_node->prev->next = new_node;
        new_node->number = new_node->prev->number + 1;
        last = new_node;
    }
}

// void queue_list::Add_by_number(int number) {
    
// }

void queue_list::Delete() {
    last = last->prev;
    delete last->next;
    last->next = head;
}

void queue_list::print() {
    Node *tmp = head;
    while (1) {
        cout << "Node #" << tmp->number << '\n';
        tmp->data->print();

        tmp = tmp->next;
        
        if (tmp == head) {
            break;
        }
    }
}

unsigned queue_list::search(Matrix &matrix) {
    Node *tmp = head;

    while (1) {
        if (&matrix == tmp->data) {
            return tmp->number;
        }

        tmp = tmp->next;

        if (tmp == head) {
            break;
        }
    }

    return 0;
}

unsigned queue_list::GetSize() {
    return last->number;
}

Matrix* queue_list::get_by_number(unsigned num) {
    if (num > last->number) {
        return nullptr;
    }

    Node *tmp = head;
    
    while (tmp->number != num) {
        tmp = tmp->next;
    }

    return tmp->data;
}