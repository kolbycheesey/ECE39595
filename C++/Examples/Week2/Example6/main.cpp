#include <iostream>
#include "Base.h"
#include "Derived.h"

int main(int argc, char* argv[ ]) {
   Base* d = new Derived( );
   Base* b = d;
   b->m1( ); // Derived->m1
}
