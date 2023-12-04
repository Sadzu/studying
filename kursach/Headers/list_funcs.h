#include <iostream>
#include "../Headers/list.h"
#include <fstream>

//template List<char>;

template <typename T>
void List<T>::ChangeList(int _size)
{
	size = _size;
	list = new Item<T>*[size] { NULL };
}

template <typename T>
List<T>::List(int _size)
{
	ChangeList(_size);
}

template <typename T>
List<T>::~List()
{
	for (int i = 0; i < count; i++)
	{
		delete list[i];
	}
	count = 0;
}

template <typename T>
int List<T>::GetSize()
{
	return size;
}

template <typename T>
int List<T>::GetCount()
{
	return count;
}

template <typename T>
void List<T>::SetItem(T* value, int index)
{
	list[index]->SetValue(value);
}

template <typename T>
bool List<T>::Insert(T* value)
{
	if (size != count) {
		Item<T>* item = new Item<T>(value);
		list[count] = item; 
		count++; 
		return true; 
	}

	return false;
}

template <typename T>
T* List<T>::Insert(T* value, int index) 
{
	Item<T>* item = new Item<T>(value); 
	T* oldItem = NULL; 

	if (size == count) 
	{
		oldItem = list[count - 1]->GetValue();
	}

	for (int i = count; i > index; i--) 
	{
		list[i] = list[i - 1];
	}
	list[index] = item;

	if (oldItem == NULL) 
	{
		count++;
	}

	return oldItem;
}

template <typename T>
void List<T>::DeleteTo(int index) 
{
	delete list[index]; 
	for (int i = index; i < count - 1; i++) 
	{
		list[i] = list[i + 1];
	}
	list[count - 1] = NULL; 
	count--; 
}

template <typename T>
T* List<T>::GetValue(int index) 
{
	return list[index]->GetValue();
}

template <typename T>
Item<T>* List<T>::GetItem(int index) 
{
	return list[index]; 
}

template <typename T>
void List<T>::WriteBinaryFile(std::ofstream& fout) 
{
	fout.write((char*)&size, sizeof(int)); 
	fout.write((char*)&count, sizeof(int)); 
	for (int i = 0; i < count; ++i) 
	{
		list[i]->WriteBinaryFile(fout);
	}
}

template <typename T>
void List<T>::ReadBinaryFile(std::ifstream& fin)
{
	fin.read((char*)&size, sizeof(int));
	ChangeList(size);

	fin.read((char*)&count, sizeof(int)); 
	for (int i = 0; i < count; ++i) 
	{
		Item<T>* item = new Item<T>();
		item->ReadBinaryFile(fin);
		list[i] = item; 
	}
}

template <typename T>
void List<T>::Print() 
{
	std::cout << "� ������������ �������� " << size << std::endl;
	for (int i = 0; i < count; ++i) 
	{
		list[i]->Print();
		if (i + 1 != count)
			std::cout << ", ";
		else
			std::cout << std::endl;
	}
}