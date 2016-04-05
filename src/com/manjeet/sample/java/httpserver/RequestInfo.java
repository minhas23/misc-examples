package com.manjeet.sample.java.httpserver;

public class RequestInfo 
{
	long insertionTime;
	private long timeoutVal;
	private Thread thread;
	
	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public long getInsertionTime() {
		return insertionTime;
	}
	
	public long getTimeoutVal() {
		return timeoutVal;
	}
	public void setTimeoutVal(long timeoutVal) {
		this.timeoutVal = timeoutVal;
	}
	
	public void setInsertionTime(long insertionTime) {
		this.insertionTime = insertionTime;
	}
	public RequestInfo() {
		super();
		timeoutVal = 0;
		insertionTime=0;
	}
	public RequestInfo(long insertionTime, long timeoutVal, Thread t) {
		super();
		this.insertionTime = insertionTime;
		this.timeoutVal = timeoutVal;
		this.thread = t;
		
	}
	
	
}
