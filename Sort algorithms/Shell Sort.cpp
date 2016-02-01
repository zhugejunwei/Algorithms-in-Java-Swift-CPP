//
//  shell.cpp
//
//
//  Created by 诸葛俊伟 on 1/30/16.
//  Copyright © 2016 诸葛俊伟. All rights reserved.
//  Lastest modified on 2/1/2016.
//

#include <iostream>
#include <vector>
using namespace std;
//typedef vector<int> number;

void swap(int* x, int* y)
{
    int temp = *y;
    *x = *y;
    *y = temp;
}

void shell(int num[], int size)
{
    int gap, j, i;
    for (gap = size/2; gap > 0; gap /= 2)
        for (i = gap; i < size; ++i)
            for (j = i - gap; j >= 0 && num[j] > num[j+gap]; j -= gap)
            {
                swap(num[j], num[j+gap]);
            }
}

int main()
{
    int num[] = {3,2,1,5,4,6,1,10};
    int size = 8;
    cout<<"original array:"<<endl;
    for (int i = 0; i < size; ++i)
        cout<<num[i]<<" ";
    cout<<endl;
    shell(num, size);
    cout<<"shelled array:"<<endl;
    for (int i = 0; i < size; ++i)
        cout<<num[i]<<" ";
    cout<<endl;
    return 0;
}