#include <iostream>
#include "Node.h"

Node::Node(int v) : value(v) { }

Node::~Node( ) {
   std::cout << "deleting node with value: " << value << std::endl;
}

void Node::print(int level) {
   std::cout << value << " ";
   if (next) next->print(level+1);
   if (level == 0) std::cout << std::endl;
}
