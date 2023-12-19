#include <iostream>
#include "Obj.h"

void foo1(Obj objVar) {
   std::cout << "foo1" << std::endl;
   objVar.i = 1;
}

void foo2(Obj& objRef) {
      objRef.i = 2;
}

void foo3(Obj* objPtr) {
      std::cout << "foo2 ptr" << std::endl;
}

int main(int argc, char* argv[ ]) { 
   Obj oVar(-1);
   Obj&  oRef = oVar;
   Obj* oPtr = &oVar;
   foo1(oVar);
   std::cout << oVar.i << std::endl;
   foo1(oRef);
   std::cout << oVar.i << std::endl;
   std::cout << oVar.i << std::endl;
   foo2(oVar);
   std::cout << oVar.i << std::endl;
   foo2(oRef);
   foo3(oPtr);
}   
   
