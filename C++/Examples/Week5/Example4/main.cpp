#include <iostream>
#include "Obj.h"
int main(int argc, char* argv[ ]) { 
   Obj* p1 = new Obj(1);
   Obj* p2 = new Obj(2);
   p1 = p2	;
   p1->i = 500;
   std::cout << p2->i << std::endl; // 500 printed
}   

