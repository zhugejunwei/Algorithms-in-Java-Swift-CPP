//
//  LCM_GCD.cpp
//  
//
//  Created by 诸葛俊伟 on 1/19/16.
//
//


//LCM和GCD是Google常考的数学知识, 很高兴你问到~

#include <stdio.h>

public static int getLCM(int a, int b){
    return (a*b)/getGCD(a, b);
}
public static int getGCD(int a, int b){
    if(a==b) return a;
    if(a>b) return getGCD(a-b, b);
    else return getGCD(a, b-a);
}