#include "Default.h"

Default::Default( ) {
   array = new int[5];
   for (int i = 0; i < 5; i++)
      array[i] = i;
}
