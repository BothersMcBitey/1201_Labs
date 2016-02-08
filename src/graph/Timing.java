package graph;
import java.util.Random;

public class Timing {

	public static void main(String[] args){		
//		Random r = new Random();
		for(int i = 12; i < 18; i++){			
			Graph graph = new Graph(i, 0.5);
//			for(int j = 0; j<i;j++) System.out.println(graph.degr/ee(j));
			int minCol = i;
			int j = 1;
			boolean found = false;
			double time = System.nanoTime();
//			while(j < i && !found){				
				Colouring col = graph.bestColouring(i);				
				if(col.cost() == 0 ){
					minCol = Math.min(minCol, col.no_colours());
					found = true;
				}
				System.out.println("noCol=" + j + " cost=" + col.cost());
//				j++;
//			}
			time = System.nanoTime() - time;
			System.out.println("size=" + i + " minCol=" + minCol + " time=" + time/Math.pow(10, 9));
		}
	}
}
