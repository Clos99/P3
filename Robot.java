
public class Robot {
	private boolean isCurious, isHungry, isInactive;
	private Energy battery;
	private int xPos, yPos;
	
	public Robot() {
		isCurious = isHungry = isInactive = false;
		battery = new Energy();
		xPos = yPos = 0;
	}
	
	public void setState() {
		if(battery.getEnergy() <= 0)
			setInactive(true);
		else if(battery.getEnergy() < 100) {
			setHungry(true);
		} 
		else if(battery.getEnergy() >100)
			setCurious(true);
	}
	
	
	
	
	
	
	
	
	//getters + setters
	public Energy getBattery() {
		return battery;
	}


	public void setBattery(Energy battery) {
		this.battery = battery;
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


	

	public boolean isCurious() {
		return isCurious;
	}

	public void setCurious(boolean c) {
		this.isCurious = c;
		this.isHungry = this.isInactive = !c;
		
	}

	public boolean isHungry() {
		return isHungry;
	}

	public void setHungry(boolean H) {
		this.isHungry = H;
		this.isCurious = this.isInactive = !H;
	}

	public boolean isInactive() {
		return isInactive;
	}

	public void setInactive(boolean I) {
		this.isInactive = I;
		this.isHungry = this.isCurious = !I;
	}
	
	
	
	
	

}
