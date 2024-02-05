#include "../headers/vector_templates.h"
#include <ctime>
#include <vector>

using namespace std;

int main() {
    unsigned time_vec_begin = clock();
    Vec<int> data;
    for (int i = 0; i < 1000000; i++) {
        data.push_back(i + 1);
    }
    data.clear();
    unsigned time_vec = (clock() - time_vec_begin);

    unsigned time_vector_begin = clock();
    vector<int> d;
    for (int i = 0; i < 1000000; i++) {
        d.push_back(i + 1);
    }
    d.clear();
    unsigned time_vector = (clock() - time_vector_begin);

    cout << "Vec (clocks): " << time_vec << '\n' << "vector (clocks): " << time_vector << '\n';

    return 0;
}