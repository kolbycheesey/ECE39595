#include "BinaryNode.h"
#include "Student.h"
#include "User.h"
int main(void) {
   BinaryNode<int> bnint(5);
   bnint.insertContent(3);
   bnint.insertContent(6);
   bnint.insertContent(4);
   bnint.insertContent(7);
   bnint.insertContent(6);
   bnint.insertContent(9);
   bnint.print(0);
   std::cout << "______" << std::endl;
   Student stu1("John"); 
   Student stu2("Mary"); 
   Student stu3("Tom"); 
   Student stu4("Amy"); 
   Student stu5("Ted"); 
   BinaryNode<Student> bnstu(stu1); 
   bnstu.insertContent(stu2);
   bnstu.insertContent(stu3);
   bnstu.insertContent(stu4);
   bnstu.insertContent(stu5);
   bnstu.print(0);
   std::cout << "__________" << std::endl;
   User usr1(21);
   User usr2(28);
   User usr3(19);
   User usr4(17);
   User usr5(22);
   User usr6(20);
   User usr7(18);
   BinaryNode<User> bnusr(usr1);
   bnusr.insertContent((User)3);
   bnusr.insertContent(usr2);
   bnusr.insertContent(usr3);
   bnusr.insertContent(usr4);
   bnusr.insertContent(usr5);
   bnusr.insertContent(usr6);
   bnusr.insertContent(usr7);
   bnusr.print(0);
   std::cout << "__________" << std::endl;
   std::cout << bnint.searchContent(11) << " ";
   std::cout << bnint.searchContent(10) << std::endl;
   Student stu6("Ted");
   std::cout << bnstu.searchContent(stu6)<< " ";
   std::cout << bnstu.searchContent(stu2)<< std::endl;
   std::cout << bnusr.searchContent(usr2)<< " ";
   std::cout << bnusr.searchContent(usr4)<< std::endl;
}

