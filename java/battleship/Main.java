import java.util.Random;
import java.util.Scanner;

class Main
{
	public static void main(String[] args) 
    {
        /* ---------------------------------------------------------
        // Player choose ship location
        // Print player ships
        
        // Opponent random ship location
        
        // Print player guess
        // Player guess
        // Print player guess
        
        // Opponent guess
        // Print opponent guess

        ------------------------------------------------------------ */
        
        Scanner str = new Scanner(System.in);
        Scanner num = new Scanner(System.in);
        
        Player player = new Player();
        
        int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
        int direction = -1;
        
        System.out.println("Welcome to battleship");
        System.out.println("First, you will need to place your ships");
        player.printMyShips();
        
        for(int i = 0; i < 5; i++)
        {
            Ship ship = new Ship(SHIP_LENGTHS[i]);
            System.out.println("You have to place down a ship with the length of " + SHIP_LENGTHS[i]);
            
            while(true)
            {
                System.out.println("Which row? (A-J)");
                String r = str.nextLine().toLowerCase();
                int row = (int) r.charAt(0) - 97;
                
                System.out.println("Which column?");
                int col = num.nextInt() - 1;
                
                while(true)
                {
                    System.out.println("Horizontal or vertical?");
                    String d = str.nextLine().toLowerCase();
                    
                    if(d.equals("horizontal") || d.equals("h"))
                    {
                        direction = 0;
                        break;
                    }
                    else if(d.equals("vertical") || d.equals("v"))
                    {
                        direction = 1;
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid. Please enter the whole word or the first letter only.");
                    }                    
                }

                try
                {
                    if(player.chooseShipLocation(ship, row, col, direction))
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Ship will overlap another ship. Please choose again.");
                    }
                    
                }
                catch(Exception e)
                {
                    System.out.println("Invalid location. Please choose again.");
                }
            }
            
            player.printMyShips();
        }
        
        System.out.println("Both players have chosen ship locations. The game will now begin");
        
        // Opponent chooses ships
        
        Random rand = new Random();
        
        for(int i = 0; i < 5; i++)
        {
            int k = 0;
            int j = 0;
            Ship s = new Ship(SHIP_LENGTHS[i]);
            while(true)
            {
                int direct = rand.nextInt(2);
                if(direct == 0)
                {
                    k = SHIP_LENGTHS[i] - 1;
                }
                else
                {
                    j = SHIP_LENGTHS[i] - 1;
                }
                int row = rand.nextInt(10 - j);
                int col = rand.nextInt(10 - k);
                
                if(player.chooseOpponentShipLocation(s, row, col, direct))
                {
                    break;
                }
            }
        }
        
        System.out.println("\n--------------\nOpponent ships\n--------------\n");
        
        player.printOpponentShips();
        
        //Game loop
        
        int playerCounter = 0;
        int opponentCounter = 0;
        
        while(playerCounter != 17 && opponentCounter != 17)
        {
            System.out.println("You will make a guess.");
            player.printMyGuesses();
            
            while(true)
            {
                System.out.println("Which row? (A-J)");
                String r = str.nextLine().toLowerCase();
                int row = (int) r.charAt(0) - 97;
                    
                System.out.println("Which column?");
                int col = num.nextInt() - 1;
                
                try
                {
                    if(player.getOpponentStatus(row, col) == 0)
                    {
                        if(player.recordPlayerGuess(row, col))
                        {
                            playerCounter++;
                            System.out.println("Hit!");
                        }
                        else
                        {
                            System.out.println("Miss");
                        }
                        player.printMyGuesses();
                        break;
                    }
                    else
                    {
                        System.out.println("This location is already guessed. Please choose again.");
                    }
                    
                }
                catch(Exception e)
                {
                    System.out.println("This is an invalid location. Please choose again.");
                }  
            }
            
            System.out.println("Total hits = " + playerCounter);
            System.out.println("The opponent will now guess. Hit enter to continue.");
            str.nextLine();
            
            while(true)
            {
            	System.out.println("e");
                int row = rand.nextInt(10);
                int col = rand.nextInt(10);
                
                if(player.getPlayerStatus(row, col) == 0)
                {
                    if(player.recordOpponentGuess(row, col))
                    {
                        opponentCounter++;
                        System.out.println("Opponent hits!");
                    }
                    else
                    {
                        System.out.println("Opponent misses");
                    }
                    player.printOpponentGuesses();
                    break;
                }        
            }
            
            System.out.println("Enter to continue");
            str.nextLine();
        }
        
        if(opponentCounter == 17)
        {
        	System.out.println("Opponent wins!");
        }
        else if(playerCounter == 17)
        {
        	System.out.println("You win!");
        }
    }
}
