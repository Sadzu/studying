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

    queue_list ql;
    try {
        ql.print();
    } 
    catch(const char *error_message) {
        cout << error_message << endl;
    }

    char *matrix_name = new char[50];
    int matrix_number;
    cout << "Enter the name of matrix: ";
    cin >> matrix_name;
    cout << "Enter the number of matrix: ";
    cin >> matrix_number;

    Matrix_with_name m_name = Matrix_with_name(ord, lns, matrix_name);
    Matrix_with_int m_int = Matrix_with_int(ord, lns, matrix_number);

    try {
        ql.Delete();
    } catch (const char *error_message) {
        cout << error_message << endl;
    }

    ql.Add(m_parent);
    ql.Add(m_name);
    ql.Add(m_int);

    try {
        ql.Delete();
    } catch (const char *error_message) {
        cout << error_message << endl;
    }
    ql.print();

    try {
        ql.print();
    }
    catch (const char *error_message) {
        cout << error_message << endl;
    }

    try {
        m_parent.change_value(3, 3, -5);
        m_parent.print();
    } catch (const char *error_message) {
        cout << error_message << endl;
    }
    try {
        m_name.change_value(1, 3, -5);
        m_name.print();
    } catch (const char *error_message) {
        cout << error_message << endl;
    }
    try {
        m_parent.change_value(1, 1, -5);
        m_parent.print();
    } catch (const char *error_message) {
        cout << error_message << endl;
    }

    return 0;
}
