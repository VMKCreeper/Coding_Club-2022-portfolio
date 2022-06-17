import java.util.ArrayList;

class Main {
  public static void main(String[] args) 
  {
      //-53, 630
    twoFactors(-200, 8000);
  }
    
    //sum function (x + y = b)
    //product function (x * y = c)
  public static void twoFactors(int a, int b) 
  {
      ArrayList<Integer> factors = new ArrayList<Integer>();
      ArrayList<Integer> negativeFactors = new ArrayList<Integer>();
    
      int x = 0;
      boolean yes = false;
    
        if (b >= 0) //Positive number
        {
            for(int i = b; i >= 1; i--)
            {
                if (b % i == 0)
                {
                    if (i*i == b)
                    {
                        for(int c = 0; c < 2; c++)
                        {
                            factors.add(i);
                            negativeFactors.add(i*-1);
                        }
                    }
                    else
                    {
                        factors.add(i);
                        negativeFactors.add(i*-1);
                    }  
                } 
            }
            
            //System.out.println(factors);
            //System.out.println(negativeFactors);

            int y = factors.size() - 1;

            for(int i = 0; i < factors.size() / 2; i++)
            {
                if (factors.get(x) + factors.get(y) == a)  {
                    System.out.println(factors.get(x) + ", " + factors.get(y));
                    yes = true;
                    break;
                }
                if (negativeFactors.get(x) + negativeFactors.get(y) == a)  {
                    System.out.println(negativeFactors.get(x) + ", " + negativeFactors.get(y));
                    yes = true;
                    break;
                }
                x += 1;
                y -= 1;
            }
        }
        else //Negative number
        {
           for(int i = b; i <= -1; i++)
            {
                if (b % i == 0)
                {
                    factors.add(i);
                    factors.add(b / i);
                } 
            }
            //System.out.println(factors);

            int y = 1;  

            for(int i = 0; i < factors.size() / 2; i++)
            {
                if (factors.get(x) + factors.get(y) == a)  {
                    System.out.println(factors.get(x) + ", " + factors.get(y));
                    yes = true;
                    break;
                }
                x += 2;
                y += 2;
            }
        }

    if (!yes)
        {
            System.out.println("Not factorable");
        }
  }
}
