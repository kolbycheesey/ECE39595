#include <iostream>
#include "Node.h"
#include "LinkedList.h"

LinkedList::LinkedList( ) : head(nullptr) {
   //Node* head = nullptr;
}

LinkedList::~LinkedList( ) { }
   
void LinkedList::addNode(Node* n) /*: n->setNext(head) ,head(n)*/ {
   n->setNext(head);
   head = n;
}

void LinkedList::print( ) {
   if (head != nullptr) {
      head->print( );
   }
}
