package com.tictactoe.field;

/**
 * Date: 24.07.13
 * Time: 16:35
 * Класс, описывающий игровое поле.
 * Является model в MVC
 */
public class Field {
    public static final int FIELD_SIZE = 4;
    private Cell[] cells;

    public Field(){
        cells = new Cell[FIELD_SIZE * FIELD_SIZE];
        for(int i = 0 ; i < cells.length; i++)
            cells[i] = new Cell();
    }

    public Cell getCell(int i){
        return cells[i];
    }

    public void setCell(int i, Cell newCell){
        cells[i] = newCell;
    }
}
