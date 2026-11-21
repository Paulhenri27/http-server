package com.course;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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

            if(requestLine == null)
            {
                return;
            }

            String[] header = requestLine.split(" ");


            if(header.length != 3)
            {
                sendHttpResponse(out, "<div>Bad Request</div>" , "HTTP/1.1 400 BAD REQUEST");
            }

            if(header[1].equals("/"))
            {
                String body = "<div>new format</div>";
                String code = "HTTP/1.1 200 OK";
                sendHttpResponse(out,body,code);
            }
            else
            {
                String body = "<div>NOT FOUND</div>";
                String code = "HTTP/1.1 404 NOT FOUND";
                sendHttpResponse(out,body,code);
            }
            String message;
            while((message = in.readLine()) != null && !message.isEmpty())
            {
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    public void sendHttpResponse(OutputStream out , String body,String code) throws IOException
    {


        String response = code + "\r\n"+
                "Content-type: text/html\r\n"+
                "Content-length: " + body.length() + "\r\n"+
                "\r\n" +
                body;

        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);

        out.write(responseBytes);
        out.flush();
    }
}
