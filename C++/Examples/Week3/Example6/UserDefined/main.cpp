#include <iostream>
#include "Defined.h"

void foo(Defined d) {
   d.array[0] = 100;
   std::cout << "d.array[0]: " << d.array[0] << std::endl;
}

int main(int argc, char* argv[ ]) {
   Defined d;
   std::cout << "d.array[0]: " << d.array[0] << std::endl;
   foo(d);
   std::cout << "d.array[0]: " << d.array[0] << std::endl;
}



