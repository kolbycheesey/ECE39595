#include <iostream>
#include "Base.h"
#include "Derived.h"

int main(int argc, char* argv[ ]) {
   Derived* d = new Derived( );
   d->m1( ); // Derived->m1
   d->m2( ); // Derived->m2
   Base* b = d;
   b->m1( ); // Derived->m1
   // b->m2( ); error, remove the commenting and compile to see the error.
}
