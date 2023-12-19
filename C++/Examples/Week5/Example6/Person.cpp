#include <iostream>
#include <string>
#include "Person.h"

Person::Person(std::string _name) {
   name = _name;
}

void Person::setRole( ) {
	role = "unclassified";
	std::cout << "Person::setRole executed" << std::endl;
}

std::string Person::getRole( ) {\
	return role;
}
