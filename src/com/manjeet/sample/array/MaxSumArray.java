package com.manjeet.sample.array;

public class MaxSumArray {
	
	public static void main(String[] args) {
		int[] array = {-1,-2,3,-45,-6,-7,-1,-9};
		getMaxSumArray(array);
	}
	
	/**
	 * Initialize maxSum and currentSum to array[0]
	 * this handles the case if all the elements are negatives also
	 * @param array
	 * @return
	 */
	public static int getMaxSumArray(int[] array){
		
		int max = array[0];
		int sum = array[0];
		
		int start = 0;
		int end = -1;
		for (int i = 1; i < array.length; i++){
			sum = Math.max(array[i], sum + array[i]);
			max = Math.max(max, sum);
			
		}
		System.out.println("maxSum =" + max + ", start=" + start + " , end="+ end);
		return max;
	}

}
