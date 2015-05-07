package com.manjeet.sample;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;


public class WineTasting {
    
    private static HashMap<String,HashSet<String>> wineChoiceMap = new HashMap<String,HashSet<String>>();
    private static HashMap<String,ArrayList<String>> personWineListFinalResult = new HashMap<String,ArrayList<String>>();

    public static void main(String[] args) throws Exception {
        // We can take the other files as an arguments
        String inFile = "/Users/manjeet/Desktop/person_wine_3.txt";
        String outFile = "/Users/manjeet/Desktop/output.txt";
       
        // If we have more number of files, then we can call createMap for all the files to create global map
        createMap(inFile);
        int numWineSold = processMap();
        generateOutput(numWineSold, outFile);
        
        System.out.println("Completed...");
    }
    
    
    private static void createMap(String inFile) throws Exception {
        System.out.println("Creating Map...");
        File file = new File(inFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
       
        String datarow;
        while((datarow = br.readLine()) != null){
            String[] tokens = datarow.split("\t");
            String name = tokens[0];
            String wine = tokens[1];
            
            if(!wineChoiceMap.containsKey(wine)){
                wineChoiceMap.put(wine, new HashSet<String>());
            }
            wineChoiceMap.get(wine).add(name);
        }
        br.close();
    }

    private static int processMap() {
        System.out.println("Processing Map...");
        int numWineSold = 0;
        Iterator<Entry<String, HashSet<String>>> itr = wineChoiceMap.entrySet().iterator();
        while(itr.hasNext()){
            
            Entry<String, HashSet<String>> entry = (Entry<String, HashSet<String>>)itr.next();
            String wine = entry.getKey();
            HashSet<String> personList = entry.getValue();
            for(String person : personList){
                if(!personWineListFinalResult.containsKey(person)){
                    personWineListFinalResult.put(person, new ArrayList<String>());
                }
                if(personWineListFinalResult.get(person).size() < 3){
                    personWineListFinalResult.get(person).add(wine);
                    numWineSold++;
                    break;
                }
            }
        }
        return numWineSold;
    }

    private static void generateOutput(int numWineSold, String outFile) throws Exception {
        System.out.println("Generating output...");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        bw.write(String.valueOf(numWineSold));
        bw.newLine();
        for(String person: personWineListFinalResult.keySet()){
            for(String wines: personWineListFinalResult.get(person)){
                bw.write(person + "\t" + wines);
                bw.newLine();
            }
        }
        bw.close();
    }
    
}