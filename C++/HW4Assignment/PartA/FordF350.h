#ifndef FORDF350_H_
#define FORDF350_H_

#include <string>
#include "Vehicle.h"

class FordF350 : public Vehicle {
private:
   std::string color;
public:
   FordF350(std::string _color);
   std::string getModel( );
   std::string getColor( );
   std::string getEngine( );
   std::string getTransmission( );
   int getNumPassengers( );
   std::string getWarranty( );
};
#endif /* FORDF350_H_ */
