#ifndef DERIVED_H_
#define DERIVED_H_
#include "Base.h"

class Derived : public Base {
public:
   Derived( );
   virtual ~Derived( );

   virtual void f(char);
   using Base::f;  

};
#endif /*DERIVED_H_*/
