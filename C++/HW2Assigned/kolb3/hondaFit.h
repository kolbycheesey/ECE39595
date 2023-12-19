#ifndef HONDAFIT_H_
#define HONDAFIT_H_
#include "Vehicle.h"
#include <string>

class hondaFit : public Vehicle {
    private:
        std::string color;
        std::string model;
        std::string engine;
        std::string transmission;
        int numPassengers;
        std::string warranty;

    public:
        hondaFit(std::string _color);
        std::string getModel( );
        std::string getColor( );
        std::string getEngine( );
        std::string getTransmission( );
        int getNumPassengers( );
        std::string getWarranty( );    
};
#endif /* HONDAFIT_H_ */