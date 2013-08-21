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
            try
            {
            while (!sc.hasNextInt()) {
                System.out.println("Please re-enter");
                sc.nextLine();
            }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                return;
            }

            choice = sc.nextInt();
        }

        if(choice == 3)
        {
            System.out.println("Goodbye!");
            return;
        }

        if(choice == 1)
        {
            System.out.println("Local game!");
            game = new Local();
        }

        else
        {
            choice = 0;
            System.out.println("Network Game");
            System.out.println("Make a choice");
            System.out.println("1. Server");
            System.out.println("2. Client");
            //проверка ввода корректного номера меню
            while (choice != 1 && choice != 2)
            {
                while (!sc.hasNextInt()) {
                    System.out.println("Please re-enter");
                    sc.nextLine();
                }

                choice = sc.nextInt();
            }
            if(choice == 1)
                game = new Server();
            else
            {
                System.out.println("Enter server's IP address or enter for 127.0.0.1");
                sc.nextLine();
                while (!sc.hasNextLine())
                {
                    System.out.println("Please re-enter");
                    sc.nextLine();
                }

                String IP = sc.nextLine();
                System.out.println(IP);
                game = new Client();
            }
        }

        //sc.reset();
        //sc.close();
        game.start();


        System.out.println("Goodbye!");
        return;
    }
}
