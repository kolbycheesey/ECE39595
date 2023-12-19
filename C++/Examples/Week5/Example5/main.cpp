#include <iostream>

#include "Person.h"
#include "Student.h"

int main(int argc, char* argv[ ]) {
   Person bob ("Bob");
   Person& bobR = bob;
   Student jane(3.9, "Jane");
   Student& janeR = jane;
   std::cout << "bob name: " << bob.name << std::endl;
   bobR = janeR; // OK
   std::cout << "bob name: " << bob.name << std::endl;
   // ERROR jane = bob; 
   bobR.name = "Bob";
   Person& janePersonR = jane;
   std::cout << "jane gpa: " << jane.gpa << ", jane name: " << jane.name << std::endl;
   janePersonR = bobR;
   std::cout << "jane gpa: " << jane.gpa << ", jane name: " << jane.name << std::endl;
}
