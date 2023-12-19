#include "iostream"
#include "Intvec.h"

int main() {
   Intvec v1(20); 
   Intvec v2; 
   std::cout << "assigning lvalue...\n"; 
   v2 = v1; 
   std::cout << "ended assigning lvalue...\n";
} 
