package com.example.einzelprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionThread extends Thread{
    String input;
    String result;

    public ConnectionThread(String input){
        this.input = input;
    }

    public String getResult() {
        return result;
    }

    public  void run() {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        try {


            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(input +'\n');
            result = inFromServer.readLine();
            clientSocket.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
}
