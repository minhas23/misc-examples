package com.manjeet.sample.java.httpserver;

import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.*;


public class MyRequestThread extends Thread
{
	private Socket socket = null;

    public MyRequestThread(Socket socket) 
    {
    	super("MyMultiServerThread");
    	this.socket = socket;
    }

    public void run() {

	try {
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
	    parseAndProcessRequest(in,out);
	   
	    out.close();
	    in.close();
	    socket.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

	private void parseAndProcessRequest(BufferedReader in, PrintWriter out) 
	{
		 try 
		 {
			String methodRequest = in.readLine();
			StringTokenizer tokenizer = new StringTokenizer(methodRequest);
			String httpMethod = tokenizer.nextToken();
			String httpQueryString = tokenizer.nextToken();
			
			if (methodRequest.startsWith("GET")) 
			{ 
		        int paramStart = methodRequest.indexOf('?');
		        long timeout = 0,connid=0;
		        if(paramStart > 0) // this is the case for GET/sleep case
		        {
		    		String[] tokens = httpQueryString.split("\\?");
					if(tokens.length > 1){
						for(String str: tokens[1].split("&")){
							if(str.contains("connid")){
								if(str.split("=").length > 1){
									connid = Long.parseLong(str.split("=")[1]);
								}
								      
							}else if(str.contains("timeout")){
								if(str.split("=").length >1 ){
									timeout = Long.parseLong(str.split("=")[1]);
								}
							}
						}
					}
		        	getSleep(out,timeout,connid);
		        }
		        else     
		        {
		        	getServerStatus(out);
		        }
		    } 
			else if (methodRequest.startsWith("POST")) 
			{ 
				System.out.println("POST Request");
				int paramStart = methodRequest.indexOf('?');
				long connid=0;
		        if(paramStart > 0) 
		        {
		        	String[] tokens = httpQueryString.split("\\?");
					if(tokens.length > 1){
						for(String str: tokens[1].split("&")){
							if(str.contains("connid")){
								if(str.split("=").length > 1){
									connid = Long.parseLong(str.split("=")[1]);
								}
							}
						}
					}
		        }
					
					Map<String,String> paramMap = new HashMap<String,String>();
					String lastParam = null;
					do {
						String currentLine = in.readLine();
						if (currentLine.indexOf("Content-Disposition: form-data") != -1) {
							String param = currentLine.split("Content-Disposition: form-data; name=")[1];
							       lastParam = param.replaceAll("\"", "");
							       StringBuilder value = new StringBuilder();
							       while (true){
							    	   currentLine = in.readLine();
							    	   if(currentLine.contains("Content-Disposition: form-data")){
							    		   continue;
							    	   } else if (currentLine.contains("WebKitFormBoundary")){
							    		   break;
							    	   } else{
							    		   value.append(currentLine);
							    	   }
							       }
							       paramMap.put(lastParam, value.toString());
							     
					 	  }
	                                             
					}while (in.ready()); //End of do-while
					if(connid == 0){
						connid = Long.parseLong(paramMap.get("connid"));
					}
		        	postKill(out,connid);
		    } 
			else
			{
				
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} //read from the stream
	}

	private void postKill(PrintWriter out, long connid) 
	{
		System.out.println("Killing connid:"+ connid);
		MyCacheManager myCM = MyCacheManager.getInstance();
		boolean isFound = myCM.removeConnection(connid);
		String httpHeader="";
		if(isFound)
		{
			httpHeader = ResponseStatus.makeHTTPHeader(200, 1);
			httpHeader = httpHeader+ "stat:ok";
		}
		else
		{
			httpHeader = ResponseStatus.makeHTTPHeader(400, 1);
			httpHeader = httpHeader+ "stat:request not found";
		}
		sendOutput(out,httpHeader);
	}

	
	private void getServerStatus(PrintWriter out) 
	{
		System.out.println("Server-status call");
		MyCacheManager myCM = MyCacheManager.getInstance();
		HashMap<Long, Long> statusTable = myCM.getStatus();
		StringBuilder sb = new StringBuilder();
		sb.append(ResponseStatus.makeHTTPHeader(200, 1));
		for(long key : statusTable.keySet())
		{
			sb.append(key);
			sb.append("=");
			sb.append(statusTable.get(key));
			sb.append("\r\n");
		}
		sendOutput(out,sb.toString());
	}

	/**
	 * This is the main worker function. It performs GET sleep operation.
	 * @param out
	 * @param timeout
	 * @param connid
	 */
	private void getSleep(PrintWriter out, long timeout, long connid) 
	{
		System.out.println("Sleeping connid="+connid + " ,timeout="+timeout);
		try 
		{
			MyCacheManager myCM = MyCacheManager.getInstance();
			myCM.putInCache(timeout, connid);
			Thread.sleep(timeout*1000);
			String header = ResponseStatus.makeHTTPHeader(200, 1);
			header = header + "stat:ok";
			System.out.println("header:" + header);
			sendOutput(out, header);
			myCM.removeConnection(connid);
			// code to return the things
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void sendOutput(PrintWriter out, String httpHeader) 
	{
		out.println(httpHeader);
		out.close();
	}

}
