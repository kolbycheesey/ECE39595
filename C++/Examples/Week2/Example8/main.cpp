#include <iostream>
#include "Base.h"
#include "Derived1.h"
#include "Derived2.h"

int main(int argc, char* argv[ ]) {
   Derived2* d2 = new Derived2( );
   Derived1* d1 = d2;
   Base* b = d2;
   b->m1( ); // S1
   d1->m1( ); // S2
}
