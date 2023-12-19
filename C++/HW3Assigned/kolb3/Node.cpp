#include <iostream>
#include "Integer.h"
#include "Node.h"
#include "LinkedList.h"

Node::Node( ) : value(new Integer( ) ), nextNode(nullptr) { 
   //value = new Integer( ); 
   //nextNode = nullptr;
}

Node::Node(int v) : value(new Integer( v ) ), nextNode(nullptr) {
   //value = new Integer(v), nextNode = nullptr; 
}

Node::~Node( ) { }

Node* Node::next( ) {
   return nextNode;
}

void Node::setNext(Node* n) {
   nextNode = n;
}

void Node::print( ) {
   if (value != nullptr) {
      value->print( );
   } 
   std::cout << std::endl;

   if (nextNode != nullptr) {
      nextNode->print( );
   }
}
