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

ostream& operator <<(ostream &os_bin, Matrix &matrix) {
    os_bin << matrix.getMatrixOrder() << " ";
    int **tmp = matrix.getlines();
    for (size_t i = 0; i < matrix.getMatrixOrder(); i++) {
        for (size_t j = 0; j < matrix.getMatrixOrder(); j++) {
            os_bin << tmp[i][j] << " ";
        }
    }

    return os_bin;
}

istream& operator >>(istream &is_bin, Matrix &matrix) {
    is_bin >> matrix.MatrixOrder;
    matrix.lines = new int *[matrix.MatrixOrder];
    for (size_t i = 0; i < matrix.MatrixOrder; i++) {
        matrix.lines[i] = new int[matrix.MatrixOrder];
        for (size_t j = 0; j < matrix.MatrixOrder; j++) {
            is_bin >> matrix.lines[i][j];
        }
    }

    return is_bin;
}
