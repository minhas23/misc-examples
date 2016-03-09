package com.manjeet.sample.array;

public class MaxSumArray {
	
	public static void main(String[] args) {
		int[] array = {-1,-2,3,5,-6,1,-1,-9};
		getMaxSumArray(array);
	}
	
	/**
	 * Initialize maxSum and currentSum to array[0]
	 * this handles the case if all the elements are negatives also
	 * @param array
	 * @return
	 */
	public static int getMaxSumArray(int[] array){
		
		int max = 0;
		int sum = 0;

		for (int i = 0; i < array.length; i++){
	        sum = sum + array[i];
	        if (sum < 0)
	            sum = 0;
	        if (max < sum)
	            max = sum;
	    }
		System.out.println(max);
		return max;
	}

}
