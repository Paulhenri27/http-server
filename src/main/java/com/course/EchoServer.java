package com.course;

import java.io.*;
import java.net.Socket;

public class EchoServer extends Thread
{

    private final Socket socket;

    public EchoServer(Socket socket)
    {
        this.socket = socket;
    }


    @Override
    public void run()
    {
        try(socket;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream out = socket.getOutputStream())
        {
            String requestLine = in.readLine();

            System.out.println(requestLine);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
