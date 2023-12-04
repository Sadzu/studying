#pragma once

#include <iostream>
#include <fstream>
#include "item.h"

template <typename T>
class List {
 private:
	int size = 0; 
	int count = 0;
	Item<T>** list = NULL; 
	void ChangeList(int);
 public:
	List(int);
	~List(); 

	int GetSize(); 
	int GetCount(); 
	bool Insert(T*); 
	void SetItem(T*, int); 
	T* Insert(T*, int); 
	void DeleteTo(int); 
	T* GetValue(int); 
	Item<T>* GetItem(int); 
	void ReadBinaryFile(std::ifstream&); 
	void WriteBinaryFile(std::ofstream&); 
	void Print(); 
};
