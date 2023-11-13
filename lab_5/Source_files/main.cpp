#include "../Header_files/headers.h"
#include "../Header_files/queue_list.h"

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
    Matrix m_parent = Matrix(ord, lns);

    char *mat_name = new char[200];
    cout << "Enter name: ";
    cin >> mat_name;

    int number;
    cout << "Enter number: ";
    cin >> number;

    Matrix_with_name m_name = Matrix_with_name(ord, lns, mat_name);
    Matrix_with_int m_int = Matrix_with_int(ord, lns, number);

    queue_list qu;
    qu.Add(m_parent);
    qu.Add(m_name);
    qu.Add(m_int);

    qu.print();

    return 0;
}
