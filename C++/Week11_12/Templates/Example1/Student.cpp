#include <iostream>
#include <string>
#include "Student.h"

Student::Student(std::string name): s_name(name) { }

Student::Student(const Student& orig): s_name(orig.s_name){ }

Student::Student( ) {s_name = "";}

bool Student::operator< (const Student& arg2) {
   return (s_name < arg2.s_name);
}

bool Student::operator==(const Student& arg2) {
   if (s_name == arg2.s_name) {return true;}
   else {return false;}
}

std::ostream& operator<< (std::ostream& os, const Student& stu) {
   os << stu.s_name << std::endl;
   return os;
}

