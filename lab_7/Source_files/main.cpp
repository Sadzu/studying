#include "../Header_files/cyclic_queue.h"
#include "../Header_files/cyclic_queue_funcs.h"

using namespace std;

void char_test();
void int_test();

int main() {
    while (true) {
        cout << "Menu\n" << "1. Launch string test\n" << "2. Launch int test\n" << "-1 for exit\n";
        int flag;
        cin >> flag;

        if (flag == -1) {
            break;
        }

        switch (flag)
        {
        case -1:
            break;
        
        case 1:
            char_test();
            break;

        case 2:
            int_test();
            break;

        default:
            break;
        }
    }

    return 0;
}

void char_test() {
    cout << "How long queue for strings?: ";
    size_t string_queue_size;
    cin >> string_queue_size;
    
    cyclic_queue<char> string_queue = cyclic_queue<char>(string_queue_size);

    char *tmp = new char[50];
    cout << "Enter string: ";
    cin >> tmp;
    string_queue.enQueue(tmp);
    string_queue.print();
    cout << "One more: ";
    char *tmp_1 = new char[100];
    cin >> tmp_1;
    string_queue.enQueue(tmp_1);
    string_queue.print();
    string_queue.deQueue();
    string_queue.deQueue();
    string_queue.print();
}

void int_test() {
    cout << "How long queue for integers?: ";
    size_t int_queue_size;
    cin >> int_queue_size;
    
    cyclic_queue<int> string_queue = cyclic_queue<int>(int_queue_size);

    int tmp;
    cout << "Enter number: ";
    cin >> tmp;
    string_queue.enQueue(&tmp);
    string_queue.print();
    cout << "One more: ";
    int tmp_1;
    cin >> tmp_1;
    string_queue.enQueue(&tmp_1);
    string_queue.print();
    string_queue.deQueue();
    string_queue.deQueue();
    string_queue.print();
}