package com.course;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        try(ServerSocket serverSocket = new ServerSocket(8080))
        {
            try(Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream()))
            {
                out.println("Hello from server");
            }
            catch(IOException e)
            {
               System.err.println("Error: " + e.getMessage());
            }
        }

    }
}