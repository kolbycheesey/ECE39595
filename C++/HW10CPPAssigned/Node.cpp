#include <iostream>
#include "Node.h"
#include "Int.h"

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
