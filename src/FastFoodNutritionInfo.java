/**
 * @ FastFoodNutritionInfo.java
 * @ This program implements the Fast Food Nutrition(FFN) class and creates FFN objects
 *   from a data set gotten online
 * @ author: Destiny
 * @ date: Oct 4, 2024
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FastFoodNutritionInfo implements Comparable <FastFoodNutritionInfo> {
    // class variables
    private String company;
    private String item;
    private Double calories;
    private Double totalFat;
    private Double carbs;
    private Double protein;

    // array to store objects from data file
    static ArrayList<FastFoodNutritionInfo> allFFN = new ArrayList<FastFoodNutritionInfo>();


    // default constructor
    public FastFoodNutritionInfo() {
        this.company = "unknown";
        this.item = "unknown";
        this.calories = 0.0;
        this.totalFat = 0.0;
        this.carbs = 0.0;
        this.protein = 0.0;
    }


    // Constructor for FFN data
    public FastFoodNutritionInfo(String company, String item, Double totalFat, Double calories, Double carbs, Double protein) {
        this.company = company;
        this.item = item;
        this.calories = calories;
        this.totalFat = totalFat;
        this.carbs = carbs;
        this.protein = protein;
    }

    // String interface for FFN data
    @Override
    public String toString() {
        return "Company: " + company + ", Item: " + item + ", Total Calories: " + calories + "\n" +
                " \t Macro Breakdown: " + "Fat: " + totalFat + " carbs " + carbs + " protein " + protein + "\n";
    }

    // Comparable interface for FFN data
        // tree will compare food by calorie amount
    @Override
    public int compareTo(FastFoodNutritionInfo other) {
        return this.calories.compareTo(other.calories);
    }

    // Getters and Setters for Calories
    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    // Getters and Setters for Company

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    // Getters and Setters for total fat

    public void setTotalFat(Double totalFat) {
        this.totalFat = totalFat;
    }

    public Double getTotalFat() {
        return totalFat;
    }

    // Getters and Setters for Carbs
    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    // Getters and Setters for items
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    // Getters and Setters for Protein
    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    // Method to read the data file and convert it to FastFoodNutritionInfo objects
    public static void readFastFoodData(String csvFilePath) throws IOException {
        Scanner scanner = new Scanner(new File(csvFilePath));
        scanner.nextLine(); // Skip top line

        // Read each line from the CSV file
        while (scanner.hasNextLine()) {
            // Read the current line
                String company = null;
                String item = null;
                Double totalFat = null;
                Double calories = null;
                Double carbs = null;
                Double protein = null;


                String dataEntry = scanner.nextLine();

                // Split the strings by commas
                String[] properData = dataEntry.split(",");


                // convert strings to object attribute variables
                    company = properData[0];
                    item = properData[1];

                    // Convert the Strings to Doubles
                    totalFat = Double.parseDouble(properData[3]);  //
                    calories = Double.parseDouble(properData[2]);
                    carbs = Double.parseDouble(properData[4]);
                    protein = Double.parseDouble(properData[5]);

                // Create a new FastFoodNutritionInfo object
                FastFoodNutritionInfo ffnObject = new FastFoodNutritionInfo(company, item, totalFat, calories, carbs, protein);

                // Add the object to the list
                allFFN.add(ffnObject);
            }
        }

        // method prints entire database
    public static void printFFNData() {
        // Iterate through the list and print each object
        for (FastFoodNutritionInfo ffn : allFFN) {
            System.out.println(ffn.toString());
        }
    }

    // Method use a given item and company name to ID the actual FFN of object
    public static FastFoodNutritionInfo getFFNFromItemAndCompany(String item, String company){;
        for(int i = 0; i < allFFN.size();i++){
            FastFoodNutritionInfo currentFFN = allFFN.get(i);
            boolean sameItem = currentFFN.item.equals(item);
            boolean sameCompany = currentFFN.company.equals(company);
            if(sameItem && sameCompany) {
                return allFFN.get(i);
                }
            }
        return null;
        }
    }



