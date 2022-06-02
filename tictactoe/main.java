import java.util.Scanner;
import java.util.Random;

class Main 
{
    public static void main(String[] args) 
    {
        int turn = 0;
    	
        TicTacToe game = new TicTacToe();
        game.printBoard();
        
        while(game.checkWin() == false && turn < 9)
        {
            makeMove(game);
            turn++;
            check(game);
            game.checkWin();
            if(game.checkWin() == false)
            {
            	game.printBoard();
            	easyBot(game);
            	turn++;
                game.printBoard();
            }
        }
        game.printBoard();
        if(game.checkWin())
        {
        	game.winMessage();
        }
        else
        {
        	System.out.println("Its a draw");
        }
    }

    public static void check(TicTacToe game)
    {
        game.checkRow(); 
        game.checkCol();
        game.checkDiag();
    }
    
    public static void makeMove(TicTacToe game)
    {
    	Scanner sc = new Scanner(System.in);
    	
    	boolean yeet = true;
        while(yeet)
        {
        	String choice = sc.nextLine();

            String[] array = choice.split(" ");
            int i = Integer.parseInt(array[0]);
            int j = Integer.parseInt(array[1]);
        	if(game.pickLocation(i, j))
            {
                game.takeTurn(i, j);
                yeet = false;
            }
        	else
        	{
        		System.out.println("This location is taken, please enter new location.");
        	}
        }
    }
    
    public static void easyBot(TicTacToe game)
    {
    	Random move = new Random();
    	
    	boolean yeet = true;
		// 1 second delay
    	try 
    	{
            Thread.sleep(1000);
        } 
    	catch (InterruptedException e) 
    	{
            e.printStackTrace();
        }
    	
		while(yeet)
		{
			int col = move.nextInt(3);
			int row = move.nextInt(3);
			if(game.pickLocation(col, row))
	        {
	            game.takeTurn(col, row);
	            yeet = false;
	        }
		}		
    	check(game);
    	game.checkWin();
    }
}
