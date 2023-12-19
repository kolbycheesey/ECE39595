#include <iostream>
#include <string>
#include "hondaFit.h"
#include "Vehicle.h"

hondaFit::hondaFit(std::string _color) : Vehicle("Honda Fit"){
    color = _color;
    std::cout << "built\n";
    model = "Honda Fit";
    engine = "1.5L i-DSI 4 Cylinder";
    transmission = "manual";
    numPassengers = 5;
    warranty = "3/36000 + 5/60000 Powertrain";


    //Vehicle("Honda Fit")    //superclass constructor?
}

std::string hondaFit::getModel( ){
    return model;
}

std::string hondaFit::getColor( ){
    return color;
}

std::string hondaFit::getEngine( ){
    return engine;
}

std::string hondaFit::getTransmission( ){
    return transmission;
}

int hondaFit::getNumPassengers( ) {
    return numPassengers;
}

std::string hondaFit::getWarranty( ){
    return warranty;
}