#ifndef PERSON_H_
#define PERSON_H_

#include <string>
class Person {
public:
   std::string name;
   Person(std::string);
   virtual void setRole( );
   virtual std::string getRole( );
protected:
   std::string role;
};
#endif /* PERSON_H_ */
