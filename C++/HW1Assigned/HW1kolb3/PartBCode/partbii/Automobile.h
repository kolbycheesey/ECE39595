#ifndef AUTOMOBILE_H_INCLUDED
#define AUTOMOBILE_H_INCLUDED
#include <string>

//declarations

class Automobile {
    
    private:
        int vin;
        float miles;
        std::string lastMaintenance;
        //for part ii
        int mm;
        int dd;
        int yyyy;

    public:
        Automobile(int number, float milesDrive, int month, int day, int year);

    //get
    int getVin();
    float getMiles();
    std::string getDate();

    //set
    void setVin(int number);
    void setMiles(float milesDrive);
    void setDate(int month, int day, int year);
};

#endif