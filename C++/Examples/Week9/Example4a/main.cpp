#include "Node.h"

int main(int argc, char** args) {

   std::shared_ptr<Node> head = std::make_shared<Node>(9);

   for (int i=8; i>=0; i--) {
      std::shared_ptr<Node> newNode = std::make_shared<Node>(i);
      newNode->next = head;
      head = newNode;
   }

   head->print(0);
} // head is deleted upon exit from main
