#include "Base.h"
#include "Derived.h"

int main(int argc, char* argv[ ]) {
   Base* base = new Derived( );
   delete base;
}
