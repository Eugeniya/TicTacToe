package com.tictactoe.field;

/**
 * Date: 24.07.13
 * Time: 17:30
 * Класс view в MVC
 */
public class FieldView {
    //private static final int FIELD_SIZE = Field.FIELD_SIZE;

    public void showField(Field field){
        System.out.println();
        for (int i = 0; i < field.getFieldSize(); i++)
        {
            for (int j = 0; j < field.getFieldSize(); j++)
                System.out.print("[" + field.getCell(i * field.getFieldSize() + j).getState() + "]");
            System.out.println();
        }
    }
}
