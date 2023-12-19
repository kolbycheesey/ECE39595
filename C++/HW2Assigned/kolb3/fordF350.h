#ifndef FORDF350_H_
#define FORDF350_H_
#include "Vehicle.h"
#include <string>

class fordF350 : public Vehicle {
    private:
        std::string color;
        std::string model;
        std::string engine;
        std::string transmission;
        int numPassengers;
        std::string warranty;

    public:
        fordF350(std::string _color);
        std::string getModel( );
        std::string getColor( );
        std::string getEngine( );
        std::string getTransmission( );
        int getNumPassengers( );
        std::string getWarranty( ); 
};
#endif /* FORDF350_H_ */
