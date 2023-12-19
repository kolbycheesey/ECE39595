#include <iostream>
#include "Duck.h"
#include <memory>

Duck::Duck( ) { }

void Duck::swim( ) {
   std::cout << "All ducks float" << std::endl;
}

void Duck::performQuack ( ) {
   quackBehavior->quack( );
}

void Duck::performFly( ) {
   flyBehavior->fly( );
}

void Duck::setFlyBehavior(FlyBehavior* fb) {
   std::shared_ptr<FlyBehavior> flyBehavior = std::make_shared<FlyBehavior>(fb);//fb;
}
   
void Duck::setQuackBehavior(QuackBehavior* qb) {
   std::shared_ptr<QuackBehavior> quackBehavior = std::make_shared<QuackBehavior>(qb);  //qb;
}
