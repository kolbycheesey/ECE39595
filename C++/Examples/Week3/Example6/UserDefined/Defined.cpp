#include "Defined.h"

Defined::Defined( ) {
   array = new int[5];
   for (int i = 0; i < 5; i++)
      array[i] = i;
}

Defined::Defined(Defined& s) {
   array = new int[5];
   for (int i = 0; i < 5; i++)
      array[i] = s.array[i];
}

