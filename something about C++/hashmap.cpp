//
//  hashmap.cpp
//  
//
//  Created by 诸葛俊伟 on 1/19/16.
//
//

#include <stdio.h>
#include <map>
#include <iostream>
#include <cassert>

int main(int argc, char **argv)
{
    std::map<std::string, int> m;
    m["hello"] = 23;
    // check if key is present
    if (m.find("world") != m.end())
        std::cout << "map contains key world!\n";
    // retrieve
    std::cout << m["hello"] << '\n';
    std::map<std::string, int>::iterator i = m.find("hello");
    assert(i != m.end());
    std::cout << "Key: " << i->first << " Value: " << i->second << '\n';
    return 0;
}