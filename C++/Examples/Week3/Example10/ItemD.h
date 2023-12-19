#ifndef ITEMD_H_
#define ITEMD_H_
#include "Item.h"

class ItemD : public Item {
public:
   ItemD( );
   virtual ~ItemD( );
   static int getNumberOfItems( );
private: // "private" to make explicit
};
#endif /* ITEMD_H_ */

