//
//  Huffman.cpp
//
//
//  Created by 诸葛俊伟 on 1/27/16.
//  Copyright © 2016 诸葛俊伟. All rights reserved.
//

#include <iostream>
#include <queue> // priority queue
#include <map> // map() dictionary
#include <vector> // Huff_code
#include <iterator> // cout map
#include <algorithm>
#include <string>
using namespace std;

#define NChar 8 // use 8 bits to describe all symbols
#define Nsymbols 1 << NChar // can totally describe 256 symbols, including a-z, A-Z
typedef vector<bool> Huff_code; // sizeof(bool) = 8 bits
map<char, Huff_code> Huff_Dic; // Huffman coding dictionary

/* ------------------------------------------------- */
/* - Huffman Tree elements: */
/* - 2 child trees */
/* - character and frequency of current node */
/* ------------------------------------------------- */
class HTree
{
public:
    HTree* left;
    HTree* right;
    int weight;
    char ch;
    
    HTree(){left = right = NULL; ch = '\0'; weight = 0;}
    HTree(HTree* l, HTree* r, int w, char c){left = l; right = r; ch = c; weight = w;}
    ~HTree(){delete left; delete right;}
    bool ISleaf(){return !left && !right;}
};

/* ------------------------------------------------- */
/* - for sorting in priority queue */
/* - we cannot use overloading in class HTree */
/* ------------------------------------------------- */
class Compare_Tree
{
public:
    bool operator()(HTree* t1, HTree* t2)
    {
        return t1->weight > t2->weight;
    }
};

/* ------------------------------------------------- */
/* - use priority queue to store Huffman tree */
/* ------------------------------------------------- */
HTree* BuildTree(int* frequency)
{
    priority_queue<HTree*, vector<HTree*>, Compare_Tree> QTree;
    
    // 1st level
    for (int i = 0; i < Nsymbols; ++i) {
        if (frequency[i]) {
            QTree.push(new HTree(NULL, NULL, frequency[i], (char)i));
        }
    }
    
    // build
    while (QTree.size() > 1)
    {
        HTree* lc = QTree.top();
        QTree.pop();
        HTree* rc = QTree.top();
        QTree.pop();
        
        HTree* parent = new HTree(lc, rc, lc->weight + rc->weight, (char)256);
        QTree.push(parent);
    }
    // return tree root
    return QTree.top();
}

/* ------------------------------------------------- */
/* - start coding with HTree */
/* ------------------------------------------------- */
void Huffman_coding(HTree* root, Huff_code& curcode)
{
    if (root->ISleaf())
    {
        Huff_Dic[root->ch] = curcode;
        return;
    }
    Huff_code lcode = curcode;
    Huff_code rcode = curcode;
    lcode.push_back(false);
    rcode.push_back(true);
    
    Huffman_coding(root->left, lcode);
    Huffman_coding(root->right, rcode);
}

int main()
{
    int freq[Nsymbols] = {0};
    string str2 = "This is my first Huffman encoding";
    const char* str = str2.c_str(); // convert string to char*
    
    // statistic character frequency
    while (*str != '\0')
        freq[*str++]++; //
    
    // build tree
    HTree* myRoot = BuildTree(freq);
    Huff_code nullcode;
    nullcode.clear();
    Huffman_coding(myRoot, nullcode);
    
    for (map<char, Huff_code>::iterator it = Huff_Dic.begin(); it != Huff_Dic.end(); it++) {
        cout<<(*it).first<<"\t";
        std::copy(it->second.begin(), it->second.end(), std::ostream_iterator<bool>(cout));
        cout<<endl;
    }
}