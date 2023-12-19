#ifndef TWOTUPLE_H_
#define TWOTUPLE_H_
#include <iostream>
#include "Int.h"

template <typename TPL1, typename TPL2>
class TwoTuple {
   private:
      TPL1 i1;
      TPL2 i2;

   public:
      TwoTuple(); //zero arg
      TwoTuple(TPL1 ci1, TPL2 ci2);
      //TwoTuple(); //non-zero arg
      friend bool operator<(TwoTuple<TPL1, TPL2>& data1, TwoTuple<TPL1, TPL2>& data2) {
         //compare if < return true
         if(data1.i1 < data2.i1) {
            return true;
         }
         if(data1.i2 < data2.i2){
            return true;
         }
         return false;
      }
      friend bool operator>(TwoTuple<TPL1, TPL2>& data1, TwoTuple<TPL1, TPL2>& data2) {
         //compare if > return true
         if(data1.i1 > data2.i1) {
            return true;
         }
         if(data1.i2 > data2.i2){
            return true;
         }
         return false;
      }
      friend bool operator==(TwoTuple<TPL1, TPL2>& data1, TwoTuple<TPL1, TPL2>& data2) {
         //compare if = return true
         if(data1.i1 != data2.i1) {
            return false;
         }
         if(data1.i2 != data2.i2){
            return false;
         }
         return true;
      }
      friend std::ostream& operator<<(std::ostream& os, const TwoTuple<TPL1, TPL2>& tuple) {
         os << "[" << tuple.i1 << ", " << tuple.i2 << "]";
         return os;
      }
};

//zero arg constructor
template <typename TPL1, typename TPL2>
TwoTuple<TPL1, TPL2>::TwoTuple( ) { }

//normal constructor
template <typename TPL1, typename TPL2>TwoTuple<TPL1, TPL2>::TwoTuple( TPL1 ci1, TPL2 ci2 ) : i1(ci1), i2(ci2)  { 

}
//friend functions must be declared in the header function
/*template <typename TPL1, typename TPL2>
std::ostream& operator<<(std::ostream& os, const TwoTuple<TPL1, TPL2>& tuple) {
         os << "[" << tuple.i1 << ", " << tuple.i2 << "]";
         return os;
}

//equal to
template <typename TPL1, typename TPL2>
bool operator==(TwoTuple<TPL1, TPL2>& data1, TwoTuple<TPL1, TPL2>& data2) {
   //compare if = return true
   if(data1.i1 != data2.i1) {
      return false;
   }
   if(data1.i2 != data2.i2){
      return false;
   }
   return true;

}

//less than
template <typename TPL1, typename TPL2>
bool operator<(TwoTuple<TPL1, TPL2>& data1, TwoTuple<TPL1, TPL2>& data2) {
   //compare if < return true
   if(data1.i1 < data2.i1) {
      return true;
   }
   if(data1.i2 < data2.i2){
      return true;
   }
   return false;

}

//greater than
template <typename TPL1, typename TPL2>
bool operator>(TwoTuple<TPL1, TPL2>& data1, TwoTuple<TPL1, TPL2>& data2) {
   //compare if > return true
   if(data1.i1 > data2.i1) {
      return true;
   }
   if(data1.i2 > data2.i2){
      return true;
   }
   return false;

}*/

#endif /* TWOTUPLE_H_ */
