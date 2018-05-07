import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayDeque;

public class Robot {
	private boolean isCurious, isHungry, isInactive;
	private Energy battery;
	private int xPos, yPos;
	private Point pos;
	private ArrayList<Integer> moves;
	
	public Robot() {
		isCurious = isHungry = isInactive = false;
		battery = new Energy();
		xPos = yPos = 0;
		pos = new Point();
		moves = new ArrayList<Integer>();
	}
	
	public Robot(int x, int y) {
		isCurious = isHungry = isInactive = false;
		battery = new Energy();
		setxPos(x);
		setyPos(y);
	}
	
	public void scan() {
		Point temp = pos;
		for(int i = -40; i <= 40; i++) {
			for(int j = -40; i <= 40; i++) {
				temp.setLocation(i, j);
				//insert code that checks location for energy.
			}
		}
	}

	public void movRob() {
		int currentmov;
		int lastmov = -1;
		int left = 0;
		int right = 1;
		int up = 2;
		int down = 3;
		int diagUpLeft = 4;
		int diagUpRight = 5;
		int diagDownLeft = 6;
		int diagDownRight = 7;
		
		Random mov = new Random();
			while(getCurious()) {
				currentmov = moves.get(mov.nextInt(8));
				if(currentmov == 0 && currentmov != lastmov) {
					moveLeft();
					lastmov = right;
				}
				else if(currentmov == 1 && currentmov != lastmov) {
					moveRight();
					lastmov = left;
				}
				else if(currentmov == 2 && currentmov != lastmov) {
					moveUp();
					lastmov = down;					
				}
				else if(currentmov == 3 && currentmov != lastmov) {
					moveDown();
					lastmov = up;
				}
				else if(currentmov == 4 && currentmov != lastmov) {
					moveDiagUpLeft();
					lastmov = diagDownRight;
				}
				else if (currentmov == 5 && currentmov != lastmov) {
					moveDiagUpRight();
					lastmov = diagDownLeft;
				}
				else if (currentmov == 6 && currentmov != lastmov) {
					moveDiagDownLeft();
					lastmov = diagUpRight;
				}
				else if (currentmov == 7 && currentmov != lastmov) {
					moveDiagDownRight();
					lastmov = diagUpLeft;
				}
				
				
				scan();
				setState();
				}
			if(getInactive()) {
				
			}
				
			}
	
	public void moveLeft() {
		setxPos(getxPos() - 13);
		pos.setLocation(getxPos(), getyPos());
		battery.setEnergy(battery.getEnergy() - 13);
		
		}
		
	public void moveRight() {
			//code
		setxPos(getxPos()-13);
		pos.setLocation(getxPos(), getyPos());
		battery.setEnergy(battery.getEnergy() - 13);
		}
		
	public void moveUp() {
		setyPos(getyPos() + 13);
		pos.setLocation(getxPos(), getyPos());
		battery.setEnergy(battery.getEnergy() - 13);
		}
	private void moveDown() {
		setyPos(getyPos() - 13);
		pos.setLocation(getxPos(), getyPos() - 13);
		battery.setEnergy(battery.getEnergy() - 13);
		}
		
	public void moveDiagUpLeft(){
		Random diag = new Random();
		if(diag.nextInt(2) == 0) {
			setxPos(getxPos() - (int)(18.83*Math.cos(45)));
			setyPos(getyPos() + (int)(18.83*Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int)(18.83*Math.cos(45)));
			
		}
		else
			setxPos(getxPos() - (int)(13*Math.cos(45)));
			setyPos(getyPos() + (int)(13*Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int)(13*Math.cos(45)));
		}
		
	public void moveDiagUpRight() {
		Random diag = new Random();
		if(diag.nextInt(2) == 0) {
			setxPos(getxPos() + (int)(18.83*Math.cos(45)));
			setyPos(getyPos() + (int)(18.83*Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int)(18.83*Math.cos(45)));
			
		}
		else
			setxPos(getxPos() + (int)(13*Math.cos(45)));
			setyPos(getyPos() + (int)(13*Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int)(13*Math.cos(45)));
		}
		
	public void moveDiagDownLeft() {
		Random diag = new Random();
		if(diag.nextInt(2) == 0) {
			setxPos(getxPos() - (int)(18.83*Math.cos(45)));
			setyPos(getyPos() - (int)(18.83*Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int)(18.83*Math.cos(45)));
		}
		else
			setxPos(getxPos() - (int)(13*Math.cos(45)));
			setyPos(getyPos() - (int)(13*Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int)(13*Math.cos(45)));
		}
		
	public void moveDiagDownRight() {
		Random diag = new Random();
		if(diag.nextInt(2) == 0) {
			setxPos(getxPos() + (int)(18.83*Math.cos(45)));
			setyPos(getyPos() - (int)(18.83*Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int)(18.83*Math.cos(45)));
		}
		else
			setxPos(getxPos() + (int)(13*Math.cos(45)));
			setyPos(getyPos() - (int)(13*Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int)(13*Math.cos(45)));
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setState() {
		if(battery.getEnergy() <= 0) {
			setInactive(true);
			setHungry(false);
			setCurious(false);
		}
		else if(battery.getEnergy() < 100) {
			setHungry(true);
			setInactive(false);
			setCurious(false);
		} 
		else if(battery.getEnergy() >100)
			setCurious(true);
			setHungry(false);
			setInactive(false);
	}
	
	
	
	public String getState() {
		if(getCurious()) {
			return "Robot is curious";
		}
		else if(getHungry()) {
			return "Robot is hungry";
		}
		else
			return "Robot is inactive";
	}
	
	
	
	
	
	
	
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

	public Point getLocation() {
		return pos;
	}
	
	public void setLocation(int x, int y) {
		setyPos(y);
		setxPos(x);
		pos.setLocation(x, y);
	}

	public boolean getCurious() {
		return isCurious;
	}

	public void setCurious(boolean c) {
		this.isCurious = c;
		
		
	}

	public boolean getHungry() {
		return isHungry;
	}

	public void setHungry(boolean H) {
		this.isHungry = H;
		
	}

	public boolean getInactive() {
		return isInactive;
	}

	public void setInactive(boolean I) {
		this.isInactive = I;
		
	}
	
	
	
	
	

}
