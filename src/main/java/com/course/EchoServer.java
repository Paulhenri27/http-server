package com.course;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

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

            String[] header = requestLine.split(" ");

            Arrays.stream(header).forEach(System.out::println);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
