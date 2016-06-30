//
//  thread.cpp
//  
//
//  Created by 诸葛俊伟 on 1/19/16.
//
//

//There are three threads in a process.
//The first thread prints 1 1 1 …, the second one prints 2 2 2 …, and the third one prints 3 3 3 … endlessly.
//How do you schedule these three threads in order to print 1 2 3 1 2 3 …?

#include "thread.hpp"
#include <stdafx.h>
#include <iostream>
#include <thread>
#include <mutex>
#include <vector>
#include <condition_variable>
using namespace std;

#define MAXNUM 90

int num = 1;
condition_variable oneCondition;
condition_variable twoCondition;
condition_variable thirdCondition;
mutex guard;

void thread3()
{
    while (num <= MAXNUM)
    {
        if (num % 3 != 0)
        {
            unique_lock<mutex> lck(guard);
            thirdCondition.wait(lck);
        }
        //cout << "Thread3 : " << num << endl;
        cout << 3;
        ++num;
        oneCondition.notify_one();
    }
}

void thread2()
{
    while (num <= MAXNUM-1)
    {
        if (num % 3 != 2)
        {
            unique_lock<mutex> lck(guard);
            twoCondition.wait(lck);
        }
        //cout << "Thread2 : " << num<<endl;
        cout << 2;
        ++num;
        thirdCondition.notify_one();
    }
}

void thread1()
{
    while (num <= MAXNUM-2)
    {
        if (num % 3 != 1)
        {
            unique_lock<mutex> lck(guard);
            oneCondition.wait(lck);
        }
        //cout << "Thread1 : " << num << endl;;
        cout << 1;
        ++num;
        twoCondition.notify_one();
    }
}

int main()
{
    thread t1(thread1), t2(thread2), t3(thread3);
    t1.join(), t2.join(), t3.join();
    getchar();
}