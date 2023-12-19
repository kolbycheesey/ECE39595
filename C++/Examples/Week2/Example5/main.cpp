#include <iostream>
#include "Base.h"
#include "Derived.h"

int main(int argc, char* argv[ ]) {
   Derived* d = new Derived( );
   Base* b = d;
   // b->m1( ); ERROR
   // d->m1( ); ERROR
   b->foo(b); // Base->m1
   b->foo(d); // Base->m1
   b->m1(d);
}
