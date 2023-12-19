#include <memory>
#include <iostream>

std::weak_ptr<int> weakPtr; 

void observe( ) { 
   std::cout << "use_count == ";
   std::cout << weakPtr.use_count() << ": "; 
   std::shared_ptr<int> sharedPtr = weakPtr.lock( );
   if (sharedPtr) { // Must be copied into
                    // a shared_ptr before using 
      std::cout << "*sharedPtr: " << *sharedPtr << "X\n";
   } else { 
      std::cout << "weakPtr is expired\n";
   } 
} 

int main(int argc, char** args) {
    std::shared_ptr<int> sp;
    sp = std::make_shared<int>(42); 
    weakPtr = sp; 
    observe();  
    sp.reset( );
    observe( );
}

