package com.manjeet.sample.array;

/**
 * Find if there exist two elements with given difference
 * @author manjeet(minhas23@gmail.com)
 *
 */
public class ArrayWithDifferencePair {
    
    public static void main(String[] args) {
        int[] arr = {-2,4,5,11,23,45,67,68,89};
        findPairInSortedArray(arr, 9, 6);
    }
    
    
    static boolean findPairInSortedArray(int arr[], int size, int n)
    {
        // Initialize positions of two elements
        int i = 0;  
        int j = 1;
     
        // Search for a pair
        while (i<size && j<size)
        {
            if (i != j && arr[j]-arr[i] == n)
            {
                System.out.println("Pair Found:" +  arr[i] + "," + arr[j]);
                return true;
            }
            else if (arr[j]-arr[i] < n)
                j++;
            else
                i++;
        }
     
        System.out.println("No such pair");
        return false;
    }
}
