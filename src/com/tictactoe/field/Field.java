package com.tictactoe.field;

import java.util.ArrayList;

/**
 * Date: 24.07.13
 * Time: 16:35
 * Класс, описывающий игровое поле.
 * Является model в MVC
 */
public class Field {
    public static final int MINIMUM_FIELD_SIZE = 3;
    public static final int MAXIMUM_FIELD_SIZE = 20;
    public static final int DEFAULT_FIELD_SIZE = 3;
    private final int fieldSize;
    private Cell[] cells;
    public ArrayList<Integer> history;

    public Field(){
        this(DEFAULT_FIELD_SIZE);
    }

    public Field(int fieldSize) {
        if(fieldSize < MINIMUM_FIELD_SIZE || fieldSize > MAXIMUM_FIELD_SIZE)
            throw new RuntimeException("Illegal value of field's size.");

        this.fieldSize = fieldSize;

        cells = new Cell[fieldSize * fieldSize];
        for(int i = 0 ; i < cells.length; i++)
            cells[i] = new Cell();

        history = new ArrayList<Integer>();
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Cell getCell(int i){
        return cells[i];
    }

    public void setCell(int i, Cell newCell){
        cells[i] = newCell;
    }

    public String toString(){
        return getClass().getName();
    }
}
