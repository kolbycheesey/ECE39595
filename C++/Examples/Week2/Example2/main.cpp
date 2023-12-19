#include <iostream>
#include "Student.h"

int main(int argc, char* argv[ ]) {
   Student* s = new Student(2.9f);
   if (s->getName( ) == "") {
      std::cout << "name is empty string" << std::endl;
   } else {
      std::cout << s->getName( ) << std::endl;
   }
}

