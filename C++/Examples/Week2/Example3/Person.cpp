#include <iostream>
#include <string>

#include "Person.h"

Person::Person(std::string _name) {
   name = _name;
   std::cout << "Person(std::string)" << std::endl;
}

std::string Person::getName( ) {
   return name;
}
   
