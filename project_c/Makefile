### Makefile ###

CC = gcc 

CFLAGS = -Wall -O3 -ansi -D_GNU_SOURCE -g -std=c99

INCLUDE =-I include
SRC := src
OBJ := obj

SOURCES := $(wildcard $(SRC)/*.c)
NAME = program

OBJECTS := $(patsubst $(SRC)/%.c, $(OBJ)/%.o, $(SOURCES))

program: $(OBJECTS)
			$(CC) $(CFLAGS) $(INCLUDE) -o $(NAME) $(OBJECTS)
			


$(OBJ)/%.o: $(SRC)/%.c
				$(CC) $(CFLAGS) $(INCLUDE) -c $< -o $@

$(shell mkdir -p $(OBJ))

clean:
			rm -r $(OBJ)
			rm -f program
			rm -f gesval

cleandocs:
			rm -r docs/html
			rm -r docs/latex 