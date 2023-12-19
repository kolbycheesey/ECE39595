#ifndef USER_H_
#define USER_H_
#include <iostream>

class User {
   int u_age;
public:
   User(int age);
   User( );

   virtual bool operator< (const User& arg2);
   virtual bool operator== (const User& arg2);

   friend std::ostream& operator<< (std::ostream& os, const User& user);
};
#endif /* USER_H_ */
