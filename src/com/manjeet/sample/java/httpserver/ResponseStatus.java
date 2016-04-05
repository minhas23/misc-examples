package com.manjeet.sample.java.httpserver;


public class ResponseStatus 
{
	public static String makeHTTPHeader(int return_code, int type) 
	{
	    String s = "HTTP/1.0 ";
	    switch (return_code) {
	      case 200:
	        s = s + "200 OK";
	        break;
	      case 400:
	        s = s + "400 Bad Request";
	        break;
	      case 403:
	        s = s + "403 Forbidden";
	        break;
	      case 404:
	        s = s + "404 Not Found";
	        break;
	      case 500:
	        s = s + "500 Internal Server Error";
	        break;
	      case 501:
	        s = s + "501 Not Implemented";
	        break;
	    }

	    s = s + "\r\n"; 
	    s = s + "Connection: close\r\n"; 
	   
	   
	    switch (type) {
	      case 0:
	        break;
	      case 1:
	        s = s + "Content-Type: application/json\r\n";
	        break;
	     
	      default:
	        s = s + "Content-Type: text/html\r\n";
	        break;
	    }

	    s = s + "\r\n"; //this marks the end of the httpheader
	    return s;
	  }
}

