//
//  Binary Tree Traversal.cpp -- -- Recursive
//
//
//  Created by 诸葛俊伟 on 2/10/16.
//  Copyright © 2016 诸葛俊伟. All rights reserved.
//
// Time Complexity: O(n)
// Space Complexity: O(h) Worst: O(n) best/average: O(logn)

#include <iostream>
#include <cstdlib>
#include <vector>
#include <sstream> //stringstream

// Tree Node
struct BiTNode
{
    char data;
    BiTNode *left;
    BiTNode *right;
    
    BiTNode(char c): left(NULL), right(NULL), data(c){}
    BiTNode(const BiTNode *t): data(t->data), left(NULL), right(NULL){}
    ~BiTNode(){delete left; delete right;};
};

// insert TreeNode
void insert(struct BiTNode *&root, struct BiTNode *p)
{
    if((p->data <= root->data) && (root->left != NULL))
        insert(root->left, p);
    else if((p->data <= root->data) && (root->left == NULL))
        root->left = p;
    else if(root->right != NULL)
        insert(root->right, p);
    else root->right = p;
}

// build a tree
BiTNode* createTree(std::vector<char>& vec)
{
    if(vec.size() == 0) return NULL;
    BiTNode* root = new BiTNode(vec[0]);
    std::vector<char>::const_iterator it = vec.begin();
    ++it;
    for (; it != vec.end(); ++it)
    {
        BiTNode *t = new BiTNode(* it);
        insert(root, t);
    }
    return root;
}

//PreOrder Traversal
void PreOrder(struct BiTNode * root)
{
    if (root != NULL)
    {
        std::cout << root->data << " ";
        PreOrder(root->left);
        PreOrder(root->right);
    }
}

//InOrder Traversal
void InOrder(struct BiTNode * root)
{
    if (root != NULL)
    {
        InOrder(root->left);
        std::cout << root->data << " ";
        InOrder(root->right);
    }
}

//PostOrder Traversal
void PostOrder(BiTNode * root)
{
    if (root != NULL) {
        PostOrder(root->left);
        PostOrder(root->right);
        std::cout << root->data <<" ";
    }
}

int main()
{
    std::vector<char> data;
    char ch;
    std::cout<<"Please enter your string:\n";
    //    --------------------------------------------------/
    //    This is a way to get a dynamic string through cin
    //    --------------------------------------------------/
    std::string vector_full_line;
    std::getline(std::cin, vector_full_line);
    std::stringstream vector_stream(vector_full_line);
    for (vector_stream >> ch; vector_stream; vector_stream >> ch) {
        data.push_back(ch);
    }
    
    
    BiTNode* root = createTree(data);
    
    std::cout<<"Recursive function of Binary Tree Traversal:\n";
    std::cout<<"PreOrder: \n";
    PreOrder(root);
    std::cout<<std::endl;
    std::cout<<"InOrder: \n";
    InOrder(root);
    std::cout<<std::endl;
    std::cout<<"PostOrder: \n";
    PostOrder(root);
    std::cout<<std::endl;
}