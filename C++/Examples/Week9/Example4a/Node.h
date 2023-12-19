#ifndef NODE_H_
#define NODE_H_
#include <memory>
class Node {
private:
   int value;
public:
   std::shared_ptr<Node> next;

   Node(int v);
   ~Node( );
   void print(int level);
};
#endif /* NODE_H_ */
