/* 
 * taken from https://en.cppreference.com/book/intro/smart_pointers
 */

#include <iostream>
#include <memory>
#include <utility>
 
int main()
{
    std::unique_ptr<int> valuePtr(new int(15));
    std::unique_ptr<int> valuePtrNow(std::move(valuePtr));
 
    std::cout << "valuePtrNow = " << *valuePtrNow << '\n';
    std::cout << "Has valuePtr an associated object? "
              << std::boolalpha
              << static_cast<bool>(valuePtr) << '\n';
}

