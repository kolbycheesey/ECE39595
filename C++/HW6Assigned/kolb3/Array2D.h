#ifndef ARRAY2D_H_
#define ARRAY2D_H_
#include <iostream>
class Array2D {

public:

   //int numRows;
   //int numCols;
   //int ** ary;

   Array2D(int numR, int numC); 
   Array2D(int numR, int numC, int val); 
   //Array2D(const Array2D& other);
   Array2D(Array2D& other);

   virtual int getNumRows() const;
   virtual int getNumCols() const;
   virtual int getElement(int x, int y) const;

   virtual ~Array2D();

   virtual int& operator( )(int row, int col);
   virtual int operator( )(int row, int col) const;

   virtual bool operator==(const Array2D &a1) const;
   virtual Array2D& operator*(const Array2D &a1) const; 
   virtual Array2D& operator=(const Array2D &a1);

   friend std::ostream& operator<<(std::ostream& os, const Array2D& a1);

private:
   Array2D(int numR, int numC, char differentiator);
   void deleteAry(int** ary);
   bool sameSize(const Array2D& other) const;
   int numRows;
   int numCols;
   int ** ary;
};
#endif /* ARRAY2D_H_ */
