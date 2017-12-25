package headfirst.designpatterns.state.gumball;

public class GumballMachine {

	int count = 0; 
	int state = SOLDOUT;
  
	final static int SOLDOUT = 0;
	final static int NOQUARTER = 1;
	final static int HASQUARTER = 2;
	final static int SOLD = 3;
 
	public GumballMachine(int count) {
		this.count = count;
		if (count > 0) {
			state = NOQUARTER;
		}
	}
  
	public void insertQuarter() {
		if (state == HASQUARTER) {
			System.out.println("You can't insert another quarter");
		} else if (state == NOQUARTER) {
			state = HASQUARTER;
			System.out.println("You inserted a quarter");
		} else if (state == SOLDOUT) {
			System.out.println("You can't insert a quarter, the machine is sold out");
		} else if (state == SOLD) {
        	System.out.println("Please wait, we're already giving you a gumball");
		}
	}

	public void ejectQuarter() {
		if (state == HASQUARTER) {
			System.out.println("Quarter returned");
			state = NOQUARTER;
		} else if (state == NOQUARTER) {
			System.out.println("You haven't inserted a quarter");
		} else if (state == SOLD) {
			System.out.println("Sorry, you already turned the crank");
		} else if (state == SOLDOUT) {
        	System.out.println("You can't eject, you haven't inserted a quarter yet");
		}
	}
 
	public void turnCrank() {
		if (state == SOLD) {
			System.out.println("Turning twice doesn't get you another gumball!");
		} else if (state == NOQUARTER) {
			System.out.println("You turned but there's no quarter");
		} else if (state == SOLDOUT) {
			System.out.println("You turned, but there are no gumballs");
		} else if (state == HASQUARTER) {
			System.out.println("You turned...");
			state = SOLD;
			dispense();
		}
	}
 
	private void dispense() {
		if (state == SOLD) {
			System.out.println("A gumball comes rolling out the slot");
			count = count - 1;
			if (count == 0) {
				System.out.println("Oops, out of gumballs!");
				state = SOLDOUT;
			} else {
				state = NOQUARTER;
			}
		} else if (state == NOQUARTER) {
			System.out.println("You need to pay first");
		} else if (state == SOLDOUT) {
			System.out.println("No gumball dispensed");
		} else if (state == HASQUARTER) {
			System.out.println("No gumball dispensed");
		}
	}
 
	public void refill(int numGumBalls) {
		this.count = numGumBalls;
		state = NOQUARTER;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2004\n");
		result.append("Inventory: " + count + " gumball");
		if (count != 1) {
			result.append("s");
		}
		result.append("\nMachine is ");
		if (state == SOLDOUT) {
			result.append("sold out");
		} else if (state == NOQUARTER) {
			result.append("waiting for quarter");
		} else if (state == HASQUARTER) {
			result.append("waiting for turn of crank");
		} else if (state == SOLD) {
			result.append("delivering a gumball");
		}
		result.append("\n");
		return result.toString();
	}
}


