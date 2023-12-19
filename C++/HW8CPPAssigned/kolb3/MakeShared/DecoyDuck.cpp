#include <iostream>
#include "DecoyDuck.h"
#include "FlyNoWay.h"
#include "MuteQuack.h"
#include <memory>

DecoyDuck::DecoyDuck( ) {
   std::shared_ptr<FlyBehavior> flyBehavior = std::make_shared<FlyBehavior> (new FlyNoWay()); //new FlyNoWay( );
   std::shared_ptr<QuackBehavior> quackBehavior = std::make_shared<QuackBehavior>(new MuteQuack()); //new MuteQuack( );
}

void DecoyDuck::display( ) {
   std::cout << "I’m a decoy duck" << std::endl;
}
