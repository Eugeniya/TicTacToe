package com.tictactoe.player;

import com.tictactoe.field.FieldController;

/**
 * Date: 09.08.13
 * Time: 15:55
 */
public class Computer extends Player {
    public Computer() {
        super("Computer");
    }

    public Computer(String name) {
        super(name);
    }

    //функция, в которой игрок делает ход, возвращает номер ячейки   numberOfCell
    public int play(FieldController fieldController, char symbol){
        int numberOfCell = fieldController.roundAI(symbol);
        System.out.println();
        System.out.println("Computer chose " + numberOfCell);
        return numberOfCell;
    }

    public String toString(){
        return super.toString();
    }
}
