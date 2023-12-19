#ifndef LINKEDLIST_H_
#define LINKEDLIST_H_
#include "Integer.h"
#include "LinkedList.h"
#include "Node.h"

class LinkedList {
private:
   Node* head;
   
public:
   LinkedList( );
   virtual ~LinkedList( );
   virtual void addNode(Node* n);
   virtual void print( );
};
#endif /* LINKEDLIST_H_ */
