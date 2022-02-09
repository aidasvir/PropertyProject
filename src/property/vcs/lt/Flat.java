package property.vcs.lt;

public class Flat extends Property {
	
	private int numberOfRooms;
	
	public Flat (String c, double a, double p) {
		super(c, a, p);
		type = PropertyType.FLAT;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	@Override
	public String toString() {
		return "This is a " + getNumberOfRooms() + " rooms flat. " + super.toString();
	}


	
}
