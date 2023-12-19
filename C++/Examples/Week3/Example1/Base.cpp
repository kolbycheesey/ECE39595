#include <iostream>

#include "Base.h"

Base::Base(int u, int v, int w) : d(u), c(v), b(w), a(-1) { }

Base::~Base( ) { }

void Base::print( ) {
   std::cout << "a: " << a << ", b: " << b << ", c: " << c << ", d: " << d << std::endl;
}
