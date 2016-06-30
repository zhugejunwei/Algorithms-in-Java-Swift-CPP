//
//  thread2.cpp
//  
//
//  Created by 诸葛俊伟 on 1/19/16.
//
//

//Write a multi threaded C code with one thread printing all even numbers and the other all odd numbers. The output should always be in sequence
//ie. 0,1,2,3,4....etc

#include "thread2.hpp"
#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>

std::mutex mtx;
std::condition_variable cv;
bool doPrintOdd = false;
bool doPrintEven= true;
int nMax = 100;

void printOdd() {
    for (int i = 0; i < nMax; ++i) {
        std::unique_lock<std::mutex> lck(mtx);
        while (!doPrintOdd) cv.wait(lck);
        std::cout << 2 * i + 1 << std::endl;
        doPrintOdd = false;
        doPrintEven = true;
        cv.notify_one();
    }
}

void printEven() {
    for (int i = 0; i < nMax; ++i) {
        std::unique_lock<std::mutex> lck(mtx);
        while (!doPrintEven) cv.wait(lck);
        std::cout << 2 * i << std::endl;
        doPrintEven = false;
        doPrintOdd = true;
        cv.notify_one();
    }
}

int main() {
    std::thread t1(printOdd);
    std::thread t2(printEven);
    
    t1.join();
    t2.join();
    
    std::cout << "Press Enter to continue ..." << std::endl;
    std::cin.get();
    return 0;
}