#include <iostream>
#include "Derived.h"

Derived::Derived( ) {
   std::cout << "Derived" << std::endl;
}

Derived::~Derived( ) {
   std::cout << "~Derived" << std::endl;
}
