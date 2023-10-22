#include <iostream>
#include <stdlib.h>

using namespace std;

int output(int *data, int n); // вывод массива
int sort(int *data, int n); // сортировка
int *bin_search(int *data, int *a, int end, int *flag); // двоичный поиск

int main(){ // инициализация
    // int n = 8;
    // int data[8] = {1, 4, 5, 45, -1, 2, 34, -2};

    int n = rand() % 100 + 1;
    int *data = (int *)malloc(sizeof(int) * n);
    for(int *p = data; p - data < n; p++){
        *p = rand();
    }

    output(data, n);

    sort(data, n);

    cout << '\n';

    output(data, n);

    return 0;
    free(data);
}

int output(int *data, int n){
    for(int *p = data; p - data < n; p++){
        cout << *p << ' ';
    }

    cout << '\n';

    return 0;
}

int *bin_search(int *data, int *a, int end, int *flag){
    int st = 0, ed = end, md = (st + ed)/2;

    while(st < ed){
        md = (st + ed)/2;

        if(*a > *(data + md)){
            st = md + 1;
            
        } else if(*a < *(data + md)){
            ed = md - 1;
            
        } else{
            st = md; ed = md;
            break;
        }
    }

    if(st == ed && *a > *(data + ed)){
        *flag = 0;
        return data + st + 1;
    } else if(ed < st || (st == ed && *a <= *(data + ed))){
        *flag = 0;
        return data + st;
    } else{
        *flag = -1;
        return data;
    }
}

int sort(int *data, int n){
    int end = -1, temp = 0, flag = 0;
    
    int *bug = data + 0, *st = bug;
    
    for(int *p = data + 1; p - data < n; p++){
        end++;
        st = bin_search(data, p, end, &flag);
        if(flag == 0){
            temp = *p;

            for(int *l = p; l > st; l--){
                *l = *(l-1);
            }
            *st = temp;
        }
    }

    return 0;
}
