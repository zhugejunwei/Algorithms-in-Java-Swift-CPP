/************************************************************************/
/*  File Name: Huffman.cpp
 *       @Function: Lossless Compression
 @Author: Sophia Zhang
 @Create Time: 2012-9-26 10:40
 @Last Modify: 2012-9-26 12:10
 */
/************************************************************************/

#include"iostream"
#include "queue"
#include "map"
#include "string"
#include "iterator"
#include "vector"
#include "algorithm"
using namespace std;

#define NChar 8 //suppose use 8 bits to describe all symbols
#define Nsymbols 1<<NChar //can describe 256 symbols totally (include a-z, A-Z)
typedef vector<bool> Huff_code;//8 bit code of one char
map<char,Huff_code> Huff_Dic; //huffman coding dictionary

/************************************************************************/
/* Tree Class elements:
 *2 child trees
 *character and frequency of current node
 */
/************************************************************************/
class HTree
{
    public :
    HTree* left;
    HTree* right;
    char ch;
    int weight;
    
    HTree(){left = right = NULL; weight=0;ch ='\0';}
    HTree(HTree* l,HTree* r,int w,char c){left = l; right = r;  weight=w;   ch=c;}
    ~HTree(){delete left; delete right;}
    bool Isleaf(){return !left && !right; }
};

/************************************************************************/
/* prepare for pointer sorting*/
/*because we cannot use overloading in class HTree directly*/
/************************************************************************/
class Compare_tree
{
public:
    bool operator () (HTree* t1, HTree* t2)
    {
        return t1->weight> t2->weight;
    }
};

/************************************************************************/
/* use priority queue to build huffman tree*/
/************************************************************************/
HTree* BuildTree(int *frequency)
{
    priority_queue<HTree*,vector<HTree*>,Compare_tree> QTree;
    
    //1st level add characters
    for (int i=0;i<Nsymbols;i++)
    {
        if(frequency[i])
            QTree.push(new HTree(NULL,NULL,frequency[i],(char)i));
    }
    
    //build
    while (QTree.size()>1)
    {
        HTree* lc  = QTree.top();
        QTree.pop();
        HTree* rc = QTree.top();
        QTree.pop();
        
        HTree* parent = new HTree(lc,rc,lc->weight+rc->weight,(char)256);
        QTree.push(parent);
    }
    //return tree root
    return QTree.top();
}

/************************************************************************/
/* Give Huffman Coding to the Huffman Tree*/
/************************************************************************/
void Huffman_Coding(HTree* root, Huff_code& curcode)
{
    if(root->Isleaf())
    {
        Huff_Dic[root->ch] = curcode;
        return;
    }
    Huff_code lcode = curcode;
    Huff_code rcode = curcode;
    lcode.push_back(false);
    rcode.push_back(true);
    
    Huffman_Coding(root->left,lcode);
    Huffman_Coding(root->right,rcode);
}

int main()
{
    int freq[Nsymbols] = {0};
    string str2 = "this is the string need to be compressed";
    const char* str= str2.c_str();
    
    //statistic character frequency
    while (*str!='\0')
        freq[*str++]++;
    
    //build tree
    HTree* r = BuildTree(freq);
    Huff_code nullcode;
    nullcode.clear();
    Huffman_Coding(r,nullcode);
    
    for(map<char,Huff_code>::iterator it = Huff_Dic.begin(); it != Huff_Dic.end(); it++)
    {
        cout<<(*it).first<<'\t';
        std::copy(it->second.begin(),it->second.end(),std::ostream_iterator<bool>(cout));
        cout<<endl;
    }
}
