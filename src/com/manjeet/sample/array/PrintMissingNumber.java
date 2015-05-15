package com.manjeet.sample.array;

import java.util.Arrays;


public class PrintMissingNumber {
    
    public static void main(String[] args) {
        
        int arr[] = {88, 105, 3, 2, 200, 0, 10};
        int limit = 100;
        printMissing(arr, limit);
    }

 // A O(n) function to print missing elements in an array
   static void printMissing(int arr[], int limit)
    {
        // Initialize all number from 0 to 99 as NOT seen
        Boolean[] seen = new Boolean[limit];
        Arrays.fill(seen, Boolean.FALSE);
        int n = arr.length;
     
        // Mark present elements in range [0-99] as seen
        for (int i=0; i< n; i++)
          if (arr[i] < limit)
           seen[arr[i]] = true;
     
        // Print missing element
        int i = 0;
        while (i < limit)
        {
            // If i is missing
            if (seen[i] == false)
            {
                // Find if there are more missing elements after i
                int j = i+1;
                while (j < limit && seen[j] == false)
                      j++;
     
                // Print missing single or range
                if(i+1 == j){
                    System.out.format("%d\n", i);
                }else{
                    System.out.format("%d-%d\n", i, j-1);
                }
     
                // Update u
                i = j;
            }
            else{
                i++;
            }
        }
    }
}
