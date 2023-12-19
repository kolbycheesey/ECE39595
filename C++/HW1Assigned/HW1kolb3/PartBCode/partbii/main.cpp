#include <iostream>
#include "Automobile.h"

int main(){
    Automobile* automobile1 = new Automobile(123456789, 150689.7, 10,31, 2017);
    std::cout << "Miles drive: " +std::to_string(automobile1->getMiles()) <<std::endl;
    std::cout << "Date of last Maintenance: " +automobile1->getDate() << std::endl;
    std::cout << "VIN: " +std::to_string(automobile1->getVin()) << std::endl;

    Automobile* automobile2 = new Automobile(987654321, 534126.2, 12, 15, 2020);
    std::cout << "Miles drive: " +std::to_string(automobile2->getMiles()) <<std::endl;
    std::cout << "Date of last Maintenance: " +automobile2->getDate() << std::endl;
    std::cout << "VIN: " +std::to_string(automobile2->getVin()) << std::endl;
}