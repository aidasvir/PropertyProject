package property.vcs.lt;

public class House extends Property {
	
	private int numberOfFloors;
	
	public House (String c, double a, double p) {
		super(c, a, p);
		type = PropertyType.HOUSE;
	}


	public int getNumberOfFloors() {
		return numberOfFloors;
	}


	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}


	@Override
	public String toString() {
		return "This is a " + numberOfFloors +"-floor house." + super.toString();
	}
	
	
	
}
