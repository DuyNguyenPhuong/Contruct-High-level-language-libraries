import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The CountrySortedList class sorts the dataset based on the indicator
 * user want
 * It takes in a dataset and String of the name of the indicator. It uses
 * the add method to add each Object in a sorted order.
 */
public class CountrySorterList {
    Node firstNode;
    Node lastNode;
    String indicator;
    private class Node {
        private Country data; 
        private Node next; 
        private Node previous;
    
        private Node(Country dataPortion) {
            this(dataPortion, null, null);
        } // end constructor

        private Node(Country dataPortion, Node nextNode, Node previousNode) {
            data = dataPortion;
            next = nextNode;
            previous = previousNode;
        } // end constructor
    } // end Node

    int numberOfEntries = 0;
    /**
     * Construct a new CountrySortedList object with the type of
     * the indicator and the Dataset file user want
     * @param ind (String) The name of the indicator
     * @param filename (String) The name of the dataset file
     */
    public CountrySorterList(String ind, String filename) {
        indicator = ind;
        File inputFile = new File(filename);
        Scanner scanner = null;
        try {
          scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
          System.err.println(e);
          System.exit(1);
        }
        scanner.nextLine();
        while (scanner.hasNextLine()) {
          Country newCountry = new Country(scanner.nextLine());
          this.add(newCountry);

        }
        scanner.close();
    }
    
    /**
     * The add method add a new Country object into a sorted order
     * 
     * @param newEntry (Country) Country we want to add
     * @return true if we can add, false if we cannot
     */
    public boolean add(Country newEntry){
      boolean canAdd = false;
      Node newNode = new Node(newEntry);
      Node nodeBefore = getNodeBefore(newEntry);
      if (this.isEmpty() || (nodeBefore == null)){
        newNode.next = firstNode;
        firstNode = newNode;
        lastNode = newNode;
        newNode.previous = null;
        canAdd = true;
      }
      else if(nodeBefore.next == null){
        nodeBefore.next = newNode;
        newNode.previous = nodeBefore;
        lastNode = newNode;
      }
      else{
        Node nodeAfter = nodeBefore.next;
        newNode.next = nodeAfter;
        nodeBefore.next = newNode;
        nodeAfter.previous = newNode;
        newNode.previous = nodeBefore;
        canAdd = true;
      }
      numberOfEntries++;
      return canAdd;
    }

    /**
     * getNodeBefore method wil gives the Node directly before the
     * newNode
     * @param wantAddCountry (Country) Country we want to add
     * @return (Node) the Node before
     */
    public Node getNodeBefore(Country wantAddCountry){
      Node currentNode = firstNode;
      Node nodeBefore = null;
      CountryComparator compareTemp = new CountryComparator(this.indicator);
      while (currentNode!= null && (compareTemp.compare(wantAddCountry, currentNode.data) >0)){
        nodeBefore = currentNode;
        currentNode = currentNode.next;
      }
      return nodeBefore;
    }

    /**
     * Check if the CountrySortedList object is empty
     * @return true if it's empty; false if it's not
     */
    public boolean isEmpty(){
      if (firstNode == null){
        return true;
      }
      else{
        return false;
      }
    }

    /**
     * Print the list of Country objects on the top which have 
     * the smallest indicator
     * If the indicator is the Name, it will print the list of
     * Countries on the top in alphabetical order (A-Z)
     * @param topNumber (int) number of top Countries
     * @return (String) the top Country objects with its position number
     */
    public String listTop(int topNumber){
      String top = "";
      Node currentNode = firstNode;
      for (int i = 0; i< topNumber; i++){
        top += String.valueOf(i+1);
        top += ": ";
        top += currentNode.data.getName();
        top += "\n";
        currentNode = currentNode.next;
      }
      return top;
    }

    /**
     * Print the list of Country objects in the bottom which have 
     * the biggest indicator
     * If the indicator is the Name, it will print the list of
     * Countries in the bottom in alphabetical order (A-Z)
     * @param bottomNumber (int) number of top Countries
     * @return (String) the top Country objects with its position number
     */
    public String listBottom (int bottomNumber){
      String bottom = "";
      Node currentNode = lastNode;
      for (int i = 0; i< bottomNumber; i++){
        bottom += String.valueOf(i+1);
        bottom += ": ";
        bottom += currentNode.data.getName();
        bottom += "\n";
        currentNode = currentNode.previous;
      }
      return bottom;
    }

    /**
     * tests to see if a string the user entered is a number
     * @param userString the string the user entered
     * @return (boolean) true if entry is a number, false if not
     */
    public static boolean isInteger(String userString){
      try{
          Integer.parseInt(userString);
          return true;
      } 
      catch(Exception e){
          return false;
      }
    }

    /**
     * The main will handle user input
     * It will ask the user to do the program again until it's inputted correctly
     * It will ask what indication and file name user want
     * Then it will ask if user what to see the top or the bottom of the list
     * Then it will ask How many Countries to be printed do the user want
     * Then it will print the result.
     * @param args
     */
    public static void main(String[] args) {
      Scanner myObj = new Scanner(System.in);
      System.out.println("Here are the list of the indicators you can choose to compare:");
      System.out.println("1. \"Name\" - 2. \"Population Total\" - 3. \"CO2 Emissions\""); 
      System.out.println("4. \"Access To Electricity\" - 5. \"Renewable Energy\""); 
      System.out.println("6. \"Protected Areas\" - 7. \"Population Growth\""); 
      System.out.println("8. \"Urban Population Growth\"");
      String userInd = "";
      while (true){
        System.out.println("Enter the indicator you want to compare:");
        userInd = myObj.nextLine();
        if (userInd.equals("Name") || userInd.equals("Population Total") || 
        userInd.equals("CO2 Emissions") || userInd.equals("Access To Electricity") 
        || userInd.equals("Renewable Energy")  || userInd.equals("Protected Areas")
        || userInd.equals("Population Growth") || userInd.equals("Urban Population Growth")){
          break;
        }
        else{
          System.out.println("Your input is not in the list. Do it again!");
        }
      }

      System.out.println("Great! You want to compare the " + userInd);
      System.out.println("Now enter your dataset file name:");
      String userFileName = myObj.nextLine(); 
      
      CountrySorterList countryList = new CountrySorterList(userInd,userFileName);

      System.out.println("Great! Now which choice do you want to do: ");
      String userChoice = "";
      while (true){
        System.out.println("Type the corresponding number for task ");
        System.out.println("1. Print the list of Country on the Top (the smallest or the top of the A-Z order)");
        System.out.println("2. Print the list of Country in the Bottom (the biggest or the bottom of the A-Z order)");
        userChoice = myObj.nextLine();
        if (userChoice.equals("1") || userChoice.equals("2")){
          break;
        }
        else{
          System.out.println("Enter only \"1\" or \"2\". Do it again!");
        }
      }
      String userNum = "";
      int userNumInt;
      
      while(true){
        System.out.println("Great! Now enter number of top/bottom countries you want: ");
        userNum = myObj.nextLine();
        
        if (!isInteger(userNum)){
          System.out.println("Only enter number between 1 and " + String.valueOf(countryList.numberOfEntries));
          continue;
        }
        userNumInt = Integer.parseInt(userNum);
        if (userNumInt > countryList.numberOfEntries || userNumInt <= 0){
          System.out.println("Only enter number between 1 and " + String.valueOf(countryList.numberOfEntries));
        }
        else{
          break;
        }
      }
      System.out.println("Here is the result:");
      if (userChoice.equals("1")){
        System.out.println(countryList.listTop(userNumInt));
      }
      else if (userChoice.equals("2")){
        System.out.println(countryList.listBottom(userNumInt));
      }
      myObj.close();
    }
}