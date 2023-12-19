# suffix rules don't allow pre-requisites to be used and are deprecated for
# this reason.  The right way to do this is to use patterns.  The "%" pattern
# allows any file to be matched.

#okay so for whatever reason this doesnt work so if you run the command line it will run
OBJS = HW6.o Array2D.o 
CC = g++
CFLAGS = -Wall -g
INCLUDE = 
LIBS = 
CFLAGS = -Wall -g
EXECUTABLE = HW6
COMMANDLINE = #g++ -std=c++11 -o HW6 *.cpp *.h

.PHONY : run
run : $(EXECUTABLE)
	./$(EXECUTABLE) $(COMMANDLINE)

cleanmake : clean HW6

.PHONY : $(EXECUTABLE)
$(EXECUTABLE) :  $(OBJS)
	$(CC) $(CFLAGS) -o $(EXECUTABLE) $(OBJS) $(INCLUDE) $(LIBS)

%.o : %.c 
	$(CC) $(CFLAGS) -c $@ $(INCLUDE) $(LIBS)

clean :
	rm $(EXECUTABLE) ${OBJS} 
