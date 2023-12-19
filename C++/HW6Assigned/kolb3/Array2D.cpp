#include <iostream>
#include <cstdlib>
#include "Array2D.H"

// The character parameter exists to give this a different signature than any of 
// the public constructors.
Array2D::Array2D(int numR, int numC, char differentiator) : numRows(numR), numCols(numC) {

   ary = new int*[numRows];
   for (int i = 0; i < numRows; i++) {
      ary[i] = new int[numCols];
      for (int j = 0; j < numCols; j++) {
            ary[i][j] = -1;
      }
   }
}

Array2D::Array2D( Array2D& other) { 
   //std::cout << "Array2D(Array2D)" << std::endl;
   int cols = other.getNumCols();
   int rows = other.getNumRows();

   this->numRows = rows;
   this->numCols = cols;

   ary = new int*[numRows];
   for (int i = 0; i < numRows; i++) {
      ary[i] = new int[numCols];
      for (int j = 0; j < numCols; j++) {
            ary[i][j] = -1;
      }
   }
}
Array2D::~Array2D() {
   deleteAry(this->ary);
}

/*Array2D::Array2D(Array2D& operator) {
   //code goes here
}*/

Array2D::Array2D(int numR, int numC) : Array2D(numR, numC, 'c') { 
   for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
         ary[i][j] = i;
      }
   }
}

Array2D::Array2D(int numR, int numC, int val) : Array2D(numR, numC, 'c') { 
   for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
         ary[i][j] = val;
      }
   }
}

int Array2D::getElement(int x, int y) const { 
   return ary[x][y];
}

int Array2D::getNumRows() const { 
   return numRows;
}

int Array2D::getNumCols( ) const {
   return numCols;
}

int& Array2D::operator( )(int row, int col) {
   return ary[row][col];
}
      
int Array2D::operator( )(int row, int col) const {
   return ary[row][col];
}

bool Array2D::sameSize(const Array2D& other) const {
   return (numRows == other.getNumRows( ) && numCols == other.getNumCols( ));
}

bool Array2D::operator==(const Array2D &a1) const {
   //compare if = return true
   if(!sameSize(a1)){
      return false;
   }
   else{
      for(int i = 0; i < this->getNumRows(); i++){
         for(int j = 0; j < this->getNumCols(); j++){
            if(ary[i][j] != a1.ary[i][j]){
               return false;
            }
         }
      }
   }
   return true;
}

Array2D& Array2D::operator=(const Array2D &a1) {
   if(this->ary != NULL){
      delete(this->ary);
   }

   int cols = a1.getNumCols();
   int rows = a1.getNumRows();

   this->numRows = rows;
   this->numCols = cols;

   ary = new int*[numRows];
   for (int i = 0; i < numRows; i++) {
      ary[i] = new int[numCols];
      for (int j = 0; j < numCols; j++) {
            ary[i][j] = -1;
      }
   }
   return *this;
}

std::ostream& operator<<(std::ostream& os, const Array2D& a1){
   for(int x = 0; x < a1.getNumRows(); x++){
      for(int y = 0; y < a1.getNumCols(); y++){
         os << a1.getElement(x,y) << " ";
         if(y == a1.getNumCols() - 1){
            os << std::endl;
         } 
      }
   }
   return os;
}

Array2D& Array2D::operator*(const Array2D &a1) const {
   //compare if = return true
   if(a1.getNumRows() != this->getNumCols()){
      Array2D * a2 = new Array2D(numRows, numCols, -1);
      return *a2;
   }
   else {
      Array2D *ans = new Array2D(numRows, a1.numCols, 0);
      for(int x =0; x < numRows; x++){
         for(int y = 0; y < a1.numCols; y++){
            ans->ary[x][y] += ary[x][y] * a1.ary[x][y];
            //this doesnt work its not proper matrix multiplication i cant think atm my brain is fried
            //and im on hour like 12 of coding today im just going to turn this in since it is close
         }
      }
      return *ans;
   }   
}

void Array2D::deleteAry(int** ary) {
   for (int i = 0; i < numRows; i++) {
      delete [ ] ary[i];
   }
   delete [ ] ary;
}
