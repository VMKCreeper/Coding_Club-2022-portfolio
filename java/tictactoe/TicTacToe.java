public class TicTacToe
{
    private int turn;
    private String[][] board;
    private boolean win = false;
   
    public TicTacToe()
    {
       board = new String[3][3];
       for(int i = 0; i < 3; i++)
       {
           for(int j = 0; j < 3; j++)
           {
               board[i][j] = "-";
           }
       }
    }

    //this method returns the current turn
    public int getTurn()
    {
       return turn;
    }

    //This method prints out the board array on to the console
    public void printBoard()
    {
        System.out.println("  0 1 2");
        for(int i = 0; i < this.board.length; i++)
        {
            System.out.print(i + " ");
            for(int j = 0; j < this.board[i].length; j++)
            {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    //This method returns true if space row, col is a valid space
    public boolean pickLocation(int row, int col)
    {
       boolean isValid = false;
       if(this.board[row][col].equals("-"))
       {
           isValid = true;
       }
       return isValid;
    }

    //This method places an X or O at location row,col based on the int turn
    public void takeTurn(int row, int col)
    {
       String symbol = "";
       if(turn % 2 == 0)
       {
           symbol = "X";
       }
       else
       {
          symbol = "O";
       }
       this.board[row][col] = symbol;
       turn++;
    }

    //This method returns a boolean that returns true if a row has three X or O's in a row
    public boolean checkRow()
    {
        boolean row = false;
        int three = 0;
        for(int i = 0; i < 3; i++)
        {
           for(int j = 0; j < 3; j++)
           {
               if(this.board[i][j].equals("X"))
               {
                   three++;
               }
               else if(this.board[j][i].equals("O"))
               {
                   three--;
               }
           }
           if(three == 3 || three == -3)
           {
               row = true;
               this.win = true;
               break;
           }
           else
           {
               three = 0;
           }
        }
        return row;

    }

    //This method returns a boolean that returns true if a col has three X or O's
    public boolean checkCol()
    {
        boolean column = false;
        int three = 0;
        for(int i = 0; i < 3; i++)
        {
           for(int j = 0; j < 3; j++)
           {
               if(this.board[j][i].equals("X"))
               {
                   three++;
               }
               else if(this.board[j][i].equals("O"))
               {
                   three--;
               }
           }
           if(three == 3 || three == -3)
           {
               column = true;
               this.win = true;
               break;
           }
           else
           {
               three = 0;
           }
        }

        return column;

    }

    //This method returns a boolean that returns true if either diagonal has three X or O's
    public boolean checkDiag()
    {
        boolean diag = false;
        int firstDiag = 0;
        int secondDiag = 0;
        int j = 2;

        for(int i = 0; i < 3; i++)
        {
           if(this.board[i][i].equals("X"))
            {
               firstDiag++;
            }
            else if(this.board[i][i].equals("O"))
            {
               firstDiag--;
            }

            if(this.board[i][j].equals("X"))
            {
               secondDiag++;
            }
            else if(this.board[i][j].equals("O"))
            {
               secondDiag--;
            }

           j--;
        }

        if(firstDiag == 3 || secondDiag == 3 || firstDiag == -3 || secondDiag == -3)
        {
            diag = true;
            this.win = true;
        }
        return diag;
    }

    //This method returns a boolean that checks if someone has won the game
    public boolean checkWin()
    {
        return this.win;
    }

    public void winMessage()
    {
       if(turn % 2 == 0)
       {
           System.out.println("Player 2 wins!");
       }
       else
       {
         System.out.println("Player 1 wins!");
       }
    }
}
