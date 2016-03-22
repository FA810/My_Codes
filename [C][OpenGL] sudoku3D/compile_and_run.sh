#!/bin/bash
# this is for 32-bit architecture only!
# compile
gcc -Wall -I/usr/include/ -c -o sudoku3d.o sudoku3d.c
gcc -Wall -I/usr/include/ -o sudoku3d -L/usr/X11R6/lib  sudoku3d.o -lX11 -lglut -lGL -lGLU -lm
# optional cleaning
rm sudoku3d.o
# run
./sudoku3d
