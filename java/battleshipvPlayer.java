public class Player
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    private Ship[] ships;
    private Grid player = new Grid();
    private Grid opponent = new Grid();
    private int numShips = 0;
    private int numOpShips = 0;
    
    public Player()
    {
        ships = new Ship[SHIP_LENGTHS.length];
    }
    
    public boolean chooseShipLocation(Ship s, int row, int col, int direction)
    {
        if(numShips != 5)
        {
            s.setLocation(row, col);
            s.setDirection(direction);
            if(player.available(s))
            {
                player.addShip(s);
                numShips++;
                return true;
            }
        }
        return false;
    }
    
    public boolean chooseOpponentShipLocation(Ship s, int row, int col, int direction)
    {
        if(numOpShips != 5)
        {
            s.setLocation(row, col);
            s.setDirection(direction);
            if(opponent.available(s))
            {
                opponent.addShip(s);
                numOpShips++;
                return true;
            }
        }
        return false;
    }
    
    // Print your ships on the grid
    public void printMyShips()
    {
        player.printShips();
    }
    
    public void printOpponentShips()
    {
        opponent.printShips();
    }
    
    // Print opponent guesses
    public void printOpponentGuesses()
    {
        player.printStatus();
    }
    
    // Print your guesses
    public void printMyGuesses()
    {
        opponent.printStatus();
    }
    
    // Record a guess from the opponent
    public boolean recordOpponentGuess(int row, int col)
    {
        if(player.hasShip(row, col))
        {
            player.markHit(row, col);
            return true;
        }
        else
        {
            player.markMiss(row, col);
            return false;
        }
    }
    
    public boolean recordPlayerGuess(int row, int col)
    {
        if(opponent.hasShip(row, col))
        {
            opponent.markHit(row, col);
            return true;
        }
        else
        {
            opponent.markMiss(row, col);
            return false;
        }
    }
    
    public int getOpponentStatus(int row, int col)
    {
        return opponent.getStatus(row, col);   
    }
    
    public int getPlayerStatus(int row, int col)
    {
        return player.getStatus(row, col);
    }
}
