package com.tictactoe.game;
import com.sun.media.jfxmedia.events.PlayerStateEvent;
import com.tictactoe.human.*;
import com.tictactoe.field.*;

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
        field           = new Field();
        fieldView       = new FieldView();
        fieldController = new FieldController(field,fieldView);
        //fieldController.updateField();
    }

    //функция начала игры
    public void start(){
        initialization();
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
        for(int i = 0; i < Field.FIELD_SIZE * Field.FIELD_SIZE; i++)
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

    //нумеруем ячейки, при этом данные в ячейках не изменяются
    private void initialization(){
        for (int i = 0; i < field.FIELD_SIZE; i++)
        {
            for (int j = 1; j <=  field.FIELD_SIZE; j++)
                System.out.print("[" + (i * Field.FIELD_SIZE + j)  + "]");
            System.out.println();
        }
    }
}
