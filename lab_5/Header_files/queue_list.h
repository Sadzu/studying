#include "headers.h"

struct Node {
    Node *next;
    Node *prev;
    Matrix *data;
    unsigned number;
};

class queue_list {
 private:
    Node *head;
    Node *last;
 public:
    queue_list();

    void Delete();
    void Add(Matrix &matrix);
    void Delete_by_number(int number);
    void Add_by_number(int number);
    void print();

    ~queue_list();
};
