#include <iostream>
#include <cstdlib>
#include <time.h>
#include <cstring>
#include <fstream>
using namespace std;

class Matrix {
 public:
    Matrix();
    Matrix(int a, int **matrix);
    Matrix(const Matrix &);

    size_t getMatrixOrder();
    int **getlines();

    void EnterMatrix(int a);
    void print();
    void transportation();
    Matrix operator +(Matrix matrix);
    friend Matrix operator -(Matrix matrix_1, Matrix matrix_2);
    void operator =(Matrix matrix);
    Matrix operator ++();
    Matrix operator ++(int);
    operator int() const;
    friend istream& operator >>(istream &is_bin, Matrix &);

    ~Matrix();

 private:
    size_t MatrixOrder;
    int **lines;

};

Matrix operator -(Matrix matrix_1, Matrix matrix_2);
ostream& operator <<(ostream &os, Matrix &matrix);
istream& operator >>(istream &is_bin, Matrix &);
