#ifndef DERIVED_H_
#define DERIVED_H_
#include "Base.h"

class Derived : public Base {

public:
   void m1( );
   virtual void m2( );
};

#endif /* DERIVED_H_ */
