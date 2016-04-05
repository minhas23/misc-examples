package com.manjeet.sample.java.httpserver;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MyCacheManager 
{
	private static  MyCacheManager instance = new MyCacheManager();
	
	private ConcurrentHashMap<Long, RequestInfo> requestMap = null;
	public static MyCacheManager getInstance()
	{
		return instance;
	}

	private MyCacheManager()
	{
		requestMap = new ConcurrentHashMap<Long, RequestInfo>(10000);  
	}
	
	public  boolean  putInCache(Long connectionID,long timeout, Thread t)
	{
            
			if(requestMap.get(connectionID) == null)
			{
				RequestInfo rInf = new RequestInfo(System.currentTimeMillis(),timeout*1000, t);
				requestMap.put(connectionID, rInf);
				return true;
			}
			return false;
	}

	public HashMap<Long, Long> getStatus() 
	{
		HashMap<Long, Long> outTable = new HashMap<Long, Long>();
		for(Long key : requestMap.keySet())
		{
			RequestInfo rInfoObj = requestMap.get(key); 
			long insertionTime = rInfoObj.getInsertionTime();
			long timeOut = rInfoObj.getTimeoutVal();
			long currentTime = System.currentTimeMillis();
			long timeLeft = insertionTime+timeOut - currentTime;
			if(timeLeft > 0)
			{
				outTable.put(key, timeLeft/1000);
			}
		}
		return outTable;
	}

	public boolean removeConnection(Long connectionID) 
	{
		RequestInfo rinfo = requestMap.remove(connectionID);
			if(rinfo == null)
			{
				// no value exist 
				return false;
			}
			else{
				rinfo.getThread().interrupt();
				return true;
			}
				
		
	}
}
