package com.tictactoe.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.tictactoe.player.*;
import com.tictactoe.field.*;


/**
 * Date: 05.08.13
 * Time: 14:55
 */
public class Server {
    private Human noughtsPlayer;
    private Human crossesPlayer;
    private Field field;
    private FieldView fieldView;
    private FieldController fieldController;

    public Server() {
        try
        {
            ServerSocket server = null;
            Socket client;

            server = new ServerSocket(1234);
            client = server.accept();
            System.out.println("Got client");

            PrintWriter out = null;
            BufferedReader in = null;

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            String input;
            while((input = in.readLine()) != null)
            {
                if(input.equalsIgnoreCase("quit"))
                    break;

                out.println("Server -> " + input + 1);
                System.out.println(input);
            }

            out.close();
            in.close();
            client.close();
            server.close();

            System.out.println("Server closed");
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

