package property.vcs.lt;

public class Cottage extends Property {
	
	private int numberOfNeigbourCottages;
	
	
	public Cottage (String c, double a, double p) {
		super(c, a, p);
		type = PropertyType.COTTAGE;
	}


	public int getNumberOfNeigbourCottages() {
		return numberOfNeigbourCottages;
	}


	public void setNumberOfNeigbourCottages(int numberOfNeigbourCottages) {
		this.numberOfNeigbourCottages = numberOfNeigbourCottages;
	}


	@Override
	public String toString() {
		String neighbours = numberOfNeigbourCottages == 0 ? "no" : String.valueOf(this.numberOfNeigbourCottages);
		return "This is a cottage with " + neighbours + " neighbours." + super.toString();
	}
	
	
	
} 
