# Tic Tac Toe

A Tic Tac Toe game with a scalable board, various game modes, and a
selection of avatars.

## Dependencies

-   [Processing 3.3.6](https://processing.org/)
-   [ControlP5 2.2.6](http://www.sojamo.de/libraries/controlP5/)

## Usage

### Setup

On the initial setup screen, the first slider is for setting the board
size to *n* by *n*. Next, select from the following drop-down list
whether to play against another human or the computer.

The second slider is for setting the difficulty of the bot. A level 1
bot will always make random moves, while a level 10 bot will guarantee a
draw. **If the user selected to play against a human, this slider will
have no effect.**

The second drop-down list is for selecting who will go first. Selecting
`EX` will allow the user to place first, whereas selecting `OH` will
allow the computer to go first. **This selection will also have no
effect if the player selected to play against another human.**

The final drop-down list is for choosing the avatar/symbol set for the
game. There are around a dozen choices; try them all out!

### Defaults

The default values will create a human vs. human game on a 3x3 board.
"Iwinski vs. Todd" is the default image set.

### Keybinds

Press the key 'r' at any time to restart.

### Notes

The bot is a minimax algorithm with memoization. It has been tested to
work within a reasonable time frame for 3x3 and 4x4 boards. On 4x4
boards, the player's move will not show up until the bot's calculations
have been completed. This may take a couple of seconds. For boards
larger than 4x4, playing against a bot level 2 or higher is not
recommended.
