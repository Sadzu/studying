#pragma once

#include <iostream>
#include <fstream>

template <typename T>
class Item {
	private:
		T* value = NULL;
	public:
		Item(T*);
		Item() {};
		~Item();

		void SetValue(T*);
		T* GetValue();
		void ReadBinaryFile(std::ifstream&);
		void WriteBinaryFile(std::ofstream&);
		void Print();

		bool operator > (const Item<T>&);
		bool operator < (const Item<T>&);
};
