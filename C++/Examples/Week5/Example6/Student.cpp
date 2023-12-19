#include <iostream>

#include "Student.h"

Student::Student(int _gpa, std::string name) : Person(name) {
   gpa = _gpa; 
}

void Student::setRole( ) {
   role = "student";
   std::cout << "Student::setRole executed" << std::endl;
}
