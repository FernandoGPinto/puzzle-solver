# puzzle-solver

<b>The puzzle solver was designed to solve sudoku style square matrices with three constraints:</b>

1- There must be a digit in every cell between zero and the size of the puzzle (inclusive)<br>
2- Digits cannot be repeated in each row<br>
3- Digits cannot be repeated in each column

The size of the puzzle corresponds to the number of cells in each side (ie. number of rows or number of columns)
and the puzzle solver accepts a number between 2 and 50.

The user must enter a clue in the form of a digit in a selected cell, by indicating the column and row where it lies
Eg. Size: 5, Clue: column=3 row=2 digit=4

<b>Inner workings:</b>

The algorithm uses its own version of dancing links to help solve a square puzzle with (theoretically) any number of cells. Dancing links works by creating a matrix of doubly linked nodes, both vertically and horizontally, mediated by a row of headers, which represent the constraints of the puzzle, forming a toroid.

Each cell takes one digit, the number of digits available is the same as the number of rows or columns and digits must not be repeated in each row and column. Knowing this it was possible to create a bitfunction for each constraint, which places each node in the right place. The algorithm then proceeds to identify each possible solution and eliminate conflicting alternatives by removing the links, also known as covering.

JavaFX was used to create a simple and clear GUI.


https://user-images.githubusercontent.com/32436981/217868050-b2695814-e744-4390-b41f-e1967a3be2f4.mp4
