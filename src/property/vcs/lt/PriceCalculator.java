package property.vcs.lt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PriceCalculator {
	
	private ArrayList<Property> property;
	private TreeMap<String, Double> averagePrices = new TreeMap<>();

	
	
	
	public PriceCalculator(ArrayList<Property> property) {
		this.property = property;
	}



	public double countSqMeterPrice(Property property) {
		double sqMetterPrice = property.getPrice() / property.getArea();
		BigDecimal rounded = new BigDecimal(sqMetterPrice).setScale(2, RoundingMode.UP);
		return rounded.doubleValue();
	}



	public Double countAverageOfCity(String city) {
		
		Double sum = property.stream().filter(prop -> prop.getCity().equals(city))
				.mapToDouble(property -> countSqMeterPrice(property))
				.sum();
		
		Double average = sum / property.stream().filter(prop -> prop.getCity().equals(city)).count();
		
		DecimalFormat df = new DecimalFormat("#.##");
		average = Double.valueOf(df.format(average));
		
		averagePrices.put(city, average);
		
		return average;
	}
	

	public TreeMap<String, Double> getAveragePrices() {
		return averagePrices;
	}
	

	public List<String> findAllCities() {
		
		Set<String> cities = property.stream().map(prop -> prop.getCity())
				.collect(Collectors.toSet());
			
		return new ArrayList<String>(cities);
	}

	


	
	public void createReport() {
		
		findAllCities().stream().forEach(city -> countAverageOfCity(city));
		
		
	}




	
	
	
}
