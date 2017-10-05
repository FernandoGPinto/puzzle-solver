# puzzle-solver

The puzzle solver was designed to solve sudoku style square matrices with three constraints:

1- There must be a digit in every cell between zero and the size of the puzzle (inclusive)<br>
2- Digits cannot be repeated in each row<br>
3- Digits cannot be repeated in each column

The size of the puzzle corresponds to the number of cells in each side (ie. number of rows or number of columns)
and the puzzle solver accepts a number between 2 and 50.

The user must enter a clue in the form of a digit in a selected cell, by indicating the column and row where it lies
Eg. Size: 5, Clue: column=3 row=2 digit=4
