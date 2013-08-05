package com.tictactoe.human;

import java.util.Scanner;
import com.tictactoe.field.*;


/**
 * Date: 24.07.13
 * Time: 15:38
 * Класс, описывающий игрока
 */
public class Player {
    private String name;
    private PlayerState state;

    public Player(){
        name = "Player";
        state = PlayerState.playing;
    }

    public Player(String name) {
        this.name = name;
    }

    //функция, в которой игрок делает ход, возвращает номер ячейки   numberOfCell
    public int play(){
        Scanner sc       = new Scanner(System.in);
        int numberOfCell = 0;
        boolean flag = false;

        System.out.println();
        System.out.println(this.name + "! Enter number of cell");

        //проверка ввода корректного номера ячейки
        while (!flag)
        {
            //проверка на ввод числа
            while (!sc.hasNextInt()) {
                System.out.println("Please re-enter");
                sc.nextLine();
            }
            numberOfCell = sc.nextInt();

            //проверка на ввод допустимого значения ячейки
            if(numberOfCell < 1 || numberOfCell > Field.FIELD_SIZE * Field.FIELD_SIZE)
            {
                System.out.println("Please re-enter");
                continue;
            }

            flag = true;
        }

        System.out.println("Your choice of " + numberOfCell);
        return numberOfCell;
    }

    //private boolean getInt()

    public void setName(String newName){
        if(newName.length() > 0)
            name = newName;
        else
            System.out.println("Введите корректную фамилию");
    }

    public String getName(){
        return name;
    }

    public void setState(PlayerState newState){
        state = newState;
        System.out.println(this.name + " is " + newState.name() + "!");
    }

    public PlayerState getState(){
        return state;
    }
}
