#include "Node.h"

int main(int argc, char** args) {

   std::unique_ptr<Node> head(new Node(9));

   for (int i=8; i>=0; i--) {
      std::unique_ptr<Node> newNode(new Node(i));
      newNode->next = std::move(head);
      head = std::move(newNode);
   }

   head->print(0);
} // head is deleted upon exit from main
