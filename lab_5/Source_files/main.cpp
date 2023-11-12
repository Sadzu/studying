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
    cout << "Enter matrix name: ";
    char *name = new char[200];
    cin >> name;
    Matrix_with_name m_name(ord, lns, name);

    Matrix *m_2 = &m_name;
    
    m_2->print();

    return 0;
}
