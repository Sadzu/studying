#include "../Header_files/headers.h"

int main() {
    cout << "Enter matrix order: ";
    size_t m_1_order;
    cin >> m_1_order;
    int **m_1_lines = new int *[m_1_order];
    for (size_t i = 0; i < m_1_order; i++) {
        m_1_lines[i] = new int[m_1_order];
        for (size_t j = 0; j < m_1_order; j++) {
            cin >> m_1_lines[i][j];
        }
    }
    Matrix m_1 = Matrix(m_1_order, m_1_lines);
    for (size_t i = 0; i < m_1_order; i++) {
        delete m_1_lines[i];
    }
    delete m_1_lines;

    Matrix m_2 = Matrix(m_1);
    cout << "\nCopy construct";
    m_2.print();

    cout << "\nOperator +";
    Matrix m_3 = m_1 + m_2;
    m_3.print();
    m_1.operator+(m_2);

    cout << "\nOperator -";
    m_3 = m_1 - m_2;
    m_3.print();

    cout << "\nTransportation";
    m_1.transportation();
    m_1.print();

    cout << "\nx++";
    (m_1++).print();
    m_1.print();

    cout << "\n++x";
    (++m_1).print();
    m_1.print();

    cout << "\nCasting\n";
    int a = m_1;
    cout << a << '\n';

    ofstream fout("Resource_files/text.txt", ios::out);
    if (!fout) {
        cout << "Failed to open file\n";
    } else {
        fout << m_1;
        fout.close();
    }

    ofstream fout_bin("Resource_files/test.bin", ios::binary | ios::out);
    if (!fout_bin) {
        cout << "Failed to open file\n";
    } else {
        fout_bin << m_1;
        fout_bin.close();
    }

    ifstream fin_bin("Resource_files/test.bin", ios::binary | ios::in);
    if (!fin_bin) {
        cout << "Failed to open file\n";
    } else {
        fin_bin >> m_3;
        fin_bin.close();
    }
    m_3.print();
   
    return 0;
}
