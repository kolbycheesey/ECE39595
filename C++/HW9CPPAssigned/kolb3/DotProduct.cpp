#include <iostream>
#include <string>
#include <stdlib.h>

#include "DotProduct.h"
#include <mutex>
std::mutex identLock;

DotProduct::DotProduct(int numElements) : length(numElements) {
    arra = new double[length];
    arrb = new double[length];
    //long ans = 0;
    //srand(0);
    for(int i = 0; i < length; i++){
        arra[i] = rand( );// % 100;
        arrb[i] = rand( );// % 100;
        ans = 0.;
    }

}

DotProduct::DotProduct( ) { 
    //DotProduct(0);
}

void DotProduct::math( ) { //think i need this to allow an array to be passed to it
    dotProduct( );
}
//formally dotProduct
void DotProduct::dotProduct( ) { //think this is good need to ask about this and *
    //intialize an array arr ans
    //int ans = 0;      //since this should be intialized in the constructor should be good to comment out
    
    for(int i = 0; i < length; i++){
        ans += arra[i] * arrb[i];
        //std::cout << "ans: " << ans << "\n";
        
    }

}

//will need to change this
std::ostream& operator<<(std::ostream& os, const DotProduct& dp) {
   //int stride = 1;
   /*
   if (qs.length > 32) {
      stride = qs.length / 32;
   }*/
   //ans is a problem will need to find a way to get that in
   os << "inner product on arrays of length " << dp.length << ", result is " << dp.ans;
   /*
   for (int i=0; i<qs.length; i+=stride) {
      os << qs.arr[i] << " "; 
   }*/
   os << "\n";
   return os;
}

void DotProduct::identify() {
    std::lock_guard<std::mutex> lck(identLock);
    std::cout << *this << std::endl;
}

void DotProduct::execute( ) { //think this is good need to ask about this and *
    //intialize an array arr ans
    //int ans = 0;      //since this should be intialized in the constructor should be good to comment out

    for(int i = 0; i < length; i++){
        ans += arra[i] * arrb[i];
        //std::cout << "ans: " << ans << "\n";
        
    }

}