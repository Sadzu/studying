#include "../vector/headers/vector_templates.h"
#include "my_set.h"

int main() {
    Set<int> data;
    data.push_back(1);
    data.push_back(234);
    data.push_front(233 * 2 + 1);

    data.print();

    Vec<int> id_1 = data[1];
    cout << id_1;
    cout << data[1][0] << '\n';

    // Vec<Vec<int>> test;
    // for (int i = 0; i < 100; i++) {
    //     for (int j = 0; j < 100; j++) {
    //         test[i].push_back(j + 1);
    //     }
    // }

    // cout << test;


    return 0;
}