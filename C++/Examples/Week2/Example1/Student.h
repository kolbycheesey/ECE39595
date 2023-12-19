#ifndef STUDENT_H_
#define STUDENT_H_

#include <string>
#include "Person.h"

class Student : public Person {

private:
    float gpa;

public:
   Student(float _gpa);
   float getGPA( );
};

#endif /* STUDENT_H_ */
