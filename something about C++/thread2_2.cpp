//
//  thread2_2.cpp
//  
//
//  Created by 诸葛俊伟 on 1/19/16.
//
//

//Write a multi threaded C code with one thread printing all even numbers and the other all odd numbers. The output should always be in sequence
//ie. 0,1,2,3,4....etc

#include <stdio.h>
#include "stdafx.h"
#include <iostream>
#include <thread>
#include <Windows.h>
#include <WinBase.h>

using namespace std;

static int i=0;
CRITICAL_SECTION CritSection;
CONDITION_VARIABLE ConditionVarodd;
CONDITION_VARIABLE ConditionVareven;

void printodd()
{
    while (i<;150)
    {
        EnterCriticalSection(&CritSection);
        
        if(i%2 == 0)
        {
            SleepConditionVariableCS(&ConditionVarodd, &CritSection, INFINITE);
        }
        
        printf("\n odd %d",i);
        i++;
        LeaveCriticalSection(&CritSection);
        WakeConditionVariable(&ConditionVareven);
        
    }
}
void printeven()
{
    while(i<;150)
    {
        EnterCriticalSection(&CritSection);
        
        if(i%2 == 1)
        {
            SleepConditionVariableCS(&ConditionVareven, &CritSection, INFINITE);
        }
        
        printf("\n even %d",i);
        i++;
        LeaveCriticalSection(&CritSection);
        WakeConditionVariable(&ConditionVarodd);
        
    }
}

int main()
{
    InitializeCriticalSection(&CritSection);
    InitializeConditionVariable(&ConditionVarodd);
    InitializeConditionVariable(&ConditionVareven);
    thread t1(printodd);
    thread t2(printeven);
    t1.join();
    t2.join();
    DeleteCriticalSection(&CritSection);
    WakeAllConditionVariable(&ConditionVarodd);
    WakeAllConditionVariable(&ConditionVareven);
    return 0;
}