#include <iostream>
#include "../Headers/array.h"
#include "../Headers/item.h"
#include <fstream>

//template Arr<char>;

template <typename T>
Arr<T>::~Arr() {
	for (int i = 0; i < count; i++)
	{
		delete arr[i];
	}
	count = 0;
}

template <typename T>
void Arr<T>::CreateList(int sizeList) {
	if (count == 0) {
		arr = new List<T>*[size] { NULL };
	} else if(count == size) {
		size *= 2;
		List<T>** newArr = new List<T>*[size] { NULL };
		for (int i = 0; i < count; i++) {
			newArr[i] = arr[i];
		}
		arr = newArr;
	}

	List<T>* list = new List<T>(sizeList);
	arr[count] = list;
	count++;
}

template <typename T>
bool Arr<T>::Insert(T* value)
{
	bool isInsert = false;

	for (int i = 0; i < count; ++i)
	{
		if (arr[i]->GetSize() != arr[i]->GetCount())
		{
			arr[i]->Insert(value);
			isInsert = true;
			break;
		}
	}

	return isInsert;
}

template <typename T>
bool Arr<T>::Insert(T* value, int indexList, int indexValue)
{
	bool isInsert = false;

	if (arr[indexList]->GetCount() != arr[indexList]->GetSize())
	{
		arr[indexList]->Insert(value, indexValue);
		isInsert = true;
	}

	return isInsert;
}

template <typename T>
void Arr<T>::DeleteItem(int indexList, int indexValue)
{
	arr[indexList]->DeleteTo(indexValue);
}

template <typename T>
void Arr<T>::DeleteList(int indexList)
{
	delete arr[indexList];
	for (int i = indexList; i < count - 1; i++)
	{
		arr[i] = arr[i + 1];
	}
	arr[count - 1] = NULL;
	count--;
}

template <typename T>
void Arr<T>::Sort()
{
	int totalCountItems = 0;

	for (int i = 0; i < count; i++)
	{
		totalCountItems += arr[i]->GetCount();
	}

	Item<T>** items = new Item<T>*[totalCountItems];
	int indexItems = 0;
	int indexList = 0;
	while(true)
	{
		if (indexList == count)
			break;

		for (int i = 0; i < arr[indexList]->GetCount(); i++)
		{
			items[indexItems] = arr[indexList]->GetItem(i);
			indexItems++;
		}

		indexList++;
	}

	for (int i = 0; i < indexItems - 1; i++)
	{
		for (int k = indexItems - 1; k > i; k--)
		{
			if (*items[k] < *items[k - 1])
			{
				T* temp = items[k]->GetValue();
				items[k]->SetValue(items[k - 1]->GetValue());
				items[k - 1]->SetValue(temp);
			}
		}
	}
}

template <typename T>
bool Arr<T>::InsertSaveOrder(T* value)
{
	bool isInsert = false;
	Item<T>* item = new Item<T>(value);
	Item<T>* oldItem = new Item<T>();
	int indexList = 0;

	for (int i = 0; i < count; i++)
	{
		for (int k = 0; k < arr[i]->GetCount(); k++)
		{
			if (*item < *arr[i]->GetItem(k))
			{
				oldItem->SetValue(arr[i]->Insert(value, k));
				indexList = i + 1;
				isInsert = true;
				break;
			}
		}

		if (isInsert)
			break;
	}

	if (oldItem->GetValue() != NULL)
	{
		for (int i = indexList; i < count; i++)
		{
			if (arr[i]->GetCount() == 0) 
			{
				arr[i]->Insert(oldItem->GetValue(), 0);
				break;
			}

			for (int k = 0; k < arr[i]->GetCount(); k++)
			{
				if (*oldItem < *arr[i]->GetItem(k))
				{
					oldItem->SetValue(arr[i]->Insert(oldItem->GetValue(), k));
				}

				if (oldItem->GetValue() == NULL)
					break;
			}

			if (oldItem->GetValue() == NULL)
				break;
		}
	}

	return isInsert;
}

template <typename T>
void Arr<T>::WriteBinFile(char* nameFile)
{
	std::ofstream fout(nameFile, std::ios::binary | std::ios::out);

	fout.write((char*)&count, sizeof(int));
	for (int i = 0; i < count; i++)
	{
		arr[i]->WriteBinaryFile(fout);
	}

	fout.close();
}

template <typename T>
void Arr<T>::ReadBinFile(char *nameFile)
{
	std::ifstream fin(nameFile, std::ios::binary | std::ios::in);

	this->~Arr();
	fin.read((char*)&count, sizeof(int));
	arr = new List<T>*[count];
	for (int i = 0; i < count; i++)
	{
		List<T>* list = new List<T>(0);
		list->ReadBinaryFile(fin);
		arr[i] = list;
	}

	fin.close(); 
}

template <typename T>
void Arr<T>::PrintList(int index)
{
	std::cout << "������ ��� �������� " << index << " ";
	arr[index]->Print();
}

template <typename T>
void Arr<T>::Print()
{
	for (int i = 0; i < count; i++)
	{
		PrintList(i);
	}
}

template <typename T>
int Arr<T>::GetCount()
{
	return count;
}

template <typename T>
int Arr<T>::GetCountList(int index)
{
	return arr[index]->GetCount();
}

template <typename T>
int Arr<T>::GetSizeList(int index)
{
	return arr[index]->GetSize();
}

template <typename T>
bool Arr<T>::IsHaveFreeList()
{
	for (int i = 0; i < count; i++)
	{
		if (arr[i]->GetCount() != arr[i]->GetSize()) 
		{
			return true; 
		}
	}

	return false; 
}