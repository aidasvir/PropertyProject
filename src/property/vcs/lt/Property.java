package property.vcs.lt;

public class Property {
	private String city;
	private double area;
	private double price;
	PropertyType type;
	
	public Property (String c, double a, double p) {
		this.city = c;
		this.area = a;
		this.price = p;
	}

	public String getCity() {
		return city;
	}

	public double getArea() {
		return area;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "The property is in " + city + ", it costs " + price + " Eur, the area is " + area + " square meters";
	}

	public PropertyType getType() {
		return type;
	}
	
	
	
}
