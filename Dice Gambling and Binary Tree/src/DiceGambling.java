/* 
    Use java to implement a class Dice, a class Gambler and a method that continue gambling between two gamblers until one
    gambler cannot afford the stake.
 */

// the class of Dice to play
import java.lang.Math;

class Dice {
    private int num_faces;

    Dice(){
        num_faces=6; // standard dice with 6 faces by default.
    }

    // implement the roll dice method that roll the dice once and return the value of the roll.
    // (5 marks)
    public int rollDice() {
        /* place your code here */
        return (int)(Math.random()*6 + 1);  // returns a number between 1 and 6
    }
}

// the class of gambler
class Gambler {
    public int money;
    public Dice die;

    Gambler(int initial_money) {
        money = initial_money;
        die = new Dice();
    }

    // use static method to implement the gambling activity.
    // In a gambling activity, two gamblers gamble on a stake.
    // Each gambler rolls a die, the gambler who roles a larger value wins the stake.
    // if it is a due, then roll again, until there is a winner.
    // Note: a gambler cannot start gamble if he does not have more money than the amount of stake.
    // Note: return false if at least one gambler cannot afford the stake. Otherwise, return true.
    // Note: print the result of rolled dice values, and the winner of the gambling.
    static boolean gambling(Gambler gambler_1, Gambler gambler_2, int stake) {
        /* place your code here */

        // check if the first gambler has enough money to gamble
        if (gambler_1.money < stake) {
            System.out.println("Gambler 1 cannot afford the stake.");
            return false;
        }

        // check if ths second gambler has enough money to gamble
        else if (gambler_2.money < stake) {
            System.out.println("Gambler 2 cannot afford the stake.");
            return false;
        }

        int die1 = gambler_1.die.rollDice();        // die number for gambler 1
        int die2 = gambler_2.die.rollDice();        // die number for gambler 2

        // if gambler's die are equal, then roll again until there is a winner
        while (die1 == die2) {
            die1 = gambler_1.die.rollDice();
            die2 = gambler_2.die.rollDice();
        }

        // Prints the result of the rolled dice values
        System.out.println("The rolled dice values are... ");
        System.out.println("Gambler 1: " + die1);
        System.out.println("Gambler 2: " + die2);

        // if gambler 1's die is greater than gambler 2's die, gambler 1 wins the stake
        if (die1 > die2) {
            // add the stake amount to gambler 1's balance and remove from gambler 2's
            gambler_1.money += stake;
            gambler_2.money -= stake;
            // Prints the winner of the gambling
            System.out.println("The winner of the gambling is gambler 1");
        }

        // if gambler 2's die is greater than gambler 1's die, gambler 2 wins the stake
        else {   // gambler_1.die < gambler_2.die;
            // add the stake amount to gambler 2's balance and remove from gambler 1's
            gambler_1.money -= stake;
            gambler_2.money += stake;
            // Prints the winner of the gambling
            System.out.println("The winner of the gambling is gambler 2");
        }
        // Otherwise return true if gamblers can afford the stake
        return true;
    }
}

public class DiceGambling {

    // Continue Gambling until one gambler cannot afford the stake
    // Print the amount of money of each gambler after each round of gambling.
    // Print the value of rolled dice for each gambler in each round of gambling.
    public static void keepGambling(Gambler gambler_1, Gambler gambler_2, int stake, int max_gambling_times) {
        /* place your code here */
        int gamb_count = 1;     // count the number of times the gambling game was played

        System.out.println("Gambler 1's money: " + gambler_1.money);
        System.out.println("Gambler 2's money: " + gambler_2.money);
        System.out.println();

        // keep gambling if the gamble amount have not reached the max gambling times
        while (gamb_count <= max_gambling_times){
            boolean keep_gamb = Gambler.gambling(gambler_1, gambler_2, stake);
            // if gamblers have enough money, continue playing
            if (keep_gamb) {
                // Print the value of money of each gambler after each round of gambling
                System.out.println("Money after gambling round " + gamb_count);
                System.out.println("Gambler 1: " + gambler_1.money);
                System.out.println("Gambler 2: " + gambler_2.money);
                System.out.println();
                gamb_count += 1;
            }
            // if gamblers do not have enough money, stop gambling!
            else{
                System.out.println("Terminating gambling.");
                break;
            }
        }
        if (gamb_count > max_gambling_times){
            System.out.println("Maximum gambling times reached (" + max_gambling_times + ").");
            System.out.println("Terminating gambling.");
        }
    }

    public static void main(String[] args) {
        int initial_money = 10;
        int stake = 5;
        int max_gambling_times = 10;

        Gambler gambler1 = new Gambler(initial_money);
        Gambler gambler2 = new Gambler(initial_money);

        keepGambling(gambler1, gambler2, stake, max_gambling_times);
    }
}
