#include <iostream>
#include "BigObject.h"

void foo(const BigObject& bObj, int i) {
   // ILLEGAL bObj.array[i] = i;
}

int main(int argc, char* argv[ ]) { 
   BigObject b;
   BigObject& bRef = b; // bind b to bRef
   std::cout << "after foo b 3: " << b.array[3] << std::endl;
   foo(b, 3);
   std::cout << "before foo b 3: " << b.array[3] << std::endl;
}   

