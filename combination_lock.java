import java.util.Scanner;

class Main {
  public static void main(String[] args) {

    boolean running = true;
    String guess = "";
    int counter = 0;

    Scanner sc = new Scanner(System.in);

    System.out.println("Input your combination:");
    Combination lock = new Combination(sc.nextLine());

    while (running) 
    {
        System.out.println("Length: " + lock.getLength());
        System.out.println("Input your guess:");
        guess = lock.getClue(sc.nextLine());
        System.out.println(guess);
        counter++;

        if (lock.toString().equals(guess))
        {
            running = false;
        }
    }

    System.out.println("Guesses: " + counter);
    }
}
