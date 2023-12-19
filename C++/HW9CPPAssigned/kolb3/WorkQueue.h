#include <iostream>
#include <string>
#include <vector>
#include <memory>
#include "Command.h"

class WorkQueue : public Command {      //this should be the inheritance line now to add the functions from command.h
//not sure why I am getting this error
//class QuickSort {
private:
    //int* arr;
    //int length;
    std::vector<std::shared_ptr<Command>> q;

public:
   //QuickSort( );
   //QuickSort(int numElements
   WorkQueue();
    std::shared_ptr<Command> get();
    void put(std::shared_ptr<Command> fun);
   //virtual void sort( );
   //next two functions should be the command functions
    void execute () { std::cout << "execute() called";}
    void identify () { std::cout << "identify() called";}
};