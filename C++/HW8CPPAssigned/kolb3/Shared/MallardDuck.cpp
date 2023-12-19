#include <iostream>
#include "MallardDuck.h"
#include "Quack.h"
#include "FlyWithWings.h"
#include <memory>

MallardDuck::MallardDuck( ) {
   std::shared_ptr<QuackBehavior> quackBehavior = std::shared_ptr<QuackBehavior>(new Quack());
   //quackBehavior = new Quack( );
   std::shared_ptr<FlyBehavior> flyBehavior = std::shared_ptr<FlyBehavior>(new FlyWithWings());
   //flyBehavior = new FlyWithWings( );
}

void MallardDuck::display( ) {
   std::cout << "I’m a real mallard duck" << std::endl;
}
