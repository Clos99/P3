import java.util.ArrayList;
import java.util.Random;
public class P3 {
	private ArrayList<Energy> containers;
	
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
		Random loc = new Random();
		e.setLocation(loc.nextInt(200) - 100, loc.nextInt(200) - 100);
	}
	public static void main(String [ ] args) {
		Robot lifo;
		Robot fifo;
		P3 lifoproject = new P3();
		P3 fifoproject = new P3();
		Sample lif = new Sample();
		Sample fif = new Sample();
		
		for(int i = 0; i < 1000; i++) {
			 lifo = new Robot();
			 lifoproject = new P3();
			 while(!lifo.getInactive()) {
				 if(lifo.getCurious()) {
					 lifo.curiousMove(lifoproject.containers);
				 }
				 if(lifo.getHungry()) {
					 lifo.lifohungryMove(lifoproject.containers);
				 }
				 //System.out.println(lifo.getState());
			 }
			 lif.addData((double)lifo.gettotalMoves());
			 System.out.println("Lifo Robot # " + i + " took a made a total of " + lifo.gettotalMoves() + " moves");
		}
		
		
		for(int i = 0; i < 1000; i++) {
			fifo = new Robot();
			fifoproject = new P3();
			while(!fifo.getInactive()) {
			if(fifo.getCurious()) {
				fifo.curiousMove(fifoproject.containers);
			}
			if(fifo.getHungry()) {
				fifo.fifohungryMove(fifoproject.containers);
			}
			//System.out.println(fifo.getState());
			}
			fif.addData((double) fifo.gettotalMoves()); 
			System.out.println("Fifo Robot #" + i + " took a made a total of " + fifo.gettotalMoves() + " moves");
		}
		
		lif.computeStats();
		System.out.println("\nLifo Robots sample data: " +lif.toString() + "\n");
		
		fif.computeStats();
		System.out.println("\nFifo Robot's sample data :" + fif.toString());
		
	}

}
