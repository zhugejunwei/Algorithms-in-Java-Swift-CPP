
// Some interesting mini-games


// intelligent-input.cpp
#include <iostream>
#include <map>
#include <sstream>
#include <string>
#include <vector>
using namespace std;

int main()
{
    vector <pair <int, int> > pairs;
    string s;
    int a, b;
    
    cout << "Enter a list of pairs of integers, one pair per line, as\n  1 2\n"
    "Press ENTER on a blank line to finish.\n\n> ";
    
    // While there is input and the user has not simply pressed ENTER
    while (getline( cin, s ) && !s.empty())
    {
        stringstream ss( s );
        ss >> a >> b;
        if (!ss)
            cout << "Invalid pair. Try again";
        else
            pairs.push_back( make_pair( a, b ) );
        cout << "> ";
    }
    
    /*    -----------------
     Game 1
     -----------------*/
    //        // Now get a single number from the user, which we'll use to search the list
    //    cout << "\nThank you. Now enter a number to find> ";
    //    while (getline( cin, s ))
    //    {
    //    if (stringstream( s ) >> a) break;
    //    cout << "Invalid integer. Try again> ";
    //    }
    //
    //    // Count the number of times the user's number appears in the list
    //    b = 0;
    //    for (vector <pair <int, int> > ::iterator p = pairs.begin(); p != pairs.end(); p++)
    //    {
    //        if (p->first  == a) b++;
    //        if (p->second == a) b++;
    //    }
    //
    //    switch (b)
    //    {
    //        case 0:  cout << "That number does not appear in the list.\n";
    //        case 1:  {cout << "That number appears in the list 1 time.\n"; break;}
    //        default: cout << "That number appears in the list " << b << " times.\n";
    //    }
    
    /*   -----------------
     Game 2
     -----------------*/
    //Compare the pairs that user entered
    std::cout<<"Below is the sorted pair (big - small): \n";
    for (vector<pair<int, int>>::iterator it = pairs.begin(); it != pairs.end(); ++it)
    {
        if (it->first >= it->second)
            std::cout<< '>' <<it->first <<" "<<it->second<<"\n";
        else
            std::cout<< '>' <<it->second <<" "<<it->first<<"\n";
    }
    
    
    
    cout << "\nThanks for playing. Bye!\n";
    return 0;
}