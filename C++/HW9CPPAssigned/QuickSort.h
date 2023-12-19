#include <iostream>
#include <string>
#include "Command.h"

class QuickSort : public Command {      //this should be the inheritance line now to add the functions from command.h
//not sure why I am getting this error
//class QuickSort {
private:
   int* arr;
   int length;

   void quickSort(int low, int high);
   int min(int v1, int v2);
   int findPivot(int arr[ ], int low, int high);
   int partition(int low, int high);

public:
   QuickSort( );
   QuickSort(int numElements);

   virtual void sort( );
   //next two functions should be the command functions
   void execute (); //{ std::cout << "execute() called";}
   void identify (); //{ std::cout << "identify() called";}

   friend std::ostream& operator<<(std::ostream& os, const QuickSort& qs);
};
