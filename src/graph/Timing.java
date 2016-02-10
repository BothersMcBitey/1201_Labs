package graph;
import java.util.Arrays;
import java.util.Random;

public class Timing {

	public static void main(String[] args){
		for(int i = 12; i < 18; i++){		
			double[] time = new double[20];
			for(int j = 0; j < 20; j++){
				Graph graph = new Graph(i, 0.5);
				int minCol = i;
				boolean found = false;
				time[j] = System.nanoTime();			
				Colouring col = graph.bestColouring(3);				
				if(col.cost() == 0 ){
					minCol = Math.min(minCol, col.no_colours());
					found = true;
				}
				time[j] = (System.nanoTime() - time[j])/1000000000;
			}
			System.out.println(i + "=" + median(time));
		}
	}
	
	public static double median(double[] a) {
		Arrays.sort(a);
		return a[a.length / 2];
	}
}
