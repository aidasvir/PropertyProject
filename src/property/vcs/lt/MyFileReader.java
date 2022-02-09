package property.vcs.lt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MyFileReader {
	
	private ArrayList<Property> property = new ArrayList<Property>();
	
	public ArrayList<Property> getProperty() {
		return property;
	}
	
	
	public MyFileReader(boolean runReading) {
		
		if (runReading) {
			readFromFile();
		}
		
	}

	public String findPrice(String s) {
		
		String r = s.substring(0, s.length() - 5);
		r = r.replace(".", "");
		return r;
	}

	public Property createProperty(String type, String city, double area, double price, int sk) throws NoSuchPropertyException {
		
		switch(type) {
		
		case "f":
			Flat flat = new Flat(city, area, price);
			flat.setNumberOfRooms(sk);
			return flat;
		
		case "c":
			Cottage cottage = new Cottage(city, area, price);
			cottage.setNumberOfNeigbourCottages(sk);
			return cottage;
		case  "h":
			House house = new House(city, area, price);
			house.setNumberOfFloors(sk);
			return house;
		}
	
	
	throw new NoSuchPropertyException();
	
	}

	public void extractInfo(String line) {
		if(line.isEmpty() || line == null) {
			return;
		} 
		
		String[] lineParts = line.split(",");
		
		String type = lineParts[0];
		String city = lineParts[1];
		double area = Double.valueOf(lineParts[2]);
		double price = Double.valueOf(findPrice(lineParts[3])) * 100;
		int sk = Integer.valueOf(lineParts[4]);
		
		try {
			Property prop = createProperty(type, city, area, price, sk);
			this.property.add(prop);
		} catch (NoSuchPropertyException e) {
			
			e.printStackTrace();
		}
	}


	public void readFromFile() {
		Path failoNuoroda = Paths.get("C:\\Users\\superuser\\Desktop\\VCS JAVA\\Pamoka 04\\PropertyProject\\src\\files\\vcs\\lt\\propData.txt");
		
		
		try(Stream<String> lines = Files.lines(failoNuoroda)) {
			lines.forEach(line -> extractInfo(line));
			
		} catch(IOException e) {
			e.printStackTrace();
		} 
		
		
	}

		
}
	


