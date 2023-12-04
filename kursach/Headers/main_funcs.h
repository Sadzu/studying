#pragma once

#include <iostream>
#include <ctime>
#include "array.h"
#include "array_funcs.h"
#include "item.h"
#include "item_funcs.h"
#include "list.h"
#include "list_funcs.h"

void Menu();
void PrintHeader();
char *CreateString();
char* GenerateString(const int len);
void FillArray(Arr<char>* arr, int sizeList, int countStr);
int SelectNumber(int min, int max);