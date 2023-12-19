#include <string>
#include "Student.h"

Student::Student(float _gpa) {
   gpa = _gpa;
}

float Student::getGPA( ) {
   return gpa;
}
