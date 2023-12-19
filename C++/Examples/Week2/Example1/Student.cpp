#include <string>
#include "Student.h"

Student::Student(float _gpa) : Person( ) {
   gpa = _gpa;
}

float Student::getGPA( ) {
   return gpa;
}
