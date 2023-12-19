#include "Address.h"
#include "Person.h"
#include "Employee.h"

Employee::Employee(std::string* _name, int _birthMonth, int _birthDay, int _birthYear, Address* _address, double _salary) : salary(_salary), Person(_name, _birthMonth, _birthDay, _birthYear, _address) {
   //getSalary();
   //salary = _salary; 
}

std::string Employee::getRole( ) { 
   return "Employee";
}

/*int getZipCode() {  
   return Address.getZipCode();   
}

std::string Person::getDate() { 
   return *dateOfBirth;  
}

std::string Address::getAddress(){
   return getAddress( );
}*/

double Employee::getSalary( ) { 
   return salary;
}
