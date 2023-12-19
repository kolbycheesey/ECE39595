#include <iostream>
#include "DecoyDuck.h"
#include "FlyNoWay.h"
#include "MuteQuack.h"
#include <memory>

DecoyDuck::DecoyDuck( ) {
   flyBehavior = std::unique_ptr<FlyNoWay>(new FlyNoWay()); //new FlyNoWay( );
   quackBehavior = std::unique_ptr<QuackBehavior>(new MuteQuack()); //new MuteQuack( );
}

void DecoyDuck::display( ) {
   std::cout << "I’m a decoy duck" << std::endl;
}
