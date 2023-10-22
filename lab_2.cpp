#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>

#define MAX_EXAMS 100
#define MAX_GRADES 10
#define MAX_NAME_LENGTH 50
#define FILENAME "table.txt"

typedef struct {
    char examName[MAX_NAME_LENGTH];
    char examDate[MAX_NAME_LENGTH];
    char teacherSurname[MAX_NAME_LENGTH];
    int numGrades;
    int grades[MAX_GRADES];
} Record;

typedef struct {
    Record records[MAX_EXAMS];
    int numRecords;
} Table;

void inputRecord(Table* table);
void saveTable(const Table* table);
void loadTable(Table* table);
void printTable(const Table* table);
void sortTable(Table* table);
int searchTable(const Table* table, const char* examName);
void deleteRecord(Table* table, int index);
void editRecord(Table* table, int index);
void calculateTotalGrade(const Table* table);

int main() {
    setlocale(LC_ALL, "RU");
    Table table;
    table.numRecords = 0;

    int choice;
    int index;
    char examName[MAX_NAME_LENGTH];

    while (1) {
        printf("\n");
        printf("1. Ввод записи таблицы с клавиатуры\n");
        printf("2. Загрузка таблицы из файла\n");
        printf("3. Сохранение таблицы в файл\n");
        printf("4. Просмотр таблицы\n");
        printf("5. Сортировка таблицы по полю\n");
        printf("6. Поиск записи в таблице\n");
        printf("7. Удаление записи\n");
        printf("8. Изменение записи\n");
        printf("9. Вычисление общей оценки\n");
        printf("0. Выход\n");
        printf("Выберите операцию: ");
        scanf("%d", &choice);

        switch (choice) {
        case 1:
            inputRecord(&table);
            break;
        case 2:
            loadTable(&table);
            break;
        case 3:
            saveTable(&table);
            break;
        case 4:
            printTable(&table);
            break;
        case 5:
            sortTable(&table);
            break;
        case 6:
            printf("Введите название экзамена для поиска: ");
            scanf("%s", examName);
            index = searchTable(&table, examName);
            if (index == -1) {
                printf("Запись не найдена\n");
            }
            else {
                printf("Запись найдена. Индекс: %d\n", index);
            }
            break;
        case 7:
            printf("Введите индекс записи для удаления: ");
            scanf("%d", &index);
            deleteRecord(&table, index);
            break;
        case 8:
            printf("Введите индекс записи для редактирования: ");
            scanf("%d", &index);
            editRecord(&table, index);
            break;
        case 9:
            calculateTotalGrade(&table);
            break;
        case 0:
            exit(0);
        default:
            printf("Некорректный выбор операции\n");
        }
    }

    return 0;
}

void inputRecord(Table* table) {
    if (table->numRecords == MAX_EXAMS) {
        printf("Достигнуто максимальное количество записей\n");
        return;
    }

    Record record;

    printf("Введите название экзамена: ");
    scanf("%s", record.examName);

    printf("Введите дату экзамена: ");
    scanf("%s", record.examDate);

    printf("Введите фамилию преподавателя: ");
    scanf("%s", record.teacherSurname);

    printf("Введите количество оценок: ");
    scanf("%d", &record.numGrades);

    printf("Введите оценки: ");
    for (int i = 0; i < record.numGrades; i++) {
        scanf("%d", &record.grades[i]);
    }

    table->records[table->numRecords++] = record;

    printf("Запись добавлена\n");
}

void saveTable(const Table* table) {
    FILE* file = fopen(FILENAME, "w");
    if (file == NULL) {
        printf("Не удалось открыть файл\n");
        return;
    }

    for (int i = 0; i < table->numRecords; i++) {
        fprintf(file, "%s,%s,%s,%d", table->records[i].examName, table->records[i].examDate,
            table->records[i].teacherSurname, table->records[i].numGrades);

        for (int j = 0; j < table->records[i].numGrades; j++) {
            fprintf(file, ",%d", table->records[i].grades[j]);
        }

        fprintf(file, "\n");
    }

    fclose(file);

    printf("Таблица сохранена в файл\n");
}

void loadTable(Table* table) {
    FILE* file = fopen(FILENAME, "r");
    if (file == NULL) {
        printf("Не удалось открыть файл\n");
        return;
    }

    table->numRecords = 0;

    char line[MAX_NAME_LENGTH * 3 + MAX_GRADES * 3 + 4];

    while (fgets(line, sizeof(line), file) != NULL) {
        Record record;

        char* token = strtok(line, ",");
        strcpy(record.examName, token);

        token = strtok(NULL, ",");
        strcpy(record.examDate, token);

        token = strtok(NULL, ",");
        strcpy(record.teacherSurname, token);

        token = strtok(NULL, ",");
        record.numGrades = atoi(token);

        for (int i = 0; i < record.numGrades; i++) {
            token = strtok(NULL, ",");
            record.grades[i] = atoi(token);
        }

        table->records[table->numRecords++] = record;
    }

    fclose(file);

    printf("Таблица загружена из файла\n");
}

void printTable(const Table* table) {
    if (table->numRecords == 0) {
        printf("Таблица пуста\n");
        return;
    }

    printf("==============================================================\n");
    printf("Название экзамена\tДата экзамена\tФамилия преподавателя\tКоличество оценок\tОценки\n");
    printf("==============================================================\n");

    for (int i = 0; i < table->numRecords; i++) {
        printf("%s\t\t%s\t%s\t\t%d\t\t", table->records[i].examName, table->records[i].examDate,
            table->records[i].teacherSurname, table->records[i].numGrades);

        for (int j = 0; j < table->records[i].numGrades; j++) {
            printf("%d ", table->records[i].grades[j]);
        }

        printf("\n");
    }

    printf("==============================================================\n");
}

void sortTable(Table* table) {
    if (table->numRecords == 0) {
        printf("Таблица пуста\n");
        return;
    }

    int fieldChoice;
    printf("Выберите поле для сортировки:\n");
    printf("1. Название экзамена\n");
    printf("Ваш выбор: ");
    scanf("%d", &fieldChoice);

    switch (fieldChoice) {
    case 1:
        for (int i = 0; i < table->numRecords - 1; i++) {
            for (int j = 0; j < table->numRecords - i - 1; j++) {
                if (strcmp(table->records[j].examName, table->records[j + 1].examName) > 0) {
                    Record temp = table->records[j];
                    table->records[j] = table->records[j + 1];
                    table->records[j + 1] = temp;
                }
            }
        }
        printf("Таблица отсортирована по названию экзамена\n");
        break;
    default:
        printf("Некорректный выбор поля\n");
        break;
    }
}

int searchTable(const Table* table, const char* examName) {
    if (table->numRecords == 0) {
        printf("Таблица пуста\n");
        return -1;
    }

    for (int i = 0; i < table->numRecords; i++) {
        if (strcmp(table->records[i].examName, examName) == 0) {
            return i;
        }
    }

    return -1;
}

void deleteRecord(Table* table, int index) {
    if (index < 0 || index >= table->numRecords) {
        printf("Некорректный индекс записи\n");
        return;
    }

    for (int i = index; i < table->numRecords - 1; i++) {
        table->records[i] = table->records[i + 1];
    }

    table->numRecords--;

    printf("Запись удалена\n");
}

void editRecord(Table* table, int index) {
    if (index < 0 || index >= table->numRecords) {
        printf("Некорректный индекс записи\n");
        return;
    }

    Record* record = &(table->records[index]);

    printf("Введите новое название экзамена: ");
    scanf("%s", record->examName);

    printf("Введите новую дату экзамена: ");
    scanf("%s", record->examDate);

    printf("Введите новую фамилию преподавателя: ");
    scanf("%s", record->teacherSurname);

    printf("Введите новое количество оценок: ");
    scanf("%d", &(record->numGrades));

    printf("Введите новые оценки: ");
    for (int i = 0; i < record->numGrades; i++) {
        scanf("%d", &(record->grades[i]));
    }

    printf("Запись изменена\n");
}

void calculateTotalGrade(const Table* table) {
    if (table->numRecords == 0) {
        printf("Таблица пуста\n");
        return;
    }

    int totalGrade = 0;

    for (int i = 0; i < table->numRecords; i++) {
        for (int j = 0; j < table->records[i].numGrades; j++) {
            totalGrade += table->records[i].grades[j];
        }
    }

    printf("Общая оценка: %d\n", totalGrade);
}