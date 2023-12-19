#include "Object.h"

void bar (Object& ref, Object* ptr) {
   ref.attribute = 0;
   ptr->attribute = 0;
}

void foo(const Object& ref, const Object* ptr) {
   ptr = new Object(2);
   // ref.attribute = 0; ptr->attribute = 0;    
   // bar(ref, ptr);
   // Object* ptr2 = ptr;
   // Object* ptr3 = &ptr; Object* ptr4 = &ref;
   long ptrValue = (long) ptr;
   void* ptr5 = (void*) ptrValue;
   ((Object*) ptr5)-> attribute = 0;
}  
