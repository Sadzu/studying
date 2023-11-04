#include "../Header_files/headers.h"

Matrix::Matrix() {
    MatrixOrder = 0;
    lines = nullptr;
}

Matrix::Matrix(int a, int **matrix) {
    MatrixOrder = a;
    lines = new int *[MatrixOrder];
    for (size_t i = 0; i < MatrixOrder; i++) {
        lines[i] = new int [MatrixOrder];
        for (size_t j = 0; j < MatrixOrder; j++) {
            lines[i][j] = matrix[i][j];
        }
    }
}

Matrix::Matrix(const Matrix &matrix) {
    MatrixOrder = matrix.MatrixOrder;
    lines = new int *[MatrixOrder];
    for (size_t i = 0; i < MatrixOrder; i++) {
        lines[i] = new int[MatrixOrder];
        for (size_t j = 0; j < MatrixOrder; j++) {
            lines[i][j] = matrix.lines[i][j];
        }
    }
}

size_t Matrix::getMatrixOrder() {
    return MatrixOrder;
}

int **Matrix::getlines() {
    return lines;
}

void Matrix::EnterMatrix(int a) {
    MatrixOrder = a;

    lines = new int*[MatrixOrder];
    cout << "Enter data: \n";
    for (size_t i = 0; i < MatrixOrder; i++) {
        lines[i] = new int[MatrixOrder];
        for (size_t j = 0; j < MatrixOrder; j++) {
        cin >> lines[i][j];
        }
    }
}

void Matrix::print() {
    cout << '\n';
    for (size_t i = 0; i < MatrixOrder; i++) {
        for (size_t j = 0; j < MatrixOrder; j++) {
            cout << lines[i][j] << ' ';
        }
        cout << '\n';
    }
}

void Matrix::transportation() {
    Matrix *temp = new Matrix;
    temp->MatrixOrder = MatrixOrder;
    temp->lines = new int*[MatrixOrder];

    for (size_t i = 0; i < MatrixOrder; i++) {
        temp->lines[i] = new int[MatrixOrder];
        for (size_t j = 0; j < MatrixOrder; j++) {
            temp->lines[i][j] = lines[j][i];
        }
    }

    for (size_t i = 0; i < MatrixOrder; i++) {
        for(size_t j = 0; j < MatrixOrder; j++) {
            lines[i][j] = temp->lines[i][j];
        }
    }

    delete temp;
}

Matrix Matrix::operator +(Matrix matrix) {
    if (matrix.MatrixOrder != MatrixOrder) {
        cout << "You cannot add matrices of different dimensions\n";
        return *this;
    }

    Matrix temp = *this;
    for (size_t i = 0; i < MatrixOrder; i++) {
        for (size_t j = 0; j < MatrixOrder; j++) {
            temp.lines[i][j] += matrix.lines[i][j];
        }
    }

    return temp;
}

Matrix operator -(Matrix matrix_1, Matrix matrix_2) {
    if (matrix_1.MatrixOrder != matrix_2.MatrixOrder) {
        cout << "You cannot add matrices of different dimensions\n";
        return matrix_1;
    }

    Matrix temp = matrix_1;
    for (size_t i = 0; i < matrix_1.MatrixOrder; i++) {
        for (size_t j = 0; j < matrix_1.MatrixOrder; j++) {
            temp.lines[i][j] -= matrix_2.lines[i][j];
        }
    }

    return temp;
}

void Matrix::operator =(Matrix matrix) {
    MatrixOrder = matrix.MatrixOrder;
    delete lines;
    lines = new int *[MatrixOrder];
    for (size_t i = 0; i < MatrixOrder; i++) {
        lines[i] = new int[MatrixOrder];
        for (size_t j = 0; j < MatrixOrder; j++) {
            lines[i][j] = matrix.lines[i][j];
        }
    }
}

Matrix Matrix::operator ++() {
    for (size_t i = 0; i < MatrixOrder; i++) {
        for (size_t j = 0; j < MatrixOrder; j++) {
            lines[i][j] = lines[i][j] + 1;
        }
    }

    return *this;
}

Matrix Matrix::operator ++(int) {
    Matrix temp = *this;
    // for (size_t i = 0; i < MatrixOrder; i++) {
    //     for (size_t j = 0; j < MatrixOrder; j++) {
    //         lines[i][j] = lines[i][j] + 1;
    //     }
    // }

    ++(*this);

    return temp;
}

Matrix::operator int() const {
    return MatrixOrder;
}

Matrix::~Matrix() {
    for (size_t i = 0; i < MatrixOrder; i++) {
        delete lines[i];
    }
    delete lines;
    MatrixOrder = 0;
}

ofstream& operator <<(ofstream &os, Matrix &matrix) {
    os.write((char *)&matrix.MatrixOrder, sizeof(size_t));
    for (size_t i = 0; i < matrix.MatrixOrder; i++) {
        for (size_t j = 0; j < matrix.MatrixOrder; j++) {
            os.write((char *)&matrix.lines[i][j], sizeof(int));
        }
    }

    return os;
}

ifstream& operator >>(ifstream &is, Matrix &matrix) {
    size_t mtrord;
    for (size_t i = 0; i < matrix.MatrixOrder; i++) {
        delete matrix.lines[i];
    }
    delete matrix.lines;
    is.read((char *)&mtrord, sizeof(size_t));
    matrix.lines = new int *[mtrord];
    matrix.MatrixOrder = mtrord;
    for (size_t i = 0; i < mtrord; i++) {
        matrix.lines[i] = new int[mtrord];
        for (size_t j = 0; j < mtrord; j++) {
            is.read((char *)&matrix.lines[i][j], sizeof(int));
        }
    }

    return is;
}

fstream& operator << (fstream &in, Matrix &matrix) {
    in << matrix.MatrixOrder << ' ';
    for (size_t i = 0; i < matrix.MatrixOrder; i++) {
        for (size_t j = 0; j < matrix.MatrixOrder; j++) {
            in << matrix.lines[i][j] << ' ';
        }
    }

    return in;
}

Matrix_with_name::Matrix_with_name(int order, int **matrix, char *name) : Matrix::Matrix(order, matrix) {
    Name = new char[strlen(name)+1];
    size_t i = 0;
    for (i = 0; name[i] != '\0'; i++) {
        Name[i] = name[i];
    }
    Name[i+1] = '\0';
}

Matrix_with_name::Matrix_with_name(const Matrix_with_name &matrix) :  Matrix::Matrix(matrix) {
    Name = new char[strlen(matrix.Name)+1];
    size_t i;
    for (i = 0; matrix.Name[i] != '\0'; i++) {
        Name[i] = matrix.Name[i];
    }
    Name[i+1] = 0;
}

Matrix_with_name::Matrix_with_name() : Matrix::Matrix() {
    Name = nullptr;
}

Matrix_with_name::~Matrix_with_name() {
    delete Name;
}

void Matrix_with_name::print() {
    cout << Name;
    Matrix::print();
}

Matrix_with_int::Matrix_with_int(int order, int **matrix, int number) : Matrix::Matrix(order, matrix) {
    MatrixNumber = number;
}

Matrix_with_int::Matrix_with_int(const Matrix_with_int &matrix) : Matrix::Matrix(matrix) {
    MatrixNumber = matrix.MatrixNumber;
}

Matrix_with_int::Matrix_with_int() {
    MatrixNumber = 0;
}

void Matrix_with_int::print() {
    cout << MatrixNumber;
    Matrix::print();
}

Matrix_with_int::~Matrix_with_int() {
    MatrixNumber = 0;
}

Matrix_with_name Matrix_with_name::operator +(Matrix_with_name matrix){
    if (getMatrixOrder() != matrix.getMatrixOrder()) {
        return *this;
    }

    Matrix_with_name tmp = *this;
    int **lines_this = tmp.getlines(), **lines_matrix = matrix.getlines();
    for (size_t i = 0; i < getMatrixOrder(); i++) {
        for (size_t j = 0; j < getMatrixOrder(); j++) {
            lines_this[i][j] += lines_matrix[i][j];
        }
    }

    return tmp;
}

void Matrix_with_name::ChangeName(char *new_name) {
    delete Name;
    size_t len = strlen(new_name), i = 0;
    Name = new char[len+1];
    
    for (i = 0; new_name[i] != '\0'; i++) {
        Name[i] = new_name[i];
    }
    Name[i+1] = '\0';
}