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

struct BiTNode
{
    char data;
    BiTNode *left;
    BiTNode *right;
    
    BiTNode(): left(NULL), right(NULL), data(NULL){};
    ~BiTNode(){delete left; delete right;};
};

void AddBiTNode(BiTNode *, BiTNode *);
void PreOrder(BiTNode *);
void InOrder(BiTNode *);
void PostOrder(BiTNode *);

int main()
{
    BiTNode *root, *a, *b, *c, *d, *e;
    root = new BiTNode;
    a = new BiTNode;
    b = new BiTNode;
    c = new BiTNode;
    d = new BiTNode;
    e = new BiTNode;
    
    a->data = 'a';
    b->data = 'b';
    c->data = 'c';
    d->data = 'd';
    e->data = 'e';
    
    root = a;
    a->left = b;
    a->right = c;
    b->left = d;
    b->right = e;
    
    std::cout<<"Recursive function of Binary Tree Traversal:\n";
    
    // Building a tree -- -- failed!!
    //    std::cout<<"Please enter your data (end with space): \n";
    //    std::cin>>ch;
    //    t = NULL;
    
    //    while (ch != '\n') {
    ////        if (p=(BiTNode *)malloc(sizeof(BiTNode))) {
    //            p->data = ch;
    //            p->left = NULL;
    //            p->right = NULL;
    ////        }
    ////        else
    ////        {
    ////            std::cout<<"Memory allocated error!";
    ////            exit(0);
    ////        }
    //        if (t == NULL)
    //            t = p;
    //        else
    //            AddBiTNode(t,p);
    ////        std::cin>>ch;
    //    }
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


// Building a tree -- -- failed!!
//void AddBiTNode(struct BiTNode *t, struct BiTNode *p)
//{
//    if((p->data <= t->data) && (t->left != NULL))
//        AddBiTNode(t->left, p);
//    else if((p->data <= t->data) && (t->left == NULL))
//        t->left = p;
//    else if(t->right != NULL)
//        AddBiTNode(t->right, p);
//    else t->right = p;
//}

void PreOrder(struct BiTNode * root) //PreOrder Traversal
{
    if (root != NULL)
    {
        std::cout << root->data << " ";
        PreOrder(root->left);
        PreOrder(root->right);
    }
}

void InOrder(struct BiTNode * root)
{
    if (root != NULL)
    {
        InOrder(root->left);
        std::cout << root->data << " ";
        InOrder(root->right);
    }
}

void PostOrder(BiTNode * root)
{
    if (root != NULL) {
        PostOrder(root->left);
        PostOrder(root->right);
        std::cout << root->data <<" ";
    }
}

