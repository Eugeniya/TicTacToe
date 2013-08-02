package com.tictactoe.game;
//import com.sun.media.jfxmedia.events.PlayerStateEvent;
import com.tictactoe.human.*;
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
public class Game {
    private Player noughtsPlayer;
    private Player crossesPlayer;
    private Field field;
    private FieldView fieldView;
    private FieldController fieldController;

    public Game() {
        crossesPlayer   = new Player("Player 1");
        noughtsPlayer   = new Player("Player 2");
//        field           = new Field();
//        fieldView       = new FieldView();
//        fieldController = new FieldController(field,fieldView);
        //fieldController.updateField();
    }

    //функция начала игры
    public void start(){
        System.out.println("Enter the size of the field (3 by default).");
        System.out.println("Enter a value between 3 and 10");

        initialization();
        showField();
        int numberOfCell = 0;

        System.out.println();
        System.out.println("Player " + crossesPlayer.getName() + " places a cross.");
        System.out.println("Player " + noughtsPlayer.getName() + " places a toe.");

        //устанавливаем состояние игроков
        System.out.println();
        crossesPlayer.setState(PlayerState.playing);
        noughtsPlayer.setState(PlayerState.playing);

        //делаем количество шагов равное количеству ячеек, если в процессе
        //один из игроков выигрывает checkField() - возвращает истину, игра прекращается
        for(int i = 0; i < field.getFieldSize() * field.getFieldSize(); i++)
        {
            //четные ходы играет Крестик, нечетные - Нолик
            if(i%2 == 0)
            {
                numberOfCell = crossesPlayer.play();
                while (!fieldController.checkCell(numberOfCell - 1))
                {
                    System.out.println();
                    System.out.println("Enter the number of empty cells!");
                    numberOfCell = crossesPlayer.play();
                }

                fieldController.setCellState(numberOfCell - 1, 'X');
                fieldController.updateField();
                if (fieldController.checkField('X'))
                {
                    crossesPlayer.setState(PlayerState.winner);
                    noughtsPlayer.setState(PlayerState.loser);
                    break;
                }
            }
            else
            {
                numberOfCell = noughtsPlayer.play();
                while (!fieldController.checkCell(numberOfCell - 1))
                {
                    System.out.println();
                    System.out.println("Enter the number of empty cells!");
                    numberOfCell = noughtsPlayer.play();
                }

                fieldController.setCellState(numberOfCell - 1, '0');
                fieldController.updateField();
                if (fieldController.checkField('0'))
                {
                    noughtsPlayer.setState(PlayerState.winner);
                    crossesPlayer.setState(PlayerState.loser);
                    break;
                }
            }
        }

        if(crossesPlayer.getState() == PlayerState.playing)
            System.out.println("Draw!");
    }

    private void initialization(){
        Scanner sc       = new Scanner(System.in);
        boolean flag     = false;
        int sizeOfField  = 0;

        //проверка ввода корректного размера поля
        while (!flag)
        {
            while (!sc.hasNextInt())
            {
                System.out.println();
                System.out.println("Please re-enter");
                sc.nextLine();
            }
            sizeOfField = sc.nextInt();

            if(sizeOfField < Field.MINIMUM_FIELD_SIZE || sizeOfField > Field.MAXIMUM_FIELD_SIZE)
            {
                System.out.println("Illegal value of field's size.");
                continue;
            }

            flag = true;
        }

        field           = new Field(sizeOfField);
        fieldView       = new FieldView();
        fieldController = new FieldController(field,fieldView);
    }

    //нумеруем ячейки, при этом данные в ячейках не изменяются
    private void showField(){
        for (int i = 0; i < field.getFieldSize(); i++)
        {
            for (int j = 1; j <=  field.getFieldSize(); j++)
                System.out.print("[" + (i * field.getFieldSize() + j)  + "]");
            System.out.println();
        }
    }
}
