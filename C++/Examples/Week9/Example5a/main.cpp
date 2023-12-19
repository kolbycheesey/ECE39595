#include <memory>
#include <iostream>
#include "Node.h"

std::shared_ptr<Node>* keep5Alive = new std::shared_ptr<Node>; 

int main(int argc, char** args) {

   std::shared_ptr<Node> head = std::make_shared<Node>(9);

   for (int i=8; i>=0; i--) {
      std::shared_ptr<Node> newNode = std::make_shared<Node>(i);
      newNode->next = head;
      if (i==5) *keep5Alive = newNode;
      std::cout << "5Alive count: " << keep5Alive->use_count( ) << std::endl;
      head = newNode;
   }

   head->print(0);
} // head is deleted upon exit from main
