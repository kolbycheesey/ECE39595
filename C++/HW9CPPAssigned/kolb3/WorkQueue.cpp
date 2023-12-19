#include <iostream>
#include <string>
#include <stdlib.h>
#include <vector>
#include <memory>
#include <mutex>

#include "WorkQueue.h"

static std::mutex putMutex;

//zero arg might need one more
WorkQueue::WorkQueue() {
    //vector is made in .h file so shouldnt need anything here
    q = std::vector<std::shared_ptr<Command>>();
}

void WorkQueue::put(std::shared_ptr<Command> fun ){
    //going to want to ask questions about this
    //std::lock_guard<std::mutex> lck(putMutex);
    putMutex.lock();
    //std::cout << "just after lock.put" << std::endl;
    q.push_back(fun);/* not sure what i need to put here*/
    //release mutex
    //std::lock_guard<std::mutex> unlock(putMutex);
    putMutex.unlock();
    //std::cout << "just after unlock.put" << std::endl;
}

std::shared_ptr<Command> WorkQueue::get( ) {
    //think this is all we need for the get function
    //std::lock_guard<std::mutex> lck(putMutex);
    putMutex.lock();
    //std::cout << "just after lock.get" << std::endl;
    //check to see if empty
    if(q.empty()){
        //std::lock_guard<std::mutex> unlock(putMutex);
        putMutex.unlock();
        return NULL;
    }
    std::shared_ptr<Command> task = q.back();
    q.pop_back();
    //std::lock_guard<std::mutex> unlock(putMutex); //might not need
    
    //release mutex
    //std::lock_guard<std::mutex> unlock(putMutex);
    putMutex.unlock();
    //std::cout << "just after unlock.get" << std::endl;

    //other option removes from front
    //q.erase(q.begin()); 
    return task;

}

