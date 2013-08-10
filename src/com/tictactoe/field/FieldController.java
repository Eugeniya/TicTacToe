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

    //функция проверки состояния игрового поля на выигрыш
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

    //Функция вычисляющая ход компьютера, возвращает номер ячейки
    public int roundAI(char symbol){
        int indexOfCell = 0;
        //обход центральных ячеек
        for(int i = 0; i < model.getFieldSize() - 1 && indexOfCell == 0; i++)
        {
            for(int j = 1; j < model.getFieldSize() - 1; j++)
            {
                if(model.getCell(i * model.getFieldSize() + j).getState() == symbol)
                    indexOfCell = roundMiddleCell(i * model.getFieldSize() + j, symbol);
            }
        }

        //обход первого столбца
        for(int i = 0; i < model.getFieldSize() - 1 && indexOfCell == 0; i++)
        {
            if(model.getCell(i * model.getFieldSize()).getState() == symbol)
                indexOfCell = roundFirstColumn(i * model.getFieldSize(), symbol);
        }

        // обход последнего столбца
      for(int i = 0; i < model.getFieldSize() - 1 && indexOfCell == 0; i++)
        {
            if(model.getCell(i * model.getFieldSize() + model.getFieldSize() - 1).getState() == symbol)
                indexOfCell = roundLastColumn(i * model.getFieldSize() + model.getFieldSize() - 1, symbol);
        }

        //обход последеней строки
        for(int i = 0; i < model.getFieldSize() - 1 && indexOfCell == 0; i++)
        {
            if(model.getCell(model.getFieldSize()* (model.getFieldSize() - 1) + i).getState() == symbol)
                indexOfCell = roundLastLine(model.getFieldSize()* (model.getFieldSize() - 1) + i, symbol);
        }

        //если подходящий номер ячейки найти не удалось, то ходим в первую незаполненную ячейку
        if(indexOfCell == 0)
            for(int i = 0; i < model.getFieldSize(); i++)
                for (int j = 0; j < model.getFieldSize(); j++)
                    if(model.getCell(i * model.getFieldSize() + j).getState() == ' ')
                        return i * model.getFieldSize() + j + 1;

        return indexOfCell + 1;
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

    //Функция обхода для серединных ячеек
    private int roundMiddleCell(int index, char symbol){
        if(model.getCell(index + 1).getState() == ' ')
            return index + 1;

        if(model.getCell(index + model.getFieldSize() + 1).getState() == ' ')
            return index + model.getFieldSize() + 1;

        if(model.getCell(index + model.getFieldSize()).getState() == ' ')
            return index + model.getFieldSize();

        if(model.getCell(index + model.getFieldSize() - 1).getState() == ' ')
            return index + model.getFieldSize() - 1;

        return 0;
    }

    //Функция обхода для первого столбца поля
    private int roundFirstColumn(int index, char symbol){
        if(model.getCell(index + 1).getState() == ' ')
            return index + 1;

        if(model.getCell(index + model.getFieldSize() + 1).getState() == ' ')
            return index + model.getFieldSize() + 1;

        if(model.getCell(index + model.getFieldSize()).getState() == ' ')
            return index + model.getFieldSize();

        return 0;
    }

    //Функция обхода для последнего столбца поля
    private int roundLastColumn(int index, char symbol){
        if(model.getCell(index + model.getFieldSize()).getState() == ' ')
            return index + model.getFieldSize();

        if(model.getCell(index + model.getFieldSize() - 1).getState() == ' ')
            return index + model.getFieldSize() - 1;

        return 0;
    }

    //Функция обхода для последней строки поля
    private int roundLastLine(int index, char symbol){
        if(model.getCell(model.getFieldSize() * (model.getFieldSize() - 1) + index).getState() == ' ')
            return model.getFieldSize() * (model.getFieldSize() - 1) + index;

        return 0;
    }
}
