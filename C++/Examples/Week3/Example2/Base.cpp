#include <iostream>

#include "Base.h"

Base::Base(int u, int v, int w) : d(8), c(b), b(4), a(d) { }

Base::~Base( ) { }

void Base::print( ) {
   std::cout << "a: " << a << ", b: " << b << ", c: " << c << ", d: " << d << std::endl;
}
