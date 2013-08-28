package com.tictactoe.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.tictactoe.player.*;
import com.tictactoe.field.*;


/**
 * Date: 05.08.13
 * Time: 14:55
 * серверная часть сетевой игры
 */
public class Server extends Game{
    private Human noughtsPlayer;
    private Human crossesPlayer;
    public static final int PORT = 1235;
    private ServerSocket server = null;
    private Socket client;

    public Server() {
        noughtsPlayer = new Human("Nought");
        crossesPlayer = new Human("Crosses");
    }

    public void start(){
        PrintWriter out   = null;
        BufferedReader in = null;
        int numberOfCell  = 0;

        //создаем сервер
        try
        {
            server = new ServerSocket(PORT);
            System.out.println("Server is created");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }

        //задаем параметры игрового поля
        System.out.println();
        System.out.println("Enter the size of the field (3 by default).");
        System.out.println("Enter a value between 3 and 10");

        initialization();
        showField();

        //ожидаем клиента
        System.out.println("Waiting");
        try
        {
            client = server.accept();
            System.out.println();
            System.out.println("Got client");
            in  = new BufferedReader(new InputStreamReader(client.getInputStream()));   //входной поток (клиент сервер)
            out = new PrintWriter(client.getOutputStream(), true);      //исходящий поток (сервер клиент)
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }

        //отправляем клиенту размер игрвоого поля
        System.out.println();
        System.out.println("Send size " + this.getField().getFieldSize());
        out.println(this.getField().getFieldSize());
        System.out.println();
        System.out.println("Waiting...");

        String input, output;
        int count = 0;

        //делаем количество шагов равное количеству ячеек, если в процессе
        //один из игроков выигрывает checkField() - возвращает истину, игра прекращается
        //for(int i = 0; i < getField().getFieldSize() *  getField().getFieldSize(); i++)
        //{
        try
        {
            while((input = in.readLine()) != null && count < getField().getFieldSize() * getField().getFieldSize())
            {
                numberOfCell = Integer.parseInt(input);

                System.out.println();
                System.out.println("Crosses playing");

                //обновлем игровое поля
                getFieldController().setCellState(numberOfCell - 1, 'X');
                getFieldController().updateField();

                count ++;
                //проверяем победу
                if (getFieldController().checkField('X') || count == getField().getFieldSize() * getField().getFieldSize() - 1)
                {
                    crossesPlayer.setState(PlayerState.winner);
                    noughtsPlayer.setState(PlayerState.loser);
                    break;
                }

                numberOfCell = noughtsPlayer.play(getFieldController() , '0');
                getFieldController().setCellState(numberOfCell - 1, '0');
                getFieldController().updateField();

                output = Integer.toString(numberOfCell);
                out.println(output);

                if(getFieldController().checkField('0'))
                {
                    noughtsPlayer.setState(PlayerState.winner);
                    crossesPlayer.setState(PlayerState.loser);
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }

        if(crossesPlayer.getState() == PlayerState.playing)
            System.out.println("Draw!");

        try
        {
            out.close();
            in.close();
            client.close();
            server.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
    }

    //функция хода игрока
    // true если в результате хода игрок выигарл
    // false если в результате хода игрок проиграл
    private boolean makeMove(Player player, char symbol){
        int numberOfCell = 0;

        numberOfCell = player.play(getFieldController() , symbol);

        getFieldController().setCellState(numberOfCell - 1, symbol);
        getFieldController().updateField();
        if (getFieldController().checkField(symbol))
        {
            player.setState(PlayerState.winner);
            return true;
        }

        return false;
    }

    public String toString(){
        return getClass().getName();
    }

}

