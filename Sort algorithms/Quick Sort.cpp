//
//  main.cpp
//  QuickSort
//
//  Created by 诸葛俊伟 on 1/21/16.
//  Copyright © 2016 诸葛俊伟. All rights reserved.
//

#include <iostream>
using namespace std;

void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

int partition(int data[], int lo, int hi)
{
    int left_p,right_p;
//    int key = data[hi];
    left_p = lo-1;
    for (right_p = lo; right_p<=hi; ++right_p)
        if (data[right_p]<data[hi]) {
            ++left_p;
            swap(data[left_p], data[right_p]);
        }  
//    data[hi] = data[left_p+1];
//    data[left_p+1] = key;
    swap(data[hi], data[left_p+1]);
    
    return left_p + 1;
}

void QuickSort(int data[], int lo, int hi)
{
    if (lo<hi) {
        int key = partition(data, lo, hi);
        QuickSort(data, lo, key-1);
        QuickSort(data, key+1, hi);
    }
}

int main()
{
    int a[] = {4,9,3,6,5,7,1211,321,1,2,1};
    QuickSort(a, 0, 10);
    for (int i=0; i<11; ++i) {
        cout << "  "<<a[i];
    }
    cout<<endl;
    return 0;
}
