#include <iostream>
#include <string>
using namespace std;
template <class TPL> class BinaryNode {
   BinaryNode * bn_left;
   BinaryNode * bn_right;
   TPL bn_content;
public:
   BinaryNode(TPL content);
   virtual ~BinaryNode( );
   virtual void insertContent(TPL content);
   virtual bool searchContent(TPL content) const;
   virtual void print(int depth) const;
};

template <class TPL> BinaryNode<TPL>::BinaryNode(TPL content) {
   bn_content = content;
   bn_left = NULL;
   bn_right = NULL;
}

template <class TPL> void BinaryNode<TPL>::insertContent(TPL
                                                 content){
   if (content == bn_content) {return;} // no duplicates
   if (content < bn_content) {
      if (bn_left == NULL) {
         BinaryNode<TPL> *newnode = new BinaryNode<TPL>(content);
         bn_left = newnode;
      } else {
         bn_left->insertContent(content);
      }
   } else {
      if (bn_right == NULL) {
         BinaryNode<TPL> * newnode = new BinaryNode<TPL>(content);
         bn_right = newnode;
      } else {
         bn_right->insertContent(content);
      }
   }
}

template <class TPL> bool BinaryNode<TPL>::searchContent(
                                     TPL content) const {
   if (content == bn_content) {return true;}
   if (content < bn_content) {
      if (bn_left == NULL) {return false;}
      return bn_left->searchContent(content);
   } else {
      if (bn_right == NULL) {return false;}
      return bn_right->searchContent(content);
   }
}

template <class TPL> void BinaryNode<TPL>::print(
                               int depth) const {
   if (bn_left != NULL) {bn_left->print(depth+1);}
   for (int identcnt = 0; identcnt < depth; identcnt++)
   {
      cout << "\t";
   }
   cout << bn_content << endl;
   if (bn_right != NULL) {bn_right->print(depth+1);}
}

template <class TPL> BinaryNode<TPL>::~BinaryNode( ) {
   if (bn_left != NULL) {delete bn_left;}
   if (bn_right != NULL) {delete bn_right;}
}
