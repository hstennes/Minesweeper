# MineSolver

A Minesweeper solver (that comes with its own Minesweeper clone). The solver uses an algorithm that writes a system of equations to represent the game board, and then solves this system using Gaussian elimination.  This algorithm is able to solve the vast majority of "solvable" Minesweeper configurations (those that do not require guessing) and avoids any kind of brute force / guess-and-check techniques.  However, due to the fact that [Minesweeper is NP-complete](https://www.weizmann.ac.il/sci-tea/benari/sites/sci-tea.benari/files/uploads/softwareAndLearningMaterials/minesweeper-en.pdf), this type of algorithm is unfortunately not able to solve all configurations, a discovery I made only after completing it.

In the ideal case, the board is fully solvable without guessing, and the algorithm is able to play the game to its conclusion.
![A successful game](https://i.imgur.com/FU1Nl5X.png)

However, as any seasoned Minesweeper player will tell you, the game often generates configurations that require guessing, such as the one below.
![Guessing required](https://i.imgur.com/SdhDHFG.png)

Some solvable confiruations are still not solved by this algorithm.  A perfect algorithm would flag the square to the lower right of the 3 as a mine, but there is no known way to create such an algorithm that avoids some kind of guess-and-check, however optimized it may be.
![Algorithm limitation](https://i.imgur.com/WcxunTq.png)
