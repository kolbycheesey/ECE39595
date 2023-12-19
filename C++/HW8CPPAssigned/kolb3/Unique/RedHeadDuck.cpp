#include <iostream>
#include "RedHeadDuck.h"
#include "Quack.h"
#include "FlyWithWings.h"
#include <memory>

RedHeadDuck::RedHeadDuck( ) {
   quackBehavior = std::unique_ptr<QuackBehavior>(new Quack());
   flyBehavior = std::unique_ptr<FlyBehavior>(new FlyWithWings());
   //quackBehavior = new Quack( );
   //flyBehavior = new FlyWithWings( );
}

void RedHeadDuck::display( ) {
   std::cout << "I’m a real redheaded duck" << std::endl;
}
