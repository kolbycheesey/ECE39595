#include <iostream>
#include "Matrix.h"

Matrix::Matrix(int d1, int d2) : dim1Extent(d1), dim2Extent(d2) {
   data = new double[d1*d2];
   //prints the location
   //std::cout << data << std::endl;
}

//to get to data[x][y] it has to be &data + d1*x + y moves us number of rows
//then moves number of cols have to look at the pointer to move data points basically
double& Matrix::operator()(int r, int c) const {
   //const function
   //need the mem location of data[0]
   return (data[r*dim1Extent + c]);
}

double& Matrix::operator()(int r, int c) {
   //non const function
   return (data[r*dim1Extent + c]);
}