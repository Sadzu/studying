#include "../Headers/item.h"
#include <fstream>
#include <cstring>

//template Item<char>;

template <typename T>
Item<T>::Item(T* _value) {
    value = _value;
}

template <typename T>
Item<T>::~Item() {
    delete value;
}

template <typename T>
void Item<T>::SetValue(T* _value) {
    value = _value;
}

template <typename T>
T* Item<T>::GetValue() {
    return value;
}

template <typename T>
bool Item<T>::operator > (const Item<T>& item) {
    int minLen;
    if (strlen(value) > strlen(item.value))
        minLen = strlen(item.value);
    else
        minLen = strlen(value);
    for (int i = 0; i < minLen; ++i) {
        if (tolower(value[i]) > tolower(item.value[i]))
            return true;
        else if (tolower(value[i]) < tolower(item.value[i]))
            return false;
    }

    if (strlen(value) > strlen(item.value))
        return true;

    return false;
}

template <typename T>
bool Item<T>::operator < (const Item<T>& item) {
    int minLen;
    if (strlen(value) > strlen(item.value))
        minLen = strlen(item.value);
    else
        minLen = strlen(value);

    for (int i = 0; i < minLen; ++i) {
        if (tolower(value[i]) > tolower(item.value[i]))
            return false;
        else if (tolower(value[i]) < tolower(item.value[i]))
            return true;
    }

    if (strlen(value) < strlen(item.value))
        return true;
    return false;
}


template <typename T>
void Item<T>::ReadBinaryFile(std::ifstream& fin) {
    int length = 0;
    fin.read((char*)&length, sizeof(int));
    value = new char[length];
    fin.read(value, length);
    value[length] = '\0';
}

template <typename T>
void Item<T>::WriteBinaryFile(std::ofstream& fout) {
    int length = strlen(value);
    fout.write((char*)&length, sizeof(int));
    fout.write(value, length*sizeof(T));
}

template <typename T>
void Item<T>::Print() {
    std::cout << value;
}
