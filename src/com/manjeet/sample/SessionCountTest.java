package com.manjeet.sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


 
public class SessionCountTest {
 
	static HashMap<String, Integer> userSession = new HashMap<String,Integer>();
	static HashMap<String, Long> userLastTime = new HashMap<String,Long>();
	//sed -i.bak 's/ \// /g' sample
	public static void main(String[] args) {
     
		String inputFile = "/Users/manjeet/astro-sorted";
		System.out.println("Generating..");
		
		BufferedReader br = null;
		try
		{
	        br = new BufferedReader(new FileReader(inputFile));
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(" ");
				String date = tokens[0].trim() + " " + tokens[1].trim();
				String uuid = tokens[2].trim();
				long time = getTimeFromdate(date);
				Long lastTime = userLastTime.get(uuid);
			
				if(lastTime != null && (time-lastTime) > 30*60*1000){
					int count = userSession.get(uuid);
					userSession.put(uuid, count+1);
				}else if(lastTime == null){
					userSession.put(uuid, 1);
				}
				userLastTime.put(uuid, time);
				
			}
			float sum = 0.0f;
			for (float f : userSession.values()) {
			    sum += f;
			}
			System.out.println("sessions= " + sum);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static long getTimeFromdate(String date) {
		 long time = -1L;
		 SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date result = null;
		 try {
			 result =  df.parse(date);
			 time = result.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return time;
	}
}