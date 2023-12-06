#include "../Header_files/cyclic_queue_funcs.h"

using namespace std;

void char_test();
void int_test();

int main() {
    // while (true) {
    //     cout << "Menu\n" << "1. Launch string test\n" << "2. Launch int test\n" << "-1 for exit\n";
    //     int flag;
    //     cin >> flag;

    //     if (flag == -1) {
    //         break;
    //     }

    //     switch (flag) {
    //     case -1:
    //         break;
        
    //     case 1:
    //         char_test();
    //         break;

    //     case 2:
    //         int_test();
    //         break;

    //     default:
    //         break;
    //     }
    // }

    // return 0;
    int_test();

    return 0;
}

// void char_test() {
//     cout << "Enter strings: \n";
//     char *tmp = new char[50];
//     char *tmp_2 = new char[50];
//     cin >> tmp;
//     cin >> tmp_2;
//     cyclic_queue<char> string_queue = cyclic_queue<char>(2);
//     string_queue.enQueue(tmp);
//     string_queue.enQueue(tmp_2);
//     string_queue.print();
//     string_queue.deQueue();
//     string_queue.deQueue();
//     string_queue.print();
// }

void int_test() {
    // cout << "How long queue for integers?: ";
    // size_t int_queue_size;
    // cin >> int_queue_size;
    
    // cyclic_queue<int> int_queue = cyclic_queue<int>(int_queue_size);

    // int *tmp = new int[int_queue_size];
    // for (size_t i = 0; i < int_queue_size; i++) {
    //     cin >> tmp[i];
    //     int_queue.enQueue(&tmp[i]);
    // }
    // int_queue.print();
    // for (size_t i = 0; i < int_queue_size; i++) {
    //     int_queue.deQueue();
    // }
    cyclic_queue<int> int_queue = cyclic_queue<int>(5);
    int_queue.enQueue(1);
    int_queue.enQueue(2);
    int_queue.enQueue(3);
    int_queue.enQueue(4);
    int_queue.enQueue(5);
    int_queue.print();   
}