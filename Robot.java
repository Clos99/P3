import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayDeque;

public class Robot {
	private boolean isCurious, isHungry, isInactive;
	private Energy battery;
	private int xPos, yPos, detectionRadius;
	private Point pos;
	private ArrayList<Integer> moves;
	private ArrayDeque<Energy> lifo;
	private ArrayDeque<Energy> fifo;

	public Robot() {
		isCurious = true;
		isHungry = isInactive = false;
		battery = new Energy();
		xPos = yPos = 0;
		detectionRadius = 40;
		lifo = new ArrayDeque<Energy>();
		pos = new Point();
		moves = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			moves.add(i);
		}
	}

	public Robot(int x, int y) {
		isCurious = true;
		isHungry = isInactive = false;
		battery = new Energy();
		detectionRadius = 40;
		lifo = new ArrayDeque<Energy>();
		setxPos(x);
		setyPos(y);
	}

	public void scan(ArrayList<Energy> e) {
		Point temp = pos;
		for (int i = (int) pos.getX() - detectionRadius; i <= detectionRadius; i++) {
			for (int j = (int) (pos.getY() - detectionRadius); i <= detectionRadius; i++) {
				temp.setLocation(i, j);
				for (int en = 0; en < e.size(); en++) {
					if (temp.equals(e.get(en).getLocation())) {
						if (e.get(en).getEnergy() > 0)
							lifo.addFirst(e.get(0));
					}

				}
			}
		}
	}

	public void curiousMove(ArrayList<Energy> e) {
		while (getCurious()) {
			randomMove(e);
		}
	}

	public void randomMove(ArrayList<Energy> e) {
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
		currentmov = moves.get(mov.nextInt(8));

		if (currentmov == 0 && currentmov != lastmov) {
			moveLeft();
			lastmov = right;
		} else if (currentmov == 1 && currentmov != lastmov) {
			moveRight();
			lastmov = left;
		} else if (currentmov == 2 && currentmov != lastmov) {
			moveUp();
			lastmov = down;
		} else if (currentmov == 3 && currentmov != lastmov) {
			moveDown();
			lastmov = up;
		} else if (currentmov == 4 && currentmov != lastmov) {
			moveDiagUpLeft();
			lastmov = diagDownRight;
		} else if (currentmov == 5 && currentmov != lastmov) {
			moveDiagUpRight();
			lastmov = diagDownLeft;
		} else if (currentmov == 6 && currentmov != lastmov) {
			moveDiagDownLeft();
			lastmov = diagUpRight;
		} else if (currentmov == 7 && currentmov != lastmov) {
			moveDiagDownRight();
			lastmov = diagUpLeft;
		}

		scan(e);
		setState();
	}

	public void lifohungryMove(ArrayList<Energy> e) {
		lifosnap(e);
		setState();

	}

	public void lifosnap(ArrayList<Energy> e) {
		int totmovX, totmovY, batteryusage, neededEnergy;
		neededEnergy = 200 - battery.getEnergy();

		if (lifo.size() != 0) {
			if (getDistance(lifo.getFirst().getLocation()) <= 9) {
				totmovX = lifo.getFirst().getxPos() - this.getxPos();
				totmovY = lifo.getFirst().getyPos() - this.getyPos();
				this.setLocation(this.getxPos() + totmovX, this.getyPos() + totmovY);
				batteryusage = (int) Math.sqrt(totmovX * totmovX + totmovY * totmovY);
				battery.setEnergy(battery.getEnergy() - batteryusage);
				
				if (lifo.getFirst().isDepleted()) {

					lifo.getFirst().setEnergy(lifo.getFirst().getEnergy() - neededEnergy);
					battery.setEnergy(battery.getEnergy() + neededEnergy);
				}

				lifo.removeFirst();
			}

			if (getDistance(lifo.getFirst().getLocation()) > 9) {
				if (lifo.getFirst().getxPos() - this.getxPos() > 0) {
					totmovX = 9;
				} else
					totmovX = -9;
				if (lifo.getFirst().getyPos() - this.getyPos() > 0) {
					totmovY = 9;
				} else
					totmovY = -9;
				this.setLocation(this.getxPos() + totmovX, this.getyPos() + totmovY);

				batteryusage = (int) Math.sqrt(totmovX * totmovX + totmovY * totmovY);

				lifo.getFirst().setEnergy(lifo.getFirst().getEnergy() - neededEnergy);
				battery.setEnergy(battery.getEnergy() - batteryusage);

				battery.setEnergy(battery.getEnergy() + neededEnergy);
			}
			lifo.removeFirst();
		} else
			randomMove(e);
	}

	public void fifohungryMove(ArrayList<Energy> e) {
		fifosnap(e);
		setState();
	}

	public void fifosnap(ArrayList<Energy> e) {
		int totmovX, totmovY, batteryusage, neededEnergy;
		neededEnergy = 200 - battery.getEnergy();

		if (fifo.size() != 0) {
			if (getDistance(fifo.getLast().getLocation()) <= 9) {
				totmovX = fifo.getLast().getxPos() - this.getxPos();
				totmovY = fifo.getLast().getyPos() - this.getyPos();

				this.setLocation(this.getxPos() + totmovX, this.getyPos() + totmovY);
				batteryusage = (int) Math.sqrt(totmovX * totmovX + totmovY * totmovY);
				battery.setEnergy(battery.getEnergy() - batteryusage);
				if (fifo.getLast().isDepleted()) {
					fifo.getLast().setEnergy(fifo.getLast().getEnergy() - neededEnergy);
					

					battery.setEnergy(battery.getEnergy() + neededEnergy);
					fifo.removeLast();
				}
			}

			if (getDistance(fifo.getFirst().getLocation()) > 9) {
				if (fifo.getLast().getxPos() - this.getxPos() > 0) {
					totmovX = 9;
				} else
					totmovX = -9;
				if (fifo.getLast().getyPos() - this.getyPos() > 0) {
					totmovY = 9;
				} else
					totmovY = -9;
				this.setLocation(this.getxPos() + totmovX, this.getyPos() + totmovY);

				batteryusage = (int) Math.sqrt(totmovX * totmovX + totmovY * totmovY);

				battery.setEnergy(battery.getEnergy() - batteryusage);

			}
			fifo.removeLast();
			setState();
		} else
			randomMove(e);
	}

	public void moveLeft() {
		setxPos(getxPos() - 13);
		pos.setLocation(getxPos(), getyPos());
		battery.setEnergy(battery.getEnergy() - 13);

	}

	public void moveRight() {
		// code
		setxPos(getxPos() - 13);
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

	public void moveDiagUpLeft() {
		Random diag = new Random();
		if (diag.nextInt(2) == 0) {
			setxPos(getxPos() - (int) (18.83 * Math.cos(45)));
			setyPos(getyPos() + (int) (18.83 * Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int) (18.83 * Math.cos(45)));

		} else
			setxPos(getxPos() - (int) (13 * Math.cos(45)));
		setyPos(getyPos() + (int) (13 * Math.cos(45)));
		pos.setLocation(getxPos(), getyPos());
		battery.setEnergy(battery.getEnergy() - (int) (13 * Math.cos(45)));
	}

	public void moveDiagUpRight() {
		Random diag = new Random();
		if (diag.nextInt(2) == 0) {
			setxPos(getxPos() + (int) (18.83 * Math.cos(45)));
			setyPos(getyPos() + (int) (18.83 * Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int) (18.83 * Math.cos(45)));

		} else
			setxPos(getxPos() + (int) (13 * Math.cos(45)));
		setyPos(getyPos() + (int) (13 * Math.cos(45)));
		pos.setLocation(getxPos(), getyPos());
		battery.setEnergy(battery.getEnergy() - (int) (13 * Math.cos(45)));
	}

	public void moveDiagDownLeft() {
		Random diag = new Random();
		if (diag.nextInt(2) == 0) {
			setxPos(getxPos() - (int) (18.83 * Math.cos(45)));
			setyPos(getyPos() - (int) (18.83 * Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int) (18.83 * Math.cos(45)));
		} else
			setxPos(getxPos() - (int) (13 * Math.cos(45)));
		setyPos(getyPos() - (int) (13 * Math.cos(45)));
		pos.setLocation(getxPos(), getyPos());
		battery.setEnergy(battery.getEnergy() - (int) (13 * Math.cos(45)));
	}

	public void moveDiagDownRight() {
		Random diag = new Random();
		if (diag.nextInt(2) == 0) {
			setxPos(getxPos() + (int) (18.83 * Math.cos(45)));
			setyPos(getyPos() - (int) (18.83 * Math.cos(45)));
			pos.setLocation(getxPos(), getyPos());
			battery.setEnergy(battery.getEnergy() - (int) (18.83 * Math.cos(45)));
		} else
			setxPos(getxPos() + (int) (13 * Math.cos(45)));
		setyPos(getyPos() - (int) (13 * Math.cos(45)));
		pos.setLocation(getxPos(), getyPos());
		battery.setEnergy(battery.getEnergy() - (int) (13 * Math.cos(45)));
	}

	public void setState() {
		if (battery.getEnergy() <= 0) {
			setInactive(true);
			setHungry(false);
			setCurious(false);
		} else if (battery.getEnergy() < 100) {
			setHungry(true);
			setInactive(false);
			setCurious(false);
		} else if (battery.getEnergy() > 100)
			setCurious(true);
		setHungry(false);
		setInactive(false);
	}

	public String getState() {
		if (getCurious()) {
			return "Robot is curious";
		} else if (getHungry()) {
			return "Robot is hungry";
		} else
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

	public int getDistance(Point a) {
		return (int) Math.sqrt((this.getxPos() - a.getX()) * (this.getxPos() - a.getX())
				+ (this.getyPos() - a.getY()) * (this.getyPos() - a.getY()));

	}

	public String checkState() {
		if (this.getCurious()) {
			return "Robot is Curious";
		} else if (this.getHungry()) {
			return "Robot is Hungry";
		}
		return "Robot is Inactive";
	}
}
