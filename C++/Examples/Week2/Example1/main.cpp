#include <iostream>
#include "Student.h"

int main(int argc, char* argv[ ]) {
   Student* s = new Student(2.9f);
   std::cout << s->getName( ) << std::endl;
   std::cout << s->getGPA( ) << std::endl;
}

