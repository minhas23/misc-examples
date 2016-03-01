package com.manjeet.sample.algorithm;

/**
 * Given a number line from -infinity to +infinity. You start at 0 and can go either to the left or to the right. The condition is that in i’th move, you take i steps.
 *  a) Find if you can reach a given number x
 *  b) Find the most optimal way to reach a given number x, if we can indeed reach it. For example, 3 can be reached om 2 steps, (0, 1) (1, 3) and 4 can be reached in 3 steps (0, -1), (-1, 1) (1, 4).
 * @author manjeet
 *
 */
public class MinStepsToDestination {
	
	// Function to count number of steps required to reach a
	// destination.
	// source -> source vertex
	// step -> value of last step taken
	// dest -> destination vertex
	static int steps(int source, int step, int dest)
	{
	    // base cases
	    if (Math.abs(source) > Math.abs(dest))  return Integer.MAX_VALUE;
	    if (source == dest)     return step;
	 
	    // at each point we can go either way
	 
	    // if we go on positive side
	    int pos = steps(source+step+1, step+1, dest);
	 
	    // if we go on negative side
	    int neg = steps(source-step-1, step+1, dest);
	 
	    // minimum of both cases
	    return Integer.min(pos, neg);
	}
	 
	// Driver Program
	public static void main(String[] args)
	{
	    int dest = 3;
	    System.out.println("No. of steps required to reach " +dest +" is "+ steps(0, 0, dest));
	}

}
