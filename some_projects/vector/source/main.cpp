#include "../headers/vector_templates.h"

using namespace std;

int main() {
    Vec<int> data;
    for (int i = 0; i < 12; i++) {
        data.push_back(i + 1);
    }

    cout << data;

    int *tmp_data = data.get_data();
    size_t tmp_size = data.size();
    data.insert_range(tmp_data, tmp_size, 0);
    data.insert_range(tmp_data, tmp_size, 0);
    data.insert_range(tmp_data, tmp_size, 0);
    data.insert_range(tmp_data, tmp_size, 0);
    data.insert_range(tmp_data, tmp_size, 0);

    cout << data;

    delete[] tmp_data;

    return 0;
}