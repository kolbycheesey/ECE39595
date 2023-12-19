#include <iostream>
#include "Box.h"

Box::Box(int val) : data(val) { } 

void Box::xchange(Box&& other) {
   std::swap(data, other.data);
}
