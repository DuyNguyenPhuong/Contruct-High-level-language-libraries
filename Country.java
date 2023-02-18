import java.util.HashMap;

/**
 * The Country class has a Hashmap storing information
 * of each Country object (The key is the name of indicator
 * and value is the indicator value)
 * It also has the getStat and getName method that
 * return the indicator value and the name of the 
 * Country object
 */
public class Country {
    HashMap<String, String> stats = new HashMap<String, String>();
    /**
     * Contruct a new Country object
     * Take in a String input then split to an according
     * value, and add it to the Hashmap
     * @param in
     */
    public Country(String in){
        String[] splitline = in.split(",");
        stats.put("Name",splitline[0]);
        stats.put("Population Total", splitline[1]);
        stats.put("CO2 Emissions", splitline[2]);
        stats.put("Access To Electricity", splitline[3]);
        stats.put("Renewable Energy", splitline[4]);
        stats.put("Protected Areas", splitline[5]);
        stats.put("Population Growth", splitline[6]);
        stats.put("Urban Population Growth", splitline[7]);
    }

    /**
     * The getStat method takes in a String of the name
     * of an indicator then gives the value of the indicator
     * @param indicator (String) name of the indicator
     * @return (Double) value of the indicator
     */
    public double getStat(String indicator) {
        return Double.parseDouble(stats.get(indicator));
    }

    /**
     * The getName method return the name of the Country
     * object
     * @return (String) the name of the Country
     */
    public String getName() {
        return stats.get("Name");
    }

}
