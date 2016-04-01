package com.manjeet.sample.java.httpserver;

import java.io.*;
import java.net.*;
public class MyConnectionManager 
{

	/** This class implements the main server code that can handle multiple client requests.
	 * 	This handles multiple requests.
	 *  It is a http server which support GET and POST using ServerSocket. It supports following APIs
	 *  GET /sleep?timeout=20&connid=1 should sleep for 20 seconds then return a proper 200 HTTP response with a body of {“stat:”ok} (json encoded dictionary)
     *  GET /server-status should return a table that shows all the current connections and how much time they have left to sleep (so 5 seconds after I make the first sleep request, server-status would have 1 connection with 15 seconds left to sleep)
     *  POST /kill with arguments connid=1  Returns immediately, and also causes the connection with connid=X to return immediately with response {“stat”:”killed”}.
	**/

	public static void main(String[] args) throws IOException 
	{
		ServerSocket serverSocket = null;
		System.out.println ("Waiting for connection.....");

        boolean listening = true;

        try 
        {
            serverSocket = new ServerSocket(8080);
        } 
        catch (IOException e) {
            System.err.println("Could not listen on port: 8080. Assigining default");
            serverSocket = new ServerSocket(80);
        }

        while (listening)
        	new MyRequestThread(serverSocket.accept()).start(); // starts a new thread for every request.
        serverSocket.close();
    }
}
