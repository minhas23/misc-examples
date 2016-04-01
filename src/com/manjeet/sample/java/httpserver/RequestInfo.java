package com.manjeet.sample.java.httpserver;

public class RequestInfo 
{
	long insertionTime;
	public long getInsertionTime() {
		return insertionTime;
	}
	
	private long timeoutVal;
	
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
	public RequestInfo(long insertionTime, long timeoutVal) {
		super();
		this.insertionTime = insertionTime;
		this.timeoutVal = timeoutVal;
		
	}
	
	
}
