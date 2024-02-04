#include <iostream>
#include <vector>

using namespace std;

int main() {
    vector<int> data;
    for (int i = 0; i < 1000; i++) {
        data.push_back(i + 1);
    }

    return 0;
}