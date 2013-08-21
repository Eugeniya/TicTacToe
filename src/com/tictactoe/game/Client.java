package com.tictactoe.game;

import com.tictactoe.field.Field;
import com.tictactoe.field.FieldController;
import com.tictactoe.field.FieldView;
import com.tictactoe.player.Human;
import com.tictactoe.player.Player;
import com.tictactoe.player.PlayerState;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Date: 05.08.13
 * Time: 15:07
 */
public class Client extends Game{
    private Human noughtsPlayer;
    private Human crossesPlayer;
    private static final int PORT = 4321;
    private Socket clientSocket   = null;

    private String IP = "";

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Client(){
        this("127.0.0.1");
    }

    public Client(String IP) {
        this.IP       = IP;
        noughtsPlayer = new Human("Nought");
        crossesPlayer = new Human("Crosses");
    }

    public void start(){
        PrintWriter out   = null;
        BufferedReader in = null;
        //Scanner sc        = null;
        String input, output;
        int size          = 0;
        int numberOfCell  = 0;

        try
        {
            clientSocket = new Socket(IP, Server.PORT );
            System.out.println("Connected to  " + clientSocket.getInetAddress());

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            //получаем размер поля
            size = Integer.parseInt(in.readLine());
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println();
        System.out.println("got size " + size);
        initialization(size);
        System.out.println();
        showField();

        try
        {
            while(true)
            {
                //отправляем ход игрока на сервер
                numberOfCell = crossesPlayer.play(getFieldController() , 'X');
                getFieldController().setCellState(numberOfCell - 1, 'X');
                getFieldController().updateField();
                output = Integer.toString(numberOfCell);
                out.println(output);
                //проверяем победу
                if (getFieldController().checkField('X'))
                {
                    crossesPlayer.setState(PlayerState.winner);
                    noughtsPlayer.setState(PlayerState.loser);
                    return;
                }

                //читаем ответ игрока сервера
                input = in.readLine();
                numberOfCell = Integer.parseInt(input);

                System.out.println();
                System.out.println("Nought playing");
                getFieldController().setCellState(numberOfCell - 1, '0');
                getFieldController().updateField();

                if (getFieldController().checkField('X'))
                {
                    crossesPlayer.setState(PlayerState.winner);
                    noughtsPlayer.setState(PlayerState.loser);
                    return;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //return;
        }

        try
        {
            out.close();
            in.close();
            clientSocket.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    private void initialization(int size){
        this.setField(new Field(size));
        this.setFieldView(new FieldView());
        this.setFieldController( new FieldController(getField(), getFieldView()));
    }

    //функция хода игрока
    // true если в результате хода игрок выигарл
    // false если в результате хода игрок проиграл
/*    private boolean makeMove(Player player, char symbol){
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
    }*/

}
