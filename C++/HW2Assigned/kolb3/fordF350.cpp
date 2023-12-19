#include <iostream>
#include <string>
#include "fordF350.h"
#include "Vehicle.h"

fordF350::fordF350(std::string _color) : Vehicle("Ford F350"){
    color = _color;
    std::cout << "built\n";
    model = "Ford F350";
    engine = "7.3L V8";
    transmission = "10 speed automatic";
    numPassengers = 3;
    warranty = "3/36000 + 5/60000 Powertrain";
}

std::string fordF350::getModel( ){
    return model;
}

std::string fordF350::getColor( ){
    return color;
}

std::string fordF350::getEngine( ){
    return engine;
}

std::string fordF350::getTransmission( ){
    return transmission;
}

int fordF350::getNumPassengers( ) {
    return numPassengers;
}

std::string fordF350::getWarranty( ){
    return warranty;
}