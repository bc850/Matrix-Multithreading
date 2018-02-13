# Matrix-Multithreading

Matrix multiplication theory:
Given two matrices, A and B, where matrix A contains m rows and k columns and matrix
B contains k rows and n columns, the matrix product of A and B is matrix P, where P
contains m rows and n columns. Entries in matrix P for row i and column j (the cell (i, j))
is the sum of the products of the corresponding elements from ith row in A and jth
column in B.

Multi-threaded matrix multiplication:
In this problem, you are required to write a multi-threaded program for matrix product.
Calculate each cell of the product matrix P in a separate worker thread. This will involve
creating m × n worker threads. The main (or, parent) thread will initialize the matrices A
and B and allocate space to hold the matrix P. Ideally, these matrices should be declared
as global data so that each worker thread has access to these matrices. The parent thread
will create m × n worker threads, passing each worker the row i from first matrix and
column j from the second matrix that it uses in calculating the matrix product. Once all
worker threads have completed, the main thread will output the product contained in
matrix P. This requires the main thread to wait for all worker threads to finish before it
can output the value of the matrix product.

Program requirements:
Your program should read the data from a file. The file name is provided as commandline
argument at execution time. That is, the user may use the following command,
after compilation, on a console to execute your program:
>> java <program-name> <datafile-name>
Caution: your program must not prompt (ask) the user to enter a filename, nor
there should be any “hard-coding” of the input file in the program.

Data file format:
The data file format is as follows: first line has 4 integer numbers, separated by single
spaces, specifying dimension of two matrices. For example, 3 2 2 3 means matrix A has
dimension 3 × 2 and matrix B has dimension 2 × 3. Your program should check, at the
beginning, whether two matrices are eligible for multiplication and indicate (display) it
with appropriate message.

Then there is a line gap followed by first matrix cell values (written in row-column
arrangement). Each column is separated by two spaces and each row ends with a line
break. After first matrix data, there is a line gap followed by second matrix data. See the
sample data file MatrixData.txt

The program then reads the matrices, completes calculation as specified above, and
produces the output on console in a matrix format (standard row-column format). You
can assume that the data file will have integer value matrices only. However, the given
data file is just a sample. Instructor may test your program using another, but similar, data
file where the matrices have larger dimensions.

[** MUST] Multi-threading requirements:
Each worker thread displays the beginning and ending of their work. That is, the
worker thread calculating cell (i, j) of the product matrix should display the following:
“Thread <i, j> starts calculation” (to indicate beginning of thread run) and “Thread <i, j>
returns <cell-value>”. [The values of i, j, cell-value should be replaced appropriately.]
[Hint: these print statements should be within the run() method of the threads]

All exceptions must be caught properly (use of proper try-catch blocks).
