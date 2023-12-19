#include <iostream>
#include "Base.h"
#include "Derived.h"

int main(int argc, char* argv[ ]) {
   Derived* d = new Derived( );
   Base* b = d;
   // b->m1( ); S1 ERROR
   d->m1( ); // S2 Derived m1( )
   b->foo(b); // S3 Base foo and m1
   b->foo(d); // S4 Base foo and m1
}
