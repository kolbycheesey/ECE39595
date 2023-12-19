#include "iostream"
#include "Intvec.h"

int main() {
   Intvec v2;
   std::cout << "assigning rvalue...\n"; 
   v2 = Intvec(33); 
   std::cout << "ended assigning rvalue...\n"; 
} 
