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
    //false - ячейка уже занята или индекс ячейки выходит за пределы массива
    public boolean checkCell(int indexOfCell){
       if((indexOfCell >= 0) && indexOfCell < (model.getFieldSize() * model.getFieldSize())
                && model.getCell(indexOfCell).getState() == ' ')
            return true;
        return false;
    }

    //функция проверки состояния игрового поля
    public boolean checkField(char symbol){
        for(int i = 0; i < model.getFieldSize(); i++)
        {
            if( i == 0)
            {
                if (roundRight(i, symbol))
                    return true;
                if (roundDiagonalRight(symbol))
                    return true;
            }

            if(i == model.getFieldSize() - 1)
            {
                if(roundDiagonalLeft(symbol))
                    return true;
            }

            if(roundDown(i, symbol))
                return true;
        }

        for(int i = 1; i < model.getFieldSize() ; i++)
        {
            if(roundRight(i * model.getFieldSize(), symbol))
                return true;
        }

        return false;
    }

    //Функция обхода слева направо
    private boolean roundRight(int index, char symbol){
        for(int i = index; i < index + model.getFieldSize(); i++)
        {
           if(model.getCell(i).getState() != symbol)
                return false;
        }
        return true;
    }

    //Функция обхода по диагонали вправо вниз
    private boolean roundDiagonalRight(char symbol){
        for(int i = 0; i < model.getFieldSize(); i++)
        {
            if(model.getCell(i * model.getFieldSize() + i).getState() != symbol)
                return false;
        }
        return true;
    }

    //Функция обхода по диагонали влево вниз
    private boolean roundDiagonalLeft(char symbol){
        for(int i = 1; i <= model.getFieldSize(); i++)
        {
            if(model.getCell(i * model.getFieldSize() - i).getState() != symbol)
                return false;
        }
        return true;
    }

    //Функция обхода вниз
    private boolean roundDown(int index, char symbol){
        for(int i = 0; i < model.getFieldSize(); i++)
        {
            if(model.getCell(index + i * model.getFieldSize()).getState() != symbol)
                return false;
        }
        return true;
    }
}
