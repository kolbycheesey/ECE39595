#include <iostream>
#include "RedHeadDuck.h"
#include "Quack.h"
#include "FlyWithWings.h"
#include <memory>

RedHeadDuck::RedHeadDuck( ) {
   std::shared_ptr<QuackBehavior> quackBehavior = std::make_shared<QuackBehavior>(new Quack());
   std::shared_ptr<FlyBehavior> flyBehavior = std::make_shared<FlyBehavior>(new FlyWithWings());
   //quackBehavior = new Quack( );
   //flyBehavior = new FlyWithWings( );
}

void RedHeadDuck::display( ) {
   std::cout << "I’m a real redheaded duck" << std::endl;
}
