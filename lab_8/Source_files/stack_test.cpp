#include "../Header_files/stack_test.h"

unsigned stack_add_test() {
    stack<float> s;

    unsigned time_begin = clock();
    for (int i = 0; i < 1000000000; i++) {
        s.push(rand() / RAND_MAX);
    }
    unsigned time_end = clock() - time_begin;

    return time_end;
}

unsigned stack_delete_test() {
    stack<float> s;
    for (int i = 0; i < 1000000000; i++) {
        s.push(rand() / RAND_MAX);
    }
    
    unsigned time_begin = clock();
    for (int i = 0; i < 1000000000; i++) {
        s.pop();
    }
    unsigned time_end = clock() - time_begin;

    return time_end;
}
