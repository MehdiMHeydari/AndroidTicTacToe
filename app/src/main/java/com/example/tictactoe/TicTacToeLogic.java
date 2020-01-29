package com.example.tictactoe;

import android.widget.Button;

public class TicTacToeLogic {

        private int[] board;
        private int turn;           // 1 = X, -1 = O
        private boolean gameOver;

        public TicTacToeLogic() {
            board = new int[numRows()*numRows()];

            reset();
        }

        /**
         * Resets the board to starting positions
         */

        public void reset() {



            for (int i = 0; i < board.length; i++){
                board[i] = 0;

                }
            turn = 1;
           gameOver = false;

            // TODO: Complete this method
        }

        /**
         * Attempts to make a move on the board
         *
         * @param spot the location to move [0, (numRows^2)]
         * @return true if a valid is made, false otherwise
         */
        public boolean makeMove(int spot) {

            if(isGameOver()){
                return false;
            }

            if (!(spot>=0 && spot<=8)){
                return false;
            }

            int value = 0;
            for(int i= 0; i< board.length; i++ ){
                value+= board[i];
            }

            if(value == 0){
                turn = 1;
            }

            if (board[spot] == 0) {
                board[spot] = turn;
                turn = turn * -1;

                return true;
            }


            return false;
        }

        /**
         * Checks for a winner
         *
         * @return the Winner, "TIE" if a tie occurs, or empty string if there is no winner but the game is not over
         */
        public String checkWinner() {
            int i = 0;
            //checks the rows to see if there is a winner
            while (i <= board.length - numRows()) {
                int rowsum = 0;
                int row = i;
                while (row < i + numRows()) {
                    rowsum = rowsum + board[row];
                    row++;
                }


                if (rowsum == numRows()) {
                    gameOver = true;
                    return "X";


                }
                if (rowsum == numRows() * -1) {
                    gameOver = true;
                    return "O";
                }


                i += numRows();

            }
            int j = 0;
            //checks the collumns to see if there is a winner
            while (j < numRows()) {
                int collumnsum = 0;
                int collumn = j;

                while (collumn <= (j + (numRows() * 2))) {
                    collumnsum += board[collumn];
                    collumn = collumn + numRows();

                }
                if (collumnsum == numRows()) {
                    gameOver = true;
                    return "X";
                }
                if (collumnsum == numRows() * -1) {
                    gameOver = true;
                    return "O";
                }
                j++;

            }

            int z = 0;
            int diagnolsum = 0;
            //checks the diagnol going left to right to see if there is a winner
            while (z < board.length) {
                diagnolsum += board[z];
                if (diagnolsum == numRows()) {
                    gameOver = true;
                    return "X";
                }
                if (diagnolsum == numRows() * -1) {
                    gameOver = true;
                    return "O";
                }
                z += (numRows() + 1);
            }


            int w = (numRows()-1);
            int diagnol2sum=0;
            ////checks the diagnol going right to left to see if there is a winner
            while(w <= (board.length - numRows())){
                diagnol2sum += board[w];


                if (diagnol2sum == numRows()){
                    gameOver = true;
                    return "X";
                }
                if (diagnol2sum == numRows() * -1){
                    gameOver = true;
                    return "O";
                }
                w += (numRows()-1);
            }

            for(int p = 0; p< board.length; p++){
                if (board[p]==0){
                    return "";
                }
            }
            gameOver = true;
            return "TIE";

        }


        /**
         * Gets the player name of the spot asked
         *
         * @param spot the spot to check
         * @return the name of the player, empty string is the space is not occupied, or null for invalid locations
         */
        public String getPlayer(int spot) {
            //eliminates any spots out of board
            if (spot < 0 || spot >= board.length) {
                return null;
            }

            //checks which player is in each spot
            int player = board[spot];
            if (player == -1) {
                return "O";
            } else if (player == 1) {
                return "X";
            }
            return "";
        }

        public int numRows(){
            return 3;
        }

        /**
         * Is the game over?
         *
         * @return true if the game is over, false otherwise
         */
        public boolean isGameOver() {
            return gameOver;
        }

        public String whoseTurn() {
            //checks if it is X'S TURN
            if (turn == 1) {
                return "X";
            }
            //checks if it is O'S TURN
            else if (turn == -1) {
                return "O";
            }
            return "";
        }
    }


