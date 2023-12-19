#include <iostream>

#include "Person.h"
#include "Student.h"

int main(int argc, char* argv[ ]) {
   Person bob("Bob");
   Student jane(3.9, "Jane");
   Student& janeR = jane;
   Person& janePR = jane;
   janeR.setRole( );
   janePR.setRole( );
}
