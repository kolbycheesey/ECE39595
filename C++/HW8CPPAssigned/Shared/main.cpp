#include "Duck.h"
#include "RedHeadDuck.h"
#include "MallardDuck.h"
#include "DecoyDuck.h"
#include "FlyNoWay.h"
#include <memory>

int main(int argc, char *argv[]) {
   std::shared_ptr<Duck> red = std::shared_ptr<Duck>(new RedHeadDuck( ));
   std::shared_ptr<Duck> mallard = std::shared_ptr<Duck>(new MallardDuck( ));
   std::shared_ptr<Duck> decoy = std::shared_ptr<Duck>(new DecoyDuck( ));

   red->performQuack( );
   red->performFly( );
   red->swim( );
   //red->setFlyBehavior(std::unique_ptr<FlyBehavior>(new FlyNoWay()));
   red->setFlyBehavior(new FlyNoWay( ));
   red->performFly( );

   decoy->performFly( );
}
