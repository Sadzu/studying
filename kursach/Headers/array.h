#pragma once

#include <iostream>
#include <fstream>
#include "list.h"

template <typename T>
class Arr {
	private:
		List<T>** arr = NULL; 
		int count = 0; 
		int size = 1;
		
	public:
		~Arr();

		void CreateList(int sizeList); 
		bool Insert(T *value);
		bool Insert(T *value, int indexList, int indexValue); 
		void DeleteItem(int indexList, int indexValue); 
		void DeleteList(int indexList);
		void Sort(); 
		bool InsertSaveOrder(T *value);
		void WriteBinFile(char *nameFile);
		void ReadBinFile(char *nameFile); 
		void PrintList(int index); 
		void Print(); 
		int GetCount(); 
		int GetCountList(int index);
		int GetSizeList(int index);
		bool IsHaveFreeList();
};