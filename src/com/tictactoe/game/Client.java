package com.tictactoe.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Date: 05.08.13
 * Time: 15:07
 */
public class Client {
    public Client() {
        try
        {
            Socket clientSocket = null;
            clientSocket = new Socket("127.0.0.1", 1234);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));

            String user, server;

            System.out.println("Server> ");
            while ((user = inu.readLine()) != null)
            {
                out.println(user);
                server = in.readLine();
                System.out.println(server);
                if(user.equalsIgnoreCase("quit"))
                    break;
            }

            out.close();
            in.close();
            inu.close();
            clientSocket.close();
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
