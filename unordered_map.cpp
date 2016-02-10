//
//  main.cpp
//  test_with_h
//
//  Created by 诸葛俊伟 on 2/9/16.
//  Copyright © 2016 诸葛俊伟. All rights reserved.
//

// File "main.cpp"




#include <iostream>
#include <string>
#include <unordered_map>

int main ()
{
    std::unordered_map<std::string,double> mymap = {
        {"mom",5.4},
        {"dad",6.1},
        {"bro",5.9} };
    
    std::string input;
    std::cout << "who? \n";
    getline (std::cin,input);// getline
    
    std::unordered_map<std::string,double>::const_iterator get = mymap.find (input);
    
    if ( get == mymap.end() )
        std::cout << "not found";
    else
        std::cout << get->first << " is " << get->second;
    
    std::cout << std::endl;
    
    return 0;
}