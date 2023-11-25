#include <iostream>
#include "../Header_files/set_test.h"
#include "../Header_files/stack_test.h"

using namespace std;

int main() {
    cout << "Set testing\n";
    cout << "Time for 1b floats set add test: " << set_add_test() / CLOCKS_PER_SEC << " seconds" << endl;
    cout << "Time for 1b floats set delete test: " << set_delete_test() / CLOCKS_PER_SEC << " seconds" << endl;
    cout << "Time for 1b floats set search test: " << set_search_test() / CLOCKS_PER_SEC << " seconds" << endl;
    cout << "Set always sorted\n";

    cout << "Stack testing\n";
    cout << "Time for 1b floats stack add test: " << stack_add_test() / CLOCKS_PER_SEC << " seconds" << endl;
    cout << "Time for 1b floats stack delete test: " << stack_delete_test() / CLOCKS_PER_SEC << " seconds" << endl;
    cout << "Access to first element only, sorting is not possible\n";
    cout << "Access to first element only, search is not possible\n";

    return 0;
}