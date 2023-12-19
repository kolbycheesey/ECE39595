#include <iostream>
#include "MallardDuck.h"
#include "Quack.h"
#include "FlyWithWings.h"
#include <memory>

MallardDuck::MallardDuck( ) {
   std::shared_ptr<QuackBehavior> quackBehavior = std::make_shared<QuackBehavior>(new Quack());
   //quackBehavior = new Quack( );
   std::shared_ptr<FlyBehavior> flyBehavior = std::make_shared<FlyBehavior>(new FlyWithWings());
   //flyBehavior = new FlyWithWings( );
}

void MallardDuck::display( ) {
   std::cout << "I’m a real mallard duck" << std::endl;
}
