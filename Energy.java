
public class Energy {
	private double energy;
	private int xPos, yPos;

	public Energy() {
		energy = 200;
		xPos = yPos = 0;
	}
	
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void setEnergy(double e) {
		energy = e;
	}
	
	public double getEnergy() {
		return this.energy;
	}
	
}
