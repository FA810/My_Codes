Programming Test

Make a program in your favorite language/environment that takes two inputs:

 • a set of include intervals
 • a set of exclude intervals

The sets of intervals can be given in any order, and they may be empty or overlapping.

The program should output the result of taking all the includes and “remove” the excludes.

The output should be given as non-overlapping intervals in a sorted order.

Intervals will contain integers only.

Example 1:

Includes: 10-100
Excludes: 20-30

Output should be: 10-19, 31-100

Example 2:
Includes: 50-5000, 10-100
Excludes: (none)
Output: 10-5000

Example 3:
Includes: 10-100, 200-300
Excludes: 95-205
Output: 10-94,206-300

Example 4:
Includes: 10-100, 200-300, 400-500
Excludes: 95-205, 410-420
Output: 10-94, 206-300, 400-409, 421-500

The program does not need to behave in any particular way if invalid intervals are entered.
The program does not need to have any mechanism for ending the program.
The code for entering and printing out data is not important.  The example execution above suggests console based input, but any
user interface providing the same functionality is acceptable.


The code will be evaluated according to the following criteria:

 • That the code works correctly and performs the task described in this text.

 • That the code is easily maintainable, i.e. easy to read and understand, and can easily be
   adopted to use in a large program.

 • That the implementation is efficient and scales well when given large amounts of input data.


