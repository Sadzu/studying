#include "../Header Files/header.h"

//Constructors:
Matrix::Matrix() {
    order = 3;
    elements.resize(order);
    vector<vector<int>> No_Name = { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} };
    for (int i = 0; i < order; ++i) {
        elements[i].resize(order);
        for (int j = 0; j < order; ++j)
            elements[i][j] = No_Name[i][j];
    }

} //Default Constructor
Matrix::Matrix(const int& n, const vector<vector<int>>& A) {
    order = n;

    elements.resize(order);
    for (int i = 0; i < order; ++i) {
        elements[i].resize(order);
        for (int j = 0; j < order; ++j)
            elements[i][j] = A[i][j];
    }
} //Parametric Constructor
Matrix::Matrix(const Matrix& Mtrx) {
    order = Mtrx.order;

    elements.resize(order);
    for (int i = 0; i < order; ++i) {
        elements[i].resize(order);
        for (int j = 0; j < order; ++j)
            elements[i][j] = Mtrx.elements[i][j];
    }
} //Copy Constructor

//Methods:
void Matrix::SetMatrix(const int &mtrx_size, const vector<vector<int>> mtrx_elements) {
    order = mtrx_size;
    elements.resize(order);

    for (int i = 0; i < order; ++i) {
        elements[i].resize(order);
        for (int j = 0; j < order; ++j)
            elements[i][j] = mtrx_elements[i][j];
    }
}
int Matrix::Matrix_Order() const{
    return order;
}
vector<vector<int>> Matrix::Matrix_Elements() const {
    vector<vector<int>> tmp_vect;
    tmp_vect.resize(order);
    for (int i = 0; i < order; ++i) {
        tmp_vect[i].resize(order);
        for (int j = 0; j < order; ++j)
            tmp_vect[i][j] = elements[i][j];
    }

    return tmp_vect;
}
 void Matrix::PrintMatrix() const {
    for (int i = 0; i < order; ++i) {
        for (int j = 0; j < order; ++j) {
            if (j == 0) { cout << " | "; }
            cout << elements[i][j] << " ";
            if (j == 2) { cout << "| " << endl; }
        }
    }
    cout << endl;

}
void Matrix::Transpose() {
    int t;
    for (int i = 0; i < order; i++)
    {
        for (int j = i; j < order; j++) {
            t = elements[i][j];
            elements[i][j] = elements[j][i];
            elements[j][i] = t;
        }
    }
    cout << "The matrix transposed" << endl;
}
void Matrix::Sum(const Matrix& B) {
    cout << "The sum made" << endl;
    for (int i = 0; i < order; ++i) {
        for (int j = 0; j < order; ++j) {
            elements[i][j] += B.elements[i][j];
        }
    }

}
int Matrix::Conversion() const {
    return order;
} //Conversion to a new type
void Matrix::SetMtrx(const int& n, const vector<vector<int>>& M) {
    order = n;

    elements.resize(order);
    for (int i = 0; i < order; ++i) {
        elements[i].resize(order);
        for (int j = 0; j < order; ++j)
            elements[i][j] = M[i][j];
    }
}


//Overloaded operators:
Matrix Matrix::operator +(const Matrix& B) {
    Matrix sum_mtrx;
    for (int i = 0; i < order; ++i) {
        for (int j = 0; j < order; ++j)
            sum_mtrx.elements[i][j] = elements[i][j] + B.elements[i][j];
    }

    return sum_mtrx;
}
Matrix Matrix::operator =(const Matrix& B) {
    for (int i = 0; i < order; ++i) {
        for (int j = 0; j < order; ++j) {
            elements[i][j] = B.elements[i][j];
        }
    }
    Matrix tmp = *this;
    return tmp;
}
Matrix& Matrix::operator ++() {
    for (int i = 0; i < order; ++i) {
        for (int j = 0; j < order; ++j)
            elements[i][j] = elements[i][j] + 1;
    }
	return *this;
} //Prefix Increment
Matrix& Matrix::operator ++(int value) {
    Matrix tmp_mtrx = *this;
    Matrix unit_matrix = { order,{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}} };

    for (int i = 0; i < order; ++i) {
        for (int j = 0; j < order; ++j)
            elements[i][j] += unit_matrix.elements[i][j];
    }

    return tmp_mtrx;
} //Postfix Increment

//Destructor:
Matrix::~Matrix() {
    for (int i = 0; i < order; ++i)
        elements[i].clear();
    elements.clear();
}

//Friend functions:
Matrix Subtraction(const Matrix& A, const Matrix& B) {
    Matrix tmp_Mtrx;
    for (int i = 0; i < A.order; ++i) {
        for (int j = 0; j < A.order; ++j)
            tmp_Mtrx.elements[i][j] = A.elements[i][j] - B.elements[i][j];
    }

    return tmp_Mtrx;
}
fstream& operator << (fstream& out, const Matrix& matrix) {
    out << matrix.order << endl;
    for (int i = 0; i < matrix.order; ++i) {
        for (int j = 0; j < matrix.order; ++j) {
            out << matrix.elements[i][j] << " ";
            if (j == (matrix.order - 1)) out << endl;
        }
    }

    return out;
} //Output the matrix to the txt file
ofstream& operator << (ofstream& os, const Matrix& matrix) {
    os.write((char*)&matrix.order, sizeof(matrix.order));
    for (int i = 0; i < matrix.order; ++i) {
        for (int j = 0; j < matrix.order; ++j) {
            os.write((char*)&matrix.elements[i][j], sizeof(matrix.elements[i][j]));
        }
    }

    return os;
} //Output the matrix to the binary file
ifstream& operator >> (ifstream& in, Matrix& matrix) {
	for (int i = 0; i < matrix.order; ++i) 
			matrix.elements[i].clear();
	matrix.elements.clear();

    in.read((char*)&matrix.order, sizeof(int));
    matrix.elements.resize(matrix.order);
    for (int i = 0; i < matrix.order; ++i) {
        matrix.elements[i].resize(matrix.order);
        for (int j = 0; j < matrix.order; ++j) {
            in.read((char*)&matrix.elements[i][j], sizeof(int));
        }
    }

    return in;
} //Input the matrix from the binary file


//Derived class Matrix_Name:
Matrix_Name::Matrix_Name(): Matrix::Matrix() {
    name = "No Name";
} //Default constructor
Matrix_Name::Matrix_Name(const string &name, const int &n, const vector<vector<int>> &elmnts): Matrix::Matrix() {
    this -> name = name;
    SetMatrix(n, elmnts);
} //Parametric constructor
Matrix_Name::Matrix_Name(const Matrix_Name &Mtrx): Matrix::Matrix(Mtrx) {
    this -> name = Mtrx.name;
} //Constructor of copy
void Matrix_Name::PrintMatrix() const {
    cout << "The name of matrix is " << name << "." << endl;
    Matrix::PrintMatrix();
}
string Matrix_Name::ChangeName(const string &name) {
    this -> name = name;
}
Matrix_Name::~Matrix_Name() {
    name.clear();
}


//Derived class Matrix_Num:
//Constructors:
Matrix_Int::Matrix_Int(): Matrix::Matrix() {
    Num = 0;
}
Matrix_Int::Matrix_Int(const int &mtrx_num, const int &n, const vector<vector<int>> &elmnts) : Matrix::Matrix(){
    this -> Num = mtrx_num;
    SetMatrix(n, elmnts);
}
Matrix_Int::Matrix_Int(const Matrix_Int &Mtrx): Matrix::Matrix(Mtrx) {
    this -> Num = Mtrx.Num;
}
//Methods:
void Matrix_Int::PrintMatrix() const {
    cout << "The number of the matrix is: " << this -> Num << ".\n";
    Matrix::PrintMatrix();
}
string Matrix_Int::ChangeNum (const int &num) {
    this -> Num = num;
}

Matrix_Int::~Matrix_Int() {}


