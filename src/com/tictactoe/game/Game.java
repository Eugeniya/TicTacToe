package com.tictactoe.game;

import com.tictactoe.field.Field;
import com.tictactoe.field.FieldController;
import com.tictactoe.field.FieldView;
import com.tictactoe.player.Human;

import java.util.Scanner;

/**
 * Date: 09.08.13
 * Time: 17:05
 */
public class Game {
/*
    private Human noughtsPlayer;
    private Human crossesPlayer;
*/
    private Field field;
    private FieldView fieldView;
    private FieldController fieldController;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public FieldView getFieldView() {
        return fieldView;
    }

    public void setFieldView(FieldView fieldView) {
        this.fieldView = fieldView;
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    public void setFieldController(FieldController fieldController) {
        this.fieldController = fieldController;
    }

    protected void initialization(){
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
    protected void showField(){
        for (int i = 0; i < field.getFieldSize(); i++)
        {
            for (int j = 1; j <=  field.getFieldSize(); j++)
                System.out.print("[" + (i * field.getFieldSize() + j)  + "]");
            System.out.println();
        }
    }

    public void start(){}
}
