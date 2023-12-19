#include <iostream> // for cout, endl 
#include <string> 
#include <memory> 
#include <vector>

void print(const std::vector<std::unique_ptr<int>>& v);

int main( ) {    
   
   std::vector<std::unique_ptr<int>> vec;
   std::unique_ptr<int> uptr = std::make_unique<int>(34);
   vec.push_back(std::move(uptr));    
   vec.push_back(std::make_unique<int>(23)); // size is now 2    
   print( vec ); // 34 23    
   return 0; 
} 

void print(const std::vector<std::unique_ptr<int>>& v) {
   std::cout << "\nvector size is: ";
   std::cout << v.size() << std::endl;
   // std::vector<std::unique_ptr<int>>::iterator p = v.begin();
   for (const auto& p: v) {
       std::cout << *p << "  "; 
   }
   std::cout << std::endl << std::endl; 
}
