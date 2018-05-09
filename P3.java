import java.lang.Object;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;
public class P3 {
	private ArrayList<Energy> containers;

	//private boolean movLeft, movRight, movUp, movDown, movDUR, movDUL, movDDL, movDDR;
	
	public P3() {
		containers = new ArrayList<Energy>();
		
		
		for(int i = 0; i < 8; i++) {
			
		}
		//creates 30 energy containers
		for(int i = 0; i < 30; i++) {
			containers.add(new Energy());
			insert(containers.get(i));
	
			
	}
}

	public void insert(Energy e) {
		int x,y;
		Random loc = new Random();
		e.setLocation(loc.nextInt(200) - 100, loc.nextInt(200) - 100);
	}
	public static void main(String [ ] args) {
		Robot lifo;
		Robot fifo;
		P3 lifoproject = new P3();
		P3 fifoproject = new P3();
		Sample lif = new Sample();
		
		for(int i = 0; i < 1000; i++) {
			 lifo = new Robot(); 
			 while(!lifo.getInactive()) {
				 if(lifo.getCurious()) {
					 lifo.curiousMove(lifoproject.containers);
				 }
				 if(lifo.getHungry()) {
					 lifo.curiousMove(lifoproject.containers);
				 }
				 lifo.checkState();
			 }
		}
		for(int i = 0; i < 1000; i++) {
			fifo = new Robot(); 
			while(!fifo.getInactive()) {
			if(fifo.getCurious()) {
				fifo.curiousMove(fifoproject.containers);
			}
			if(fifo.getHungry()) {
				fifo.curiousMove(fifoproject.containers);
			}
			fifo.checkState();
			}
		}
		
	}

}
