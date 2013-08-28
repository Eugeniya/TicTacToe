package com.tictactoe.player;

import com.tictactoe.field.FieldController;

import java.util.Scanner;


/**
 * Date: 24.07.13
 * Time: 15:38
 * Класс, описывающий игрока человека
 */
public class Human extends Player{

    public Human(){
        super("Human");
    }

    public Human(String name) {
        super(name);
    }

    //функция, в которой игрок делает ход, возвращает номер ячейки   numberOfCell
    public int play(FieldController fieldController, char symbol){
        Scanner sc       = new Scanner(System.in);
        int numberOfCell = 0;

        System.out.println();
        System.out.println(this.getName() + "! Enter number of cell");

        //ввод номера ячейки
        do
        {
            //проверка ввода корректного номера ячейки
            while (!sc.hasNextInt())
            {
                System.out.println("Please re-enter");
                sc.nextLine();
            }
            numberOfCell = sc.nextInt();

            //проверка не занята ли ячейка
            if(fieldController.checkCell(numberOfCell - 1))
            {
                System.out.println("Your choice of " + numberOfCell);
                //sc.close();
                return numberOfCell;
            }

            System.out.println();
            System.out.println("Enter the number of empty cells!");

        } while (true);
    }

    public String toString(){
        return super.toString();
    }
}
