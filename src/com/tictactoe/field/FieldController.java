package com.tictactoe.field;

/**
 * Date: 24.07.13
 * Time: 17:40
 * Класс controller в MVC
 */
public class FieldController {
    private Field model;
    public FieldView view;

    public FieldController(Field Fmodel, FieldView Fview){
        model = Fmodel;
        view  = Fview;
    }

    public char getCellState(int i){
        return model.getCell(i).getState();
    }

    public void setCellState(int indexOfCell, char newCellState){
        model.getCell(indexOfCell).setState(newCellState);
    }

    public void updateField(){
        view.showField(model);
    }

    //функция проверки ячейки
    //numberOfCell - номер ячейки
    //true - ячейка свободна и может быть выбрана
    //false - ячейка уже занята.
    public boolean checkCell(int indexOfCell){
        if(model.getCell(indexOfCell).getState() == ' ')
            return true;
        return false;
    }

    //функция проверки состояния игрового поля
    public boolean checkField(char symbol){
        for(int i = 0; i < Field.FIELD_SIZE; i++)
        {
            if( i == 0)
            {
                //System.out.println(i);
                if (roundRight(i, symbol))
                    return true;
                if (roundDiagonalRight(symbol))
                    return true;
            }

            if(i == Field.FIELD_SIZE - 1)
            {
                //System.out.println(i);
                if(roundDiagonalLeft(symbol))
                    return true;
            }

            //System.out.println(i);
            if(roundDown(i, symbol))
                return true;
        }

        for(int i = 1; i < Field.FIELD_SIZE ; i++)
        {
            //System.out.println(i * Field.FIELD_SIZE);
            if(roundRight(i * Field.FIELD_SIZE, symbol))
                return true;
        }

        return false;
    }

    //Функция обхода слева направо
    private boolean roundRight(int index, char symbol){
        //System.out.println("направо");
        for(int i = index; i < index + Field.FIELD_SIZE; i++)
        {
            //System.out.println(i);
            if(model.getCell(i).getState() != symbol)
                return false;
        }
        return true;
    }

    //Функция обхода по диагонали вправо вниз
    private boolean roundDiagonalRight(char symbol){
        //System.out.println("по диагонали вправо вниз");
        for(int i = 0; i < Field.FIELD_SIZE; i++)
        {
            //System.out.println(i * Field.FIELD_SIZE + i);
            if(model.getCell(i * Field.FIELD_SIZE + i).getState() != symbol)
                return false;
        }
        return true;
    }

    //Функция обхода по диагонали влево вниз
    private boolean roundDiagonalLeft(char symbol){
        //System.out.println("по диагонали влево вниз");
        for(int i = 1; i <= Field.FIELD_SIZE; i++)
        {
            //System.out.println(i * Field.FIELD_SIZE - i);
            if(model.getCell(i * Field.FIELD_SIZE - i).getState() != symbol)
                return false;
        }
        return true;
    }

    //Функция обхода вниз
    private boolean roundDown(int index, char symbol){
        //System.out.println("вниз");
        for(int i = 0; i < Field.FIELD_SIZE; i++)
        {
            //System.out.println(index + i * Field.FIELD_SIZE);
            if(model.getCell(index + i * Field.FIELD_SIZE).getState() != symbol)
                return false;
        }
        return true;
    }
}
