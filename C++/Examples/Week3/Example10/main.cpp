#include "Item.h"
#include "ItemD.h"
#include <iostream>

int main (int argc, char *argv[]) { 

   Item *iP1 = new Item(4, 6.50);
   Item *iP2 = new Item(5, 7.50);
   Item *iP3 = new ItemD( );
   iP3->getNumberOfItems( );
   iP3->print( );

}
