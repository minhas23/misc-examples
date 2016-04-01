package com.manjeet.sample.java.httpserver;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;



/**
 * This is used to keep track of all the connections and their state.
 *
 */
public class MyCacheManager 
{
	private static volatile MyCacheManager instance;
	
	private ConcurrentHashMap<Long, RequestInfo> requestMap = null;
	public static MyCacheManager getInstance()
	{
		if (instance == null)
		{
			synchronized (MyCacheManager.class)
			{
				if (instance == null)
					instance = new MyCacheManager();
			}
		}

		return instance;
	}

	private MyCacheManager()
	{
		requestMap = new ConcurrentHashMap<Long, RequestInfo>(10000);  // limiting max connections here.
	}
	
	public void putInCache(long timeout,long connectionID)
	{
		
		synchronized(requestMap)       // could have used reentrant locks also
		{
			if(!requestMap.contains(connectionID))
			{
				RequestInfo rInf = new RequestInfo(System.currentTimeMillis(),timeout*1000);
				requestMap.put(connectionID, rInf);
			}
		}
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

	public boolean removeConnection(long connectionID) 
	{
		RequestInfo rinfo = requestMap.remove(connectionID);
			if(rinfo == null)
			{
				// no value exist 
				return false;
			}
			else
				return true;
		
	}
}
