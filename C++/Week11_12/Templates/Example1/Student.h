#ifndef STUDENT_H_
#define STUDENT_H_

#include <iostream>
#include <string>
class Student {
private:
   std::string s_name;
public:
   Student(std::string name);
   Student(const Student& orig);
   Student( );
   virtual bool operator< (const Student& arg2);
   virtual bool operator==(const Student& arg2);
   friend std::ostream& operator<< (std::ostream& os, const Student& stu);
};
#endif /* STUDENT_H_ */
