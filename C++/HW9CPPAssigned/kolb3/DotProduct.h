#include <iostream>
#include <string>
#include "Command.h"

class DotProduct : public Command {
    private:
        //int length;
        double* arra;
        double* arrb;
        int length;
        double ans;

        void dotProduct( );      //think I only need arr b to be passed to it



    public:
        DotProduct( );
        DotProduct(int numElements);
        virtual void math( );

        void execute ( ); //{ std::cout << "execute() called"; }
        void identify ( ); //{ std::cout << "identify() called"; }

        friend std::ostream& operator<<(std::ostream& os, const DotProduct& dp);
};