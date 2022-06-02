public class Combination
{
    private String combination;
    private String code;
    private int array = 0;
    
    //constructor
    public Combination(String input)
    {
        combination = input;
    }

    public String getClue(String input)
    {
        code = "";
        char[] guess = input.toCharArray();
        char[] check = combination.toCharArray();
        
        for (int i = 0; i < Math.min(guess.length, check.length); i ++) 
        {
            if (guess[i] != check[i]) 
            {
                for (int j = 0; j < check.length; j ++)
                {
                    if (guess[i] == check[j]) 
                    {
                        code += "+";
                        break;
                    }
                    else
                    {
                        if (j == (check.length-1)) 
                        {
                            code += "*";
                        }
                    }
                }
            }
            else
            {
                code += guess[i];
            }
        }
        return code;
        
    }

    public int getArray(String input)
    {
        char[] guess = input.toCharArray();

        for (int i = 0; i < guess.length; i ++) 
        {
            array += guess[i];
        }
        return array;
    }

    public String toString()
    {
        return combination;
    }

    public int getLength()
    {
        return combination.length();
    }
}
