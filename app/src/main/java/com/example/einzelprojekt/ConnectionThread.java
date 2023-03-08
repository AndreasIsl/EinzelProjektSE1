package com.example.einzelprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionThread extends Thread{
    String input;

    public ConnectionThread(String input){
        this.input = input;
    }

    public  void run() {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket();
        try {
            int result;
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(input);
            result = inFromServer.read();
            clientSocket.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
