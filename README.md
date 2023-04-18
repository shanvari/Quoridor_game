# Quoridor_game

This project is about solving a puzzle. In this puzzle, you are given a table. At the beginning, some
The white and black circles are placed in this table and the rest of the tables are empty. The goal is to fill this table
It is in such a way that at the end all the houses of the table are filled and the restrictions are fully satisfied
to be
The restrictions are as follows:
 1- Each row and column of the table must contain an equal number of black and white circles. (For example, if our table
 8 x 8, there must be four white circles and four black circles in each row and column.) Generally If we have an n × n table, there must be n/2 black circles and n/2 white circles in each row and column.
 - More than 2 similar circles cannot be adjacent to each other in one row or one column. (for example, three circles
White and three black circles cannot be adjacent to each other in the same row or column.)
 3- Rows and columns must be unique and there should not be a duplicate row or column.
For better understanding, look at the examples on the next page.

Implementation
First, we have to model the problem as a constraint satisfaction problem.
 1. The variables include houses from the table, which are initially circled by default.
has not been For example, if we have an n × n table and m default circles are placed,
We will have n2 - m variable.
 2. The range includes a black circle or a white circle for each variable.
 3. The limitations are those that were explained in the previous pages.
Then you should use the Backtracking algorithm with Forward Checking and innovative MRV functions
 LCV, let's solve the problem.
The modeling part is implemented by the practice solution team and you have to implement the algorithms related to the solution
do.
Finally, implement the AC3 algorithm and check the effect of using it.
![Screenshot (51)](https://user-images.githubusercontent.com/45328431/232909874-45594483-4e3f-45b4-bafa-cb8da9b976f5.png)

Entrance
In the first line of the input file, there is the number n, which indicates the length of the table, and in the second line, the number m
which indicates the number of default circles will exist. In the next m line, row, column and
The default color of each circle is specified. Note that in the input file, 0 means white circle and 1
It means a black circle and indexing in the input file is from 0.
output
In the output, the initial state related to the input and the final solved state along with the time spent to solve the problem
Dhid's show )
