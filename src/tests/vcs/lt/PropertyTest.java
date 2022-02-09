package tests.vcs.lt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import property.vcs.lt.Cottage;
import property.vcs.lt.Flat;
import property.vcs.lt.House;
import property.vcs.lt.MyFileReader;
import property.vcs.lt.NoSuchPropertyException;
import property.vcs.lt.PriceCalculator;
import property.vcs.lt.Property;
import property.vcs.lt.PropertyType;

class PropertyTest {   


    MyFileReader reader = new MyFileReader(false);
    PriceCalculator pc =  new PriceCalculator(new MyFileReader(true).getProperty());
//
    @Test
    void testCreatedPropertyClass() {
        Property prop = new Property("Palanga", 54.96, 8840.99);
        String description = prop.toString();
        assertNotEquals("Vilnius", prop.getCity());
        assertFalse(50 == prop.getArea());
        assertTrue(8840.99 == prop.getPrice());
        assertEquals(description,
                "The property is in Palanga, it costs 8840.99 Eur, the area is 54.96 square meters");
    }
//
//    
    @Test
    void testIfFlat() {
        Property prop = new Flat("Kaunas", 67, 82000);
        PropertyType type = prop.getType();
        assertTrue(prop instanceof Flat);
        assertEquals(PropertyType.FLAT, type);
    }
//
   @Test
    void testFlatHasRoomsAndDscrpt() {
        Flat flat = new Flat("Alytus", 87, 60500);
        flat.setNumberOfRooms(4);
        String description = flat.toString();
        assertTrue(flat.getNumberOfRooms() == 4);
        assertEquals(
                "This is a 4 rooms flat. " + "The property is in Alytus, it costs 60500.0 Eur, the area is 87.0 square meters",
                description);
    }
//
//   
    @Test
    void testIfCottage() {
        Property prop = new Cottage("Vilnius", 74, 120000);
        PropertyType type = prop.getType();
        assertTrue(prop instanceof Cottage);
        assertEquals(PropertyType.COTTAGE, type);
    }
//
    @Test
    void testCottageHasNeigboursAndDscrpt() {
        Cottage cottage = new Cottage("Kaunas", 87, 118500);
        cottage.setNumberOfNeigbourCottages(0);
        String description = cottage.toString();
        assertTrue(cottage.getNumberOfNeigbourCottages() == 0);
        assertEquals(
                "This is a cottage with no neighbours."
                        + "The property is in Kaunas, it costs 118500.0 Eur, the area is 87.0 square meters",
                description);
    }
//
//    
    @Test
    void testIfHouse() {
        Property prop = new House("Klaipeda", 130, 97800);
        PropertyType type = prop.getType();
        assertTrue(prop instanceof House);
        assertEquals(PropertyType.HOUSE, type);
    }
//
    @Test
    void testIfHouseHasFloorsAndDscrpt() {
        House house = new House("Palanga", 125, 500300);
        house.setNumberOfFloors(2);
        String description = house.toString();
        assertTrue(house.getNumberOfFloors() == 2);
        assertEquals(
                "This is a 2-floor house."
                        + "The property is in Palanga, it costs 500300.0 Eur, the area is 125.0 square meters",
                description);
    }
//
////Testing reading from file
//
    @Test
    void testFindPrice() {
        String s = "58.5thsnd";
        String number = reader.findPrice(s);
        assertEquals("585", number);
    }
//
//
    @Test
    void testCreateProperty() throws NoSuchPropertyException {
        Property prop1 =
                reader.createProperty("f", "New York", 20.45, 200.3, 1);
        Property prop2 = reader.createProperty("c", "London", 98.01, 100.5, 2);
        Property prop3 = reader.createProperty("h", "Paris", 117.93, 500, 2);
        assertEquals(PropertyType.FLAT, prop1.getType());
        assertEquals(PropertyType.COTTAGE, prop2.getType());
        assertEquals(PropertyType.HOUSE, prop3.getType());
    }
//
      @Test
    void testNoSuchPropertyException() {
        assertThrows(NoSuchPropertyException.class, () -> {
            reader.createProperty("flat", "New York", 20.45, 200.3, 1);
            ;
        });
    }
//
    @Test
    void testExtractInfo() {
        reader.extractInfo("c,Raseiniai,108.59,25.8thsnd,2");
        Property prop = reader.getProperty().get(0);
        assertEquals("Raseiniai", prop.getCity());
        assertTrue(108.59 == prop.getArea());
        assertTrue(25800 == prop.getPrice());
        assertEquals(PropertyType.COTTAGE, prop.getType());

    }
//
    @Test
    void testReadFromFile() {
        reader.readFromFile();
        assertTrue(36 == reader.getProperty().size());
    }
//
    @Test
    void testMyFileReader() {
        MyFileReader reader = new MyFileReader(true);
        assertFalse(reader.getProperty().isEmpty());
   }
//
////Test price calculator
//
  @Test
    void testCountSqMeterPrice() {
        Flat f = new Flat("London", 100.0, 200000.0);
        double metersPrice = pc.countSqMeterPrice(f);
        assertTrue(2000.0 == metersPrice);

    }
//
    @Test
    void testCountAvergOfCity() {
        Double avg = pc.countAverageOfCity("Klaipeda");
        assertEquals(1386.38, avg); 
        assertFalse(0.00 == avg);
    }
//
    @Test
    void testFindAllCities() {
        List<String> cities =
                pc.findAllCities();
        assertTrue(5 == cities.size());
    }
//
    @Test
    void testCreateReport() {
        pc.createReport();
        TreeMap<String, Double> averagePrices = pc.getAveragePrices();
        assertTrue(5 == averagePrices.size());
        averagePrices
                .forEach((K, V) -> System.out
                        .println(K + ", property price: " + V
                                + " per sq meter"));
    }

}








