#include <iostream>
#include "Intvec.h"

Intvec::Intvec(size_t num) : m_size(num), m_data(new int[m_size]) { 
   log("constructor"); 
} 
Intvec::~Intvec() { 
   log("destructor"); 
   if (m_data) { 
      delete[] m_data; m_data = 0;
   }
} 
   
Intvec::Intvec(const Intvec& other) : 
   m_size(other.m_size), m_data(new int[m_size]) {
   log("copy constructor"); 
   for (size_t i = 0; i < m_size; ++i) 
      m_data[i] = other.m_data[i]; 
} 
   
Intvec& Intvec::operator=(const Intvec& other) {
   log("copy assignment operator"); 
   Intvec tmp(other); 
   std::swap(m_size, tmp.m_size); 
   std::swap(m_data, tmp.m_data); 
   return *this; 
} 
   
void Intvec::log(const char* msg) {
   std::cout << "[" << this << "] " << msg << "\n"; 
} 
