#include "Automobile.h"

Automobile::Automobile(int number, float milesDrive, int month, int day, int year){
    vin = number;
    miles = milesDrive;
    lastMaintenance = ""+std::to_string(month)+"/"+std::to_string(day)+"/"+std::to_string(year);
    //for part ii
    //mm = month;
    //dd = day;
    //yyyy = year;

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
    return lastMaintenance;
}

//set
void Automobile::setVin(int number){
    vin = number;
}

void Automobile::setMiles(float milesDrive){
    miles = milesDrive;
}

void Automobile::setDate(int month, int day, int year){
    lastMaintenance = ""+std::to_string(month)+"/"+std::to_string(day)+"/"+std::to_string(year);
}