import java.awt.Point;
public class Energy {
	private int energy;
	private int xPos, yPos;
	private Point position;

	public Energy() {
		energy = 200;
		xPos = yPos = 0;
		position =  new Point();
	}
	
	public Energy(int x, int y) {
		energy = 200;
		
		position = new Point(x,y);
	}
	
	public void setEnergy(int e) {
		energy = e;
	}
	
	public void setLocation(int x, int y) {//not sure if we need this
		position.setLocation(x, y);
	}
	
	public Point getLocation() {
		return position;
	}

	public int getEnergy() {
		
		return energy;
	}
	
}
