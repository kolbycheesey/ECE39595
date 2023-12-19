#include <iostream>
#include <string>
#include "Student.h"

Student::Student(float _gpa, std::string name) : Person(name) {
   gpa = _gpa;
   std::cout << "Student(float, std::string)" << std::endl;
}

float Student::getGPA( ) {
   return gpa;
}
