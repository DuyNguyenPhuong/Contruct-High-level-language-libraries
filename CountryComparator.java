import java.lang.String;
import java.util.*;

/**
 * The CountryComparator class creates a CountryComparator object
 * It has the method compare to compare values of 2 Country.
 */
public class CountryComparator implements Comparator<Country> {
    private String indicator;

    /**
     * Contruct a new objects
     * @param indicator (String) type of indicator we want to compare
     */
    public CountryComparator(String indicator) {
        this.indicator = indicator;
    }

    /**
     * The compare method compare 2 indicator from 2 input Country object
     * @param country1 (Country) the first Country want to compare
     * @param country2 (Country) the second Country want to compare
     * @return (int) -1 if country1 comes before country2;
     * 1 if country1 comes after country2
     * 0 if country1 and country2 can be in either order
     */
    public int compare(Country country1, Country country2) {
        if(!this.indicator.equals("Name")){
            if(country1.getStat(indicator) > country2.getStat(indicator)){
                return 1;
            }
            else if (country1.getStat(indicator) < country2.getStat(indicator)){
                return -1;
            }
            else{
                return 0;
            }
        }
        else{
            int compareInt = (country1.getName().compareToIgnoreCase(country2.getName()));
            if (compareInt < 0 ){
                return -1;
            }
            else if (compareInt > 0){
                return 1;
            }
            else{
                return 0;
            }
        }
    }

    /**
     * We create a object named "test" to test like in the HW instruction
     * We also create some Country objects for testing
     * @param args
     */
    public static void main(String[] args) {
        CountryComparator test = new CountryComparator("Name");
        Country Afghanistan = new Country("Afghanistan,33.26,0.33,72.7,15.73,0.1,2.99,3.89");
        Country Angola = new Country("Angola,27,1.27,37.13,51.7,6.97,3.49,4.64");
        System.out.println(test.compare(Afghanistan, Angola));
    }
}
