package com.manjeet.sample.array;


public class SubStringTest {
 
    public static void main(String[] args) {
       String subStr = "kkill";
       String mainStr = "mankkkillvill";
       
       System.out.println(subString(subStr, mainStr));
       
    }
    //jee   manjeet 
    public static boolean subString(String subStr, String mainStr ){
        
        int subStrLen = subStr.length();
        int mainStrLen = mainStr.length();
        
        for (int i=0 ; i <=(mainStrLen-subStrLen); i++){
            int n = subStrLen;
            int j=0;
            while (n != 0){
                if(subStr.charAt(j) == mainStr.charAt(i+j)){
                    n--;
                    j++;
                } else{
                    break;
                }
            }
            
            if(j == subStrLen){
                System.out.println("Found: value of j=" + j + " ,subStrlen=" + subStrLen );
                return true;
            }
        } 
        return false;
    }
   
}