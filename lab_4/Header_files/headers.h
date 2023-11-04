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
    virtual void print();
    void transportation();
    Matrix operator +(Matrix matrix);
    friend Matrix operator -(Matrix matrix_1, Matrix matrix_2);
    void operator =(Matrix matrix);
    Matrix operator ++();
    Matrix operator ++(int);
    operator int() const;
    friend ifstream& operator >>(ifstream &is, Matrix &matrix);
    friend ofstream& operator <<(ofstream &os, Matrix &matrix);
    friend fstream& operator << (fstream &in, Matrix &matrix);

    virtual ~Matrix();

 private:
    size_t MatrixOrder;
    int **lines;

};

class Matrix_with_name: public Matrix {
 private:
   char *Name;
 public:
   Matrix_with_name(int order, int **matrix, char *name);
   Matrix_with_name(const Matrix_with_name &);
   Matrix_with_name();

   void print();
   Matrix_with_name operator +(Matrix_with_name matrix);
   void ChangeName(char *new_name);

   ~Matrix_with_name();
};

class Matrix_with_int : public Matrix {
 private:
   int MatrixNumber;
 public:
   Matrix_with_int(int order, int **matrix, int number);
   Matrix_with_int(const Matrix_with_int &);
   Matrix_with_int();

   void print();

   ~Matrix_with_int();
};

Matrix operator -(Matrix matrix_1, Matrix matrix_2);
ofstream& operator <<(ofstream &os, Matrix &matrix);
ifstream& operator >>(ifstream &is, Matrix &matrix);
fstream& operator << (fstream &in, Matrix &matrix);
