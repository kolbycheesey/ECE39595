#include <iostream>
#include <cstdlib>
#include "TwoTuple.h"

//this file is not needed
//zero arg constructor
/*
template <typename TPL1, typename TPL2>
TwoTuple<TPL1, TPL2>::TwoTuple( ) { }

//normal constructor
template <typename TPL1, typename TPL2>TwoTuple<TPL1, TPL2>::TwoTuple( TPL1 ci1, TPL2 ci2 ) : i1(ci1), i2(ci2)  { 

}

std::ostream& operator<<(std::ostream& os, const TwoTuple& tuple) {
         os << "[" << tuple.i1 << ", " << tuple.i2 << "]";
         return os;
}

//equal to
template <typename TPL1, typename TPL2>
std::ostream& operator==(std::ostream& os, const TwoTuple<TPL1, TPL2>& data) {
    //compare if = return true
    if(this.i1 != data.i1) {
        return false;
    }
    if(this.i2 != data.i2){
        return false;
    }
    return true;

}

//less than
template <typename TPL1, typename TPL2>
std::ostream& operator<(std::ostream& os, const TwoTuple<TPL1, TPL2>& data) {
    //compare if < return true
    if(this.i1 < data.i1) {
        return true;
    }
    if(this.i2 != data.i2){
        return true;
    }
    return false;

}*/

//greater than
template <typename TPL1, typename TPL2>
std::ostream& operator>(std::ostream& os, const TwoTuple<TPL1,TPL2>& data) {
    //compare if > return true
    if(this.i1 > data.i1) {
        return true;
    }
    if(this.i2 > data.i2){
        return true;
    }
    return false;

}