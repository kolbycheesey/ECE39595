#include "iostream"
#include "Box.h"

int main() {
   Box b1(1); 
   Box b2(2); 
   std::cout << "b1.data: " << b1.data << ", b2.data: " << b2.data << std::endl;
   b1 = std::move(b2); 
   std::cout << "b1.data: " << b1.data << ", b2.data: " << b2.data << std::endl;
} 
