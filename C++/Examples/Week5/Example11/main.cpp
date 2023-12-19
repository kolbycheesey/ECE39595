#include <iostream>
#include "Obj.h"

void foo1(Obj objVar) {
   std::cout << "foo1" << std::endl;
}

void foo2(Obj& objRef) {
      std::cout << "foo2 ref" << std::endl;
}

void foo3(Obj* objPtr) {
      std::cout << "foo2 ptr" << std::endl;
}

int main(int argc, char* argv[ ]) { 
   Obj oVar(1);
   Obj&  oRef = oVar;
   Obj* oPtr = &oVar;
   foo1(oVar);
   foo1(oRef);
   // ERROR foo1(oPtr);  
   foo2(oVar);
   foo2(oRef);
   // ERROR foo2(oPtr); 
   // ERROR foo3(oVar);
   // ERROR foo3(oRef);
   foo3(oPtr);
}   
   
