#include "../Header_files/headers.h"

int main() {
    size_t ord;
    cout << "Enter the order of matrix: ";
    cin >> ord;
    cout << "Enter data: ";
    int **lns = new int*[ord];
    for (size_t i = 0; i < ord; i++) {
        lns[i] = new int[ord];
        for (size_t j = 0; j < ord; j++) {
            cin >> lns[i][j];
        }
    }
    char *nm = new char[200];
    cout << "Enter matrix name: ";
    cin >> nm;

    Matrix_with_name matrix = Matrix_with_name(ord, lns, nm);

    matrix.print();
   
    return 0;
}
