//
// THIS CODE IS EVIL and you should never do anything like this.
// It demonstrates what can go wrong when you use C-style casts
// Part 1 is the static cast

#include "Person.h"
#include "Student.h"
#include "Faculty.h"
#include <iostream>

int main() {
   Faculty* f = new Faculty(2,2,2,"Dumbledore");
   //Student* s = (Student*) f; // illegal! 
   Student* s = static_cast<Student*> (s); // illegal! no way to doe this without breaking everything looks like i can just make a cast of itself lol, not sure this is what you are looking for more of me being a smart ass
   f->putSalary(1500.0); // Statement S0
   std::cout << "before putGPA(6,6) called" << std::endl;
   s->putGPA(6.6); // Statement S1
   std::cout << "before getGPA called " << std::endl;
   s->getGPA( ); // Statement S2
   std::cout << std::endl;

   //f = (Faculty*) new Student(3,3,3,"Irena"); // illegal!
   f = static_cast<Faculty*> (new /*Student*/Person(3,3,3,"Irena")); // illegal! //will not allow the static cast of this will have to change it to person to compile 
   std::cout << "before putSalary called " << std::endl;
   f->putSalary(500.0); // Statement S3
   std::cout << "before getSalary called " << std::endl;
   f->getSalary( ); // Statement S4
   std::cout << std::endl;

   //s = (Student*) new Person(1,1,1,"Bob");
   s = static_cast<Student*> (new Person(1,1,1,"Bob"));     //this worked nicely
   std::cout << "before getGPA is called " << std::flush << std::endl;
   s->getGPA( ); // Statement S5
   std::cout << "finished" << std::flush << std::endl;
}
