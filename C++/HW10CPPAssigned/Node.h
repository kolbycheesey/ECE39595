#ifndef NODE_H_
#define NODE_H_
#include <iostream>
#include "Int.h"

template <typename N>
class Node { 
private:
   N data;
   Node* left = nullptr;
   Node* right = nullptr;
public:
   Node( );
   //Node(const int data);
   Node(N content);
   void print( ) const;
   //Node* insertNode(const int n);
   Node* insertNode(N content);
};

template <typename N>
Node<N>::Node( ) { }


template <typename N> Node<N>::Node(N content) : data(content) { }

template <typename N>
void Node<N>::print( ) const {
   if (this->left != nullptr) {
      this->left->print( );
   }
   std::cout << data << " ";
   if (this->right != nullptr) {
      this->right->print( );
   }
}

template <typename N>
Node<N>* Node<N>::insertNode(N content) {
   if (this->data == content) return this;

   if (content < this->data) {
      if (left != nullptr) return left->insertNode(content); 
      else {
         left = new Node(content);
         return left;
      }
   }

   if (content > this->data) {
      if (right != nullptr) return right->insertNode(content); 
      else {
         right = new Node(content);
         return left;
      }
   }

   return nullptr;
}

#endif /* NODE_H_ */
