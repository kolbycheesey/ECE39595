#include "ItemD.h"
#include <iostream>

ItemD::ItemD( ) { }

ItemD::~ItemD( ) { }

int ItemD::getNumberOfItems( ) {
   std::cout << "derived getNumberOfItems" << std::endl;
   return 0;
}
