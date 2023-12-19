#include <iostream>
#include "Base.h"
#include "Derived.h"

int main(int argc, char* argv[ ]) {
   Derived* d = new Derived( );
   Base* b = d;
   std::cout << "b->iv: "+b->iv << std::endl;
   std::cout << "b->iv: "+b->iv << std::endl;
}

