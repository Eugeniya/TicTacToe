package com.tictactoe.field;

/**
 * Date: 24.07.13
 * Time: 15:22
 * Класс для хранение информации о ячейках игрвого поля, каждый объект данного класса
 * представялет собой ячейку игрового поля
 */
public class Cell {
    public static final char DEFAULT_CELL_VALUE = ' ';
    private char state;         //состояние клетки "Х" - крестик, "0" - нолик, " " - не занята

    public Cell(){
        state = DEFAULT_CELL_VALUE;
    }

    public void setState(char newState){
        if(newState == ' ' || newState == 'X' || newState == '0')
            state = newState;
        else
            System.out.println("Задано неверное занчение для ячейки");
    }

    public char getState(){
        return state;
    }

    public String toString(){
        return getClass().getName() + "[name=" + state + "]";
    }
}
