#include <iostream>
#include "Base.h"

void Base::m1( ) {
   std::cout << "Base.m1" << std::endl;
}

void Base::foo(Base* b) {
   b->m1( );
}
