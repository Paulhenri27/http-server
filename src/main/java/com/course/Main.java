package com.course;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        try(ServerSocket serverSocket = new ServerSocket(8080))
        {
            while(true)
            {
                Socket socket = serverSocket.accept();
                EchoServer echoServer = new EchoServer(socket);
                echoServer.start();
            }
        }

    }
}