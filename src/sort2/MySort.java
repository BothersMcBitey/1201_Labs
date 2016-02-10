package sort2;

import java.io.*;
import java.util.*;

public class MySort
{
    public static void main(String [] args ){
    	double[] time = new double[100];
    	int N = Integer.parseInt(args[0]);
    	for(int i =0; i<100; i++){		
			double[] data = new double[N];
			for (int j=0; j<N; j++)
			    data[i] = Math.random();
			time[i] = System.nanoTime();
			Arrays.sort(data);
			time[i] = (System.nanoTime() - time[i])/1000000000;
    	}
    	System.out.println(median(time));
    }
    
    public static double median(double[] a) {
		Arrays.sort(a);
		return a[a.length / 2];
	}
}