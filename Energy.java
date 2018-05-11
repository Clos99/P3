import java.awt.Point;
public class Energy {
	private int energy, xPos, yPos;
	private Point position;
	private boolean found;

	public Energy() {
		energy = 200;
		xPos = yPos = 0;
		position =  new Point();
		found = false;
	}
	
	public Energy(int x, int y) {
		energy = 200;
		setxPos(x);
		setyPos(y);
		position = new Point(x,y);
		found = false;
	}
	
	public boolean isnotDepleted() {
		return energy < 0;
	}
	
	
	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setFound(boolean found) {
		this.found = found;
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
	public boolean isFound() {
		return found;
	}
	
	
	
}
