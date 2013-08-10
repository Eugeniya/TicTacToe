import com.tictactoe.game.*;

import java.util.Scanner;

/**
 * Date: 25.07.13
 * Time: 19:56
 */
public class Main {
    public static void main(String[] args) {
        Game game;
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("~~~~~~ Tic Tac Toe ~~~~~~");
        System.out.println("Make a choice");
        System.out.println("1. Local game");
        System.out.println("2. Network game");
        System.out.println("3. Exit");

        //проверка ввода корректного номера меню
        while (choice != 1 && choice != 2 && choice != 3)
        {
            while (!sc.hasNextInt()) {
                System.out.println("Please re-enter");
                sc.nextLine();
            }

            choice = sc.nextInt();
        }

        if(choice == 1)
        {
            System.out.println("Local game!");
//            game = new Local();
        }

        if(choice == 2)
            System.out.println("Network Game");

        if(choice == 3)
        {
            System.out.println("Goodbye!");
            return;
        }


       // game = new Local();
        game = new Local();
        game.start();
    }
}
