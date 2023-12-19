#include <iostream>
#include <chrono>
#include <memory>

//added includes
#include <thread>
#include <mutex>  //not sure if this is needed yet will update as I work on the rest

#include "Quicksort.h"
#include "DotProduct.h"
#include "WorkQueue.h"

#define NUMSORTS 4
#define STARTSIZE 8000
#define NUMTHREADS 4
std::mutex outputLock;
//std::mutex joinLock;

using namespace std::chrono;


void worker(WorkQueue workQ) {
   std::shared_ptr<Command> c(workQ.get( ));
   while (c != nullptr) {
      c->execute( );
      std::lock_guard<std::mutex> lck(outputLock); 
      (*c).identify( );
      c = workQ.get( );
   }
}


int main(int argc, char** args) {

   int sortSize = STARTSIZE;
   auto tottimestart = high_resolution_clock::now();
   WorkQueue Queue = WorkQueue();
   //QuickSort sort = QuickSort(STARTSIZE);
   //DotProduct dot = DotProduct(STARTSIZE);
   std::thread t[NUMTHREADS];
   for(int i = 0; i < NUMSORTS; i++){
      //Queue.put(std::shared_ptr<Command> (new DotProduct(sortSize)));
      Queue.put(std::shared_ptr<Command> (new QuickSort(sortSize)));
      Queue.put(std::shared_ptr<Command> (new DotProduct(sortSize)));
      sortSize *= 2;
   }
   //some issues with the addition not sure as to why whill need to look at more closely
   //if in office hours ask about it, getting some add 2 not 3 but then is corrected on
   //next statement

   //after looking at piaza post 99 looks like he gets the same response so I should be good
   //this function is basically what is going to happen on one thread so will want four threads
   //to each run this and then will want to add the dotProduct into it
   for (int i=0; i<NUMSORTS; i++) {
      //DotProduct dot = DotProduct(sortSize);
      //QuickSort sort = QuickSort(sortSize);

      t[i] = std::thread(worker, Queue);
      //t.join( );     //want this in a different for loop

      //auto start = high_resolution_clock::now();

      //believe this is what the execute function is for instead.
      
      //sort.sort( );
      //dot.math( );   //might have to call something with it we will see

      //auto stop = high_resolution_clock::now();
      //auto duration = duration_cast<microseconds>(stop - start).count( );
      
      /*std::cout << "Sort of " << sortSize << " ints took " << duration << " microseconds\n";
      std::cout << "dot: \n" << dot << "\n";
      std::cout << "sort: \n" << sort << "\n";
      sortSize *=2;*/
   }
   for(int i  = 0; i<NUMSORTS; i++){
      //std::lock_guard<std::mutex> lck(joinLock);
      t[i].join();
      //t[i].clear( );
   }
   //doesnt make a difference
   //t[NUMTHREADS].~thread();

   //should be good for the timing function
   auto tottimestop = high_resolution_clock::now();
   auto totduration = duration_cast<microseconds>(tottimestop - tottimestart).count();
   std::cout << "execution time with 4 threads is " << totduration << " microseconds\n";
}
