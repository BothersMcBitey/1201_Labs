package sorting;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestSort {

	static String path = "H:\\Documents\\";//"C:\\Users\\Carrum\\Documents\\";//"/home/crea1g15/TestData/";
	
	public static void main(String[] args) {
		PrintStream inOut = System.out;
		PrintStream shOut = System.out;
		PrintStream quOut = System.out;
		try {
			inOut = new PrintStream(new File(Paths.get(path + "inOut.txt").toString()));
		} catch (FileNotFoundException e) {
			try {
				Files.createFile(Paths.get(path + "inOut.txt"));
				inOut = new PrintStream(new File(path + "inOut.txt").toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			shOut = new PrintStream(new File(Paths.get(path + "shOut.txt").toString()));
		} catch (FileNotFoundException e) {
			try {
				Files.createFile(Paths.get(path + "shOut.txt"));
				shOut = new PrintStream(new File(path + "shOut.txt").toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			quOut = new PrintStream(new File(Paths.get(path + "quOut.txt").toString()));
		} catch (FileNotFoundException e) {
			try {
				Files.createFile(Paths.get(path + "quOut.txt"));
				quOut = new PrintStream(new File(path + "quOut.txt").toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		for(int j = 10; j < 1000000; j *= 2){
			int N = j;
			double[] data = new double[N];
			for (int i = 0; i < N; i++)
				data[i] = Math.random();
			double[] data1 = (double[]) data.clone();
			double[] data2 = (double[]) data.clone();
			double[] data3 = (double[]) data.clone();
			
			long time_prev = 0;
			double time = 0;
			
			for(int k = 0; k < 100; k++){
				 time_prev = System.nanoTime();
				InsertionSort(data1);
				time = (System.nanoTime() - time_prev) / 1000000000.0;
				inOut.print(time +",");
				if(j>= 156250)System.out.println("in");
				
				time_prev = System.nanoTime();
				ShellSort(data2);
				time = (System.nanoTime() - time_prev) / 1000000000.0;
				if(j>= 156250)System.out.println("sh");
		
				shOut.print(time +",");
				
				time_prev = System.nanoTime();
				Arrays.sort(data3);
				time = (System.nanoTime() - time_prev) / 1000000000.0;
				if(j>= 156250)System.out.println("qu");
				
				quOut.print(time +",");
				if(j>= 156250) System.out.println(k);
			}
			
			inOut.println();
			shOut.println();
			quOut.println();
			
			System.out.println(j);
		}
		
		inOut.close();
		shOut.close();
		quOut.close();
	}

	// I changed this as the previous code was terribly inefficient

	public static void InsertionSort(double[] data) {
		for (int i = 1; i < data.length; i++) {
			if (data[i] < data[i - 1]) {
				double temp = data[i];
				int j = i;
				do {
					data[j] = data[j - 1];
					j--;
				} while (j > 0 && data[j - 1] > temp);
				data[j] = temp;
			}
		}
	}

	public static void ShellSort(double[] a) {
		int increment = a.length / 3 + 1;

		// Sort by insertion sort at diminishing increments.
		while (increment > 1) {
			for (int start = 0; start < increment; start++)
				insertSort(a, start, increment);

			increment = increment / 3 + 1;
		}

		// Do a final pass with an increment of 1.
		// (This has to be outside the previous loop because
		// the formula above for calculating the next
		// increment will keep generating 1 repeatedly.)
		insertSort(a, 0, 1);
	}

	public static void insertSort(double[] a, int start, int increment) {
		int j, k;
		double temp;

		for (int i = start + increment; i < a.length; i += increment) {
			j = i;
			k = j - increment;
			if (a[j] < a[k]) {
				// Shift all previous entries down by the current
				// increment until the proper place is found.
				temp = a[j];
				do {
					a[j] = a[k];
					j = k;
					k = j - increment;
				} while (j != start && a[k] > temp);
				a[j] = temp;
			}
		}
	}

	public static double median(double[] a) {
		Arrays.sort(a);
		return a[a.length / 2];
	}
}
