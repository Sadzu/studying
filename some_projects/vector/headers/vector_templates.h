#include <iostream>
#include <cstdarg>
#include <cmath>

using namespace std;

//rEaLlOc fOr nEw
template <typename T>
T* array_memory_reallocate(T *array, size_t old_size, size_t new_size) {
    T *tmp = new T[new_size];
    for (size_t i = 0; i < old_size; i++) {
        tmp[i] = array[i];
    }
    delete[] array;

    return tmp;
}

template <typename T>
class Vec {
 private:
    size_t m_size;
    long int m_last_item_index;
    T *m_data;
 public:
    //construct/destruct
    Vec() {
        m_size = 10;
        m_data = new T[10];
        m_last_item_index = -1;
    }
    Vec(size_t size, T *data) {
        m_size = size;
        if (m_data) {
            delete data;
        }
        m_data = new T[m_size];
        size_t i = 0;
        for (i = 0; i < m_size; i++) {
            m_data[i] = data[i];
        }
        m_last_item_index = i;
    }
    Vec(Vec &object) {
        this->operator=(object);
    }
    //maybe later
    // Vec(T arg, ...) {
    //     va_list vl;
    //     va_start(vl, arg);
    //     size_t i = 0;
    //     for (i = 0; ; i++);
    //     va_end(vl);
    //     m_size = i + 1;
    //     m_data = new T[m_size];
    //     m_last_item_index = i;
    //     va_start(vl, arg);
    //     for (i = 0; ; i++) {

    //     }
    // }
    ~Vec() {
        delete[] m_data;
        m_size = 0;
        m_last_item_index = -1;
    }
    //getters
    size_t get_size() {
        return m_size;
    }
    T* get_data() {
        T *copyed_data = new T[size()];
        for (size_t i = 0; i < size(); i++) {
            copyed_data[i] = m_data[i];
        }

        return copyed_data;
    }
    //pushes
    void push_back(T item) {
        if (m_last_item_index < (long int)m_size - 1) {
            m_data[m_last_item_index + 1] = item;
            ++m_last_item_index;
        } else {
            m_size = (size_t)floor(m_size * 1.5) + 1;
            m_data = array_memory_reallocate(m_data, size(), m_size);
            ++m_last_item_index;
            m_data[m_last_item_index] = item;
        }
    }
    void push_front(T item) {
        if (m_last_item_index < (long int)m_size - 1) {
            for (long int i = m_last_item_index; i >= 0; i--) {
                m_data[i + 1] = m_data[i];
            }
            m_data[0] = item;
            ++m_last_item_index;
        } else {
            m_size = floor(m_size * 1.5) + 1;
            m_data = array_memory_reallocate(m_data, size(), m_size);
            for (long int i = m_last_item_index; i > 0; i--) {
                m_data[i + 1] = m_data[i];
            }
            m_data[0] = item;
            ++m_last_item_index;
        }
    }
    void insert(T item, size_t index) {
        if (m_last_item_index < (long int)m_size - 1) {
            for (long int i = m_last_item_index; i >= (long int)index; i--) {
                m_data[i + 1] = m_data[i];
            }
            m_data[index] = item;
            ++m_last_item_index;
        } else {
            m_size = floor(m_size * 1.5) + 1;
            m_data = array_memory_reallocate(m_data, size(), m_size);
            for (long int i = m_last_item_index; i >= (long int)index; i--) {
                m_data[i + 1] = m_data[i];
            }
            m_data[index] = item;
            ++m_last_item_index;
        }
    }
    void insert_range(T *items, size_t range, size_t index) {
        if (m_last_item_index < (long int)m_size - 1 - (long int)range) {
            for (long int i = m_last_item_index; i >= (long int)index; i--) {
                m_data[i + range] = m_data[i];
            }
            m_last_item_index += (long int)range;
            size_t j = 0;
            for (size_t i = index; i < index + range; i++) {
                m_data[i] = items[j++];
            }
        } else {
            m_size = floor(m_size * 1.5) + range + 1;
            m_data = array_memory_reallocate(m_data, size(), m_size);
            for (long int i = m_last_item_index; i >= (long int)index; i--) {
                m_data[i + range] = m_data[i];
            }
            m_last_item_index += (long int)range;
            size_t j = 0;
            for (size_t i = index; i < index + range; i++) {
                m_data[i] = items[j++];
            }
        }
    }
    //pop
    void pop() {
        --m_last_item_index;
    }
    void pop(size_t index) {
        for (long int i = index; i < m_last_item_index; i++) {
            m_data[i] = m_data[i + 1];
        }
        --m_last_item_index;
    }
    //overloads
    void operator=(Vec &object) {
        if (m_data) {
            delete[] m_data;
        }
        m_size = object.max_size();
        m_last_item_index = object.size() - 1;
        m_data = object.get_data();
    }
    T operator [](size_t index) {
        if (index > size() - 1 && index < max_size() - 1) {
            return 0;
        } else if (index > max_size() - 1) {
            return -1;
        }
        return m_data[index];
    }
    //service
    bool is_empty() {
        if (m_data[0]) {
            return false;
        }
        return true;
    }
    size_t size() {
        return m_last_item_index + 1;
    }
    size_t max_size() {
        return m_size;
    }
    void clear() {
        m_last_item_index = -1;
    }
    //iterators
    T* begin() {
        return &m_data[0];
    }
    T* end() {
        return &m_data[m_last_item_index];
    }
};

template <typename T>
ostream& operator<<(ostream &out, Vec<T> &object) {
    if (!object.size()) {
        return out;
    }
    T *data = object.get_data();
    for (size_t i = 0; i < object.size(); i++) {
        out << data[i] << ' ';
    }
    out << '\n';

    delete[] data;

    return out;
}

template <typename T>
istream& operator>>(istream &in, Vec<T> &object) {
    T tmp;
    in >> tmp;
    object.push_back(tmp);

    return in;
}
