#pragma once

#include <iostream>
#include "../vector/headers/vector_templates.h"

#define HASH_NUMBER 233

template <typename T>
class Set {
 private:
    Vec<T> *m_data;
 public:
    //construct/destruct
    Set() {
        m_data = new Vec<T>[HASH_NUMBER];
    }
    Set(Set &object) {
        this->operator=(object);
    }
    ~Set() {
        delete[] m_data;
    }
    //overloads
    void operator=(Set<T> &object) {
        m_data = object.get_data();
    }
    Vec<T> operator[](size_t index) {
        return m_data[index];
    }
    //getters
    Vec<T>* get_data() {
        Vec<T> *copyed = new Vec<T>[HASH_NUMBER];
        for (int i = 0; i < HASH_NUMBER; i++) {
            copyed[i] = m_data[i];
        }

        return copyed;
    }
    //push
    void push_back(T item) {
        if (m_data[item % HASH_NUMBER].is_in(item)) {
            return;
        }
        m_data[item % HASH_NUMBER].push_back(item);
    }
    void push_front(T item) {
        if (m_data[item % HASH_NUMBER].is_in(item)) {
            return;
        }
        m_data[item % HASH_NUMBER].push_front(item);
    }
    //service
    size_t* sizes() {
        size_t *szs = new size_t[HASH_NUMBER];
        for (int i = 0; i < HASH_NUMBER; i++) {
            szs[i] = m_data[i].size();
        }

        return szs;
    }
    void print() {
        for (int i = 0; i < HASH_NUMBER; i++) {
            if (m_data[i].is_empty()) {
                continue;
            } else {
                cout << i << ": " << m_data[i];
            }
        }
    }
};

// template <typename T>
// std::ostream& operator<<(std::ostream &out, Set<T> &object) {
//     for (int i = 0; i < HASH_NUMBER; i++) {
//         if (object[i].is_empty()) {
//             continue;
//         } else {
//             out << object[i];
//         }
//     }
//     out << '\n';

//     return out;
// }
