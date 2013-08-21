package com.tictactoe.game;
import com.tictactoe.player.*;
import com.tictactoe.field.*;

import java.util.Scanner;

//перечисление сотояний игры
/*enum GameState {

} */
/**
 * Date: 24.07.13
 * Time: 18:01
 * Класс, реализующий логику игры
 */
public class Local extends Game{
    private Player noughtsPlayer;
    private Human crossesPlayer;

    public Local() {
        crossesPlayer   = new Human("Crosses");
    }

    //функция начала игры
    public void start(){
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("Make a choice");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
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
            System.out.println();
            System.out.println(" Human vs Human!");
            noughtsPlayer = new Human("Noughts");
        }

        if(choice == 2)
        {
            System.out.println();
            System.out.println("Human vs Computer!");
            noughtsPlayer = new Computer("Computer");
        }

        if(choice == 3)
        {
            System.out.println();
            System.out.println("Goodbye!");
            return;
        }


        System.out.println("Enter the size of the field (3 by default).");
        System.out.println("Enter a value between 3 and 10");

        initialization();
        showField();

        System.out.println();
        System.out.println("Player " + crossesPlayer.getName() + " places a cross.");
        System.out.println("Player " + noughtsPlayer.getName() + " places a noughts.");

        //устанавливаем состояние игроков
        System.out.println();
        crossesPlayer.setState(PlayerState.playing);
        noughtsPlayer.setState(PlayerState.playing);

        //делаем количество шагов равное количеству ячеек, если в процессе
        //один из игроков выигрывает checkField() - возвращает истину, игра прекращается
        for(int i = 0; i < getField().getFieldSize() *  getField().getFieldSize(); i++)
        {
            //четные ходы играет Крестик, нечетные - Нолик
            if(i%2 == 0)
            {
                if(makeMove(crossesPlayer, 'X'))
                {
                    noughtsPlayer.setState(PlayerState.loser);
                    break;
                }
            }
            else
            {
                if(makeMove(noughtsPlayer, '0'))
                {
                    crossesPlayer.setState(PlayerState.loser);
                    break;
                }
            }
        }

        if(crossesPlayer.getState() == PlayerState.playing)
            System.out.println("Draw!");

        //sc.close();
    }

    //функция хода игрока
    // true если в результате хода игрок выигарл
    // false если в результате хода игрок проиграл
    private boolean makeMove(Player player, char symbol){
        int numberOfCell = 0;

        numberOfCell = player.play(getFieldController() , symbol);

        getFieldController().setCellState(numberOfCell - 1, symbol);
        getFieldController().updateField();
        if (getFieldController().checkField(symbol))
        {
            player.setState(PlayerState.winner);
            return true;
        }

        return false;
    }
}
