#include <iostream>
#include "DecoyDuck.h"
#include "FlyNoWay.h"
#include "MuteQuack.h"
#include <memory>

DecoyDuck::DecoyDuck( ) {
   std::shared_ptr<FlyBehavior> flyBehavior = std::shared_ptr<FlyBehavior> (new FlyNoWay()); //new FlyNoWay( );
   std::shared_ptr<QuackBehavior> quackBehavior = std::shared_ptr<QuackBehavior>(new MuteQuack()); //new MuteQuack( );
}

void DecoyDuck::display( ) {
   std::cout << "I’m a decoy duck" << std::endl;
}
