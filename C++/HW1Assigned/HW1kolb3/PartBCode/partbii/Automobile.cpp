#include "Automobile.h"

Automobile::Automobile(int number, float milesDrive, int month, int day, int year){
    vin = number;
    miles = milesDrive;
    std::string lastMaintenance; // = ""+std::to_string(month)+"/"+std::to_string(day)+"/"+std::to_string(year);
    //for part ii
    mm = month;
    dd = day;
    yyyy = year;

}

//other functions
//get
int Automobile::getVin(){
    return vin;
}

float Automobile::getMiles(){
    return miles;
}

std::string Automobile::getDate(){
    lastMaintenance = ""+std::to_string(mm)+"/"+std::to_string(dd)+"/"+std::to_string(yyyy);
    return lastMaintenance;
}
//set
void Automobile::setVin(int number){
    vin = number;
}

void Automobile::setMiles(float milesDrive){
    miles = milesDrive;
}

void Automobile::setDate(int mm, int dd, int yyyy){
    lastMaintenance = ""+std::to_string(mm)+"/"+std::to_string(dd)+"/"+std::to_string(yyyy);
}