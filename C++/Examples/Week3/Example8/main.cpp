#include "Base.h"
#include "Derived.h"

int main() {
   Derived* d = new Derived();
   Base* b = d;
   b->f(65.3); 
   b = d;
   b->f(65.3); 
   d->f(65.3); 
}
