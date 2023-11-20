#include <iostream>
#include <vector>
#include <fstream>

using namespace std;

class Matrix {
public:
    //Constructors:
    Matrix(); //Default Constructor
    Matrix(const int& n, const vector<vector<int>>& A); //Parametric Constructor
    Matrix(const Matrix& Mtrx); //Copy Constructor

    //Methods:
    void SetMatrix(const int &mtrx_size, const vector<vector<int>> mtrx_elements);
    int Matrix_Order() const;
    vector<vector<int>> Matrix_Elements() const;
    virtual void PrintMatrix() const;
    void Transpose();
    void Sum(const Matrix& B);
    int Conversion() const; //Conversion to a new type
    void SetMtrx(const int& n, const vector<vector<int>>& M);

    //Overloaded operators:
    Matrix operator +(const Matrix& B);
    Matrix operator =(const Matrix& B);
    Matrix& operator ++(); //Prefix Increment
    Matrix& operator ++(int value); //Postfix Increment

    //Destructor:
    ~Matrix();

private:
    int order;
    vector<vector<int>> elements;

    friend Matrix Subtraction(const Matrix& A, const Matrix& B); //Matrix Subtraction
    friend fstream& operator << (fstream& out, const Matrix& matrix); //Output the matrix to the txt file
    friend ofstream& operator << (ofstream& os, const Matrix& matrix); //Output the matrix to the binary file
    friend ifstream& operator >> (ifstream& in, Matrix& matrix); //Intput the matrix from the binary file
};


//Derived class of class Matrix:
class Matrix_Name: public Matrix {
public:
    //Default constructor:
    Matrix_Name();
    //Parametric constructor:
    Matrix_Name(const string &name, const int &n, const vector<vector<int>> &elmnts);
    //Constructor of copy:
    Matrix_Name(const Matrix_Name &Mtrx);

    //Methods:
    void PrintMatrix() const override;
    string ChangeName(const string &name);

    //Destructor:
    ~Matrix_Name();
private:
    string name;
};

class Matrix_Int: public Matrix {
public:
    //Default constructor:
    Matrix_Int();
    //Parametric constructor:
    Matrix_Int(const int &mtrx_num, const int &n, const vector<vector<int>> &elmnts);
    //Constructor of copy:
    Matrix_Int(const Matrix_Int &Mtrx);

    //Methods:
    void PrintMatrix() const override;
    string ChangeNum (const int &num);

    //Destructor:
    ~Matrix_Int();
private:
    int Num;
};