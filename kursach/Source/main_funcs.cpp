#include "../Headers/main_funcs.h"
#include "../Headers/array.h"
#include "../Headers/item.h"
#include "../Headers/list.h"

//Функция вывода пунктов меню и что они делают
void PrintHeader() {
    //3 символ в аски это сердечко :)
    std::cout << "\t" << (char)3 << "Меню" << (char)3 << "\n" 
        << "1 - Создание нового списка\n"
        << "2 - Создание элемента и добавление в первый свободный список\n"
        << "3 - Создание элемента и добавление в список по номеру\n"
        << "4 - Создание элемента и добавление с сохранением порядка\n"
        << "5 - Удаление элемента по индексу в выбранном списке\n"
        << "6 - Удаление списка по индексу\n"
        << "7 - Вывести массив\n"
        << "8 - Сортировка массива\n"
        << "9 - Записать в бинарный файл\n"
        << "10 - Считать бинарный файл\n"
        << "11 - Сгенерировать случайно элементы и заполнить массив\n"
        << "12 - Закончить выбор\n";
}

//Функция для ввода строки из консоли
char* CreateString() {
    std::cout << "Введите строку\n";
    char* str = new char[50]; //Выделяем максимальную длину для строки 50
    std::cin.ignore(); //Игнорируем для корректной работы getline
    std::cin.getline(str, 50); //Считываем строчку из консоли

    return str;
}

//Функция для генерации случайной строчки
char* GenerateString(const int len) {
    static const char symbols[] = "abcdefghijklmnopqrstuvwxyz"; //массив возможных символов для генерации
    char* str = new char[len]; //выделяем память под новую строчку

    for (int i = 0; i < len; ++i) {
        str[i] = symbols[rand() % (sizeof(symbols) - 1)]; //заполняем каждый элемент строки случайной буквой
    }
    str[len] = '\0'; //ставим знак конца строки

    return str;
}

//Функция заполнения массива случайными строчками
void FillArray(Arr<char>* arr, int sizeList, int countStr) {
    for (int i = 0; i < countStr; i++) {
        char* str = GenerateString(rand() % 10 + 1); //генерируем случайную строчку
        if (!arr->IsHaveFreeList()) { //Если в массиве нет свободного места для вставки то
            arr->CreateList(sizeList); //создаем новый список с размером, который мы передали
        }

        arr->Insert(str); //Вставляем созданную строчку в массив
    }
}

//Функция для вввода в консоль номера
int SelectNumber(int min, int max) {
    if (min > max)
        max = min;
    int number;

    std::cout << " в диапазоне от " << min << " до " << max << std::endl;

    while (true) {
        std::cin >> number;
        if (!std::cin) { //Если ввели какое-то некорректное значение, то очищаем консоль
            std::cin.clear(); 
            while (std::cin.get() != '\n');
            std::cout << "Вы ввели некорректное значение!\n";
        } else if(number < min || number > max) { //Если число не входит в диапазон, то выводим сообщение об ошибке
            std::cout << "Значение не входит в диапазон!\n";
        } else { //Если число прошло все условия, то возвращаем, прерывая бесконечный цикл
            break;
        }
    }

    return number;
}

void Menu() {
    Arr<char>* arr = new Arr<char>(); //Наш массив
    int point; //Переменная выбранного пункта
    int minPoint = 1, maxPoint = 12; //Минимальный номер пункта и максимальный номер пункта
    int minSizeList = 1, maxSizeList = 1000; //Минимальная длина списка и максимальная длина списка
    char nameFile[] = "List.bin"; //Название бинарного файла

    while (true) {
        system("cls");
        PrintHeader(); //Выводим пункты
        std::cout << "Выберите пункт";
        point = SelectNumber(minPoint, maxPoint); //Вводим пункт

        if (point == maxPoint) //Если выбран пункт, который завершает меню, то выходим из бесконечного цикла
            break;

        system("cls");

        switch (point) {
            case 1: //Создание нового списка
            {
                std::cout << "Введите размер создаваемо списка";
                int sizeList = SelectNumber(minSizeList, maxSizeList);
                arr->CreateList(sizeList);
                break;
            }
            case 2: //Создание и вставка нового элемента
            {
                if (arr->IsHaveFreeList()) //Проверка, что есть свободный список
                {
                    char* str = CreateString();
                    arr->Insert(str);
                }
                else
                {
                    std::cout << "В массиве нет свободных списков\n";
                    system("pause");
                }
                break;
            }
            case 3: //Создание и вставка элемента по номеру
            {
                if (arr->IsHaveFreeList()) //Проверка на свободный список
                {
                    char* str = CreateString();
                    std::cout << "Введите индекс списка";
                    int indexList = SelectNumber(0, arr->GetCount() - 1);
                    arr->PrintList(indexList);
                    if (arr->GetCountList(indexList) != arr->GetSizeList(indexList))
                    {
                        std::cout << "Введите индекс для вставки элемента";
                        int indexItem = SelectNumber(0, arr->GetCountList(indexList));
                        arr->Insert(str, indexList, indexItem);
                    }
                    else
                    {
                        std::cout << "Список переполнен\n";
                        system("pause");
                    }
                }
                else
                {
                    std::cout << "В массиве нет свободных списков\n";
                    system("pause");
                }
                break;
            }
            case 4: //Создание и вставка с сохранением порядка
            {
                if(arr->IsHaveFreeList())
                {
                    char* str = CreateString();
                    arr->InsertSaveOrder(str);
                }
                else
                {
                    std::cout << "В массиве нет свободных списков\n";
                    system("pause");
                }
                break;
            }
            case 5: //Удаление значения из списка
            {
                if (arr->GetCount())
                {
                    std::cout << "Введите индекс списка";
                    int indexList = SelectNumber(0, arr->GetCount() - 1);
                    arr->PrintList(indexList);
                    std::cout << "Введите индекс элемента";
                    int indexItem = SelectNumber(0, arr->GetCountList(indexList) - 1);
                    arr->DeleteItem(indexList, indexItem);
                }
                else
                {
                    std::cout << "В массиве нет списков\n";
                    system("pause");
                }
                break;
            }
            case 6: //Удаление списка из массива
            {
                if (arr->GetCount())
                {
                    std::cout << "Введите индекс списка";
                    int indexList = SelectNumber(0, arr->GetCount() - 1);
                    arr->DeleteList(indexList);
                }
                else
                {
                    std::cout << "В массиве нет списков\n";
                    system("pause");
                }
                break;
            }
            case 7: //Вывод массива
            {
                arr->Print();
                system("pause");
                break;
            }
            case 8: //Сортировка массива
            {
                clock_t start_time = clock();
                arr->Sort();
                clock_t end_time = clock();
                std::cout << "Массив отсортирован за " << (double)(end_time - start_time) / CLOCKS_PER_SEC << "с\n";
                system("pause");
                break;
            }
            case 9: //Запись в бинарный файл массив
            {
                clock_t start_time = clock();
                arr->WriteBinFile(nameFile);
                clock_t end_time = clock();
                std::cout << "Массив записан в файл за " << (double)(end_time - start_time) / CLOCKS_PER_SEC << "с\n";
                system("pause");
                break;
            }
            case 10: //Чтение из бинарного файла
            {
                clock_t start_time = clock();
                arr->ReadBinFile(nameFile);
                clock_t end_time = clock();
                std::cout << "Массив считан из файла за " << (double)(end_time - start_time) / CLOCKS_PER_SEC << "с\n";
                system("pause");
                break;
            }
            case 11: //Заполнение массива случайными строками
            {
                std::cout << "Введите количество элементов";
                int count = SelectNumber(1, 1000000);
                clock_t start_time = clock();
                FillArray(arr, maxSizeList, count);
                clock_t end_time = clock();
                std::cout << "Массив заполнился за " << (double)(end_time - start_time) / CLOCKS_PER_SEC << "с\n";
                system("pause");
                break;
            }
            default:
            {
                break;
            }
        }
    }
}
