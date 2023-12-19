#include <iostream>
#include "User.h"

User::User(int age): u_age(age) { };
User::User( ): u_age(0) { }
bool User::operator< (const User& arg2) {
   return (u_age < arg2.u_age);
}
bool User::operator== (const User& arg2) {
   return (u_age == arg2.u_age);
}

std::ostream& operator<< (std::ostream& os, const User& usr) {
   os << usr.u_age << std::endl;
   return os;
} 

