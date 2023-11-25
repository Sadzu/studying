#include "../Header_files/set_test.h"

unsigned set_add_test() {
    set<float> s;

    unsigned time_begin = clock();
    for (int i = 0; i < 1000000000; i++) {
        s.insert(rand() / RAND_MAX);
    }

    unsigned time_end = clock() - time_begin;

    return time_end;
}

unsigned set_delete_test() {
    set<float> s;
    for (int i = 0; i < 1000000000; i++) {
        s.insert(i / 127);
    }

    unsigned time_begin = clock();
    for (int i = 0; i < 1000000000; i++) {
        s.erase(i / 127);
    }
    unsigned time_end = clock() - time_begin;

    return time_end;
}

unsigned set_search_test() {
    set<float> s;
    for (int i = 0; i < 1000000000; i++) {
        if (i == 1324235) {
            s.insert(1.0);
            continue;
        }
        s.insert(rand() / RAND_MAX);
    }

    unsigned time_begin = clock();
    s.count(1.0);
    unsigned time_end = clock() - time_begin;

    return time_end;
}