#include<thread>
#include<mutex>
#include<condition_variable>
#include<list>
#include <iostream>

#include "Queue.h"

Queue::Queue(int cap) : capacity(cap) { }

Queue::~Queue( ) { } 

void Queue::put(int element) {
   std::unique_lock<std::mutex> lck(mtx);
   //similar to get from lecture 10 page 28
   while(size == capacity) {
      cv.wait(lck);
   }
   queueEntries.push_back(element);
   //added notify all
   cv.notify_all();
   size++;
   puts++;
}

int Queue::get( ) {
   std::unique_lock<std::mutex> lck(mtx);
   //copied this straight from lecture 10 page 28
   while(size == 0){
      cv.wait(lck);
   }
   cv.notify_all();
   if (!queueEntries.empty( )) {
      int v = queueEntries.front( );
      queueEntries.pop_front( );
      size--;
      takes++;
      return v;
   } else {
      return 0;
   }
}

int Queue::getTakes( ) {
   return takes;
}

int Queue::getPuts( ) {
   return puts;
}
