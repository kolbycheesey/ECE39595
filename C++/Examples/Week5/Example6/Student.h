#ifndef STUDENT_H_
#define STUDENT_H_

#include <string>
#include "Person.h"

class Student : public Person {
public:
   float gpa;
   Student(int _gpa, std::string name);
   void setRole( );
};
#endif /* STUDENT_H_ */
