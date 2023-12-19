#ifndef INTVEC_H_
#define INTVEC_H_
class Intvec { 
public: 
   explicit Intvec(size_t num = 0);
   ~Intvec();
   Intvec(const Intvec& other);
   Intvec& operator=(const Intvec& other);
private: 
   void log(const char* msg);
   size_t m_size; 
   int* m_data;
};
#endif /* INTVEC_H_ */
