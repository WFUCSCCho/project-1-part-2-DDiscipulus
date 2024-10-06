import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Proj1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Here is a list of valid commands");
        System.out.println("Insert: provide food item");
        System.out.println("Delete: deletes item from tree");
        System.out.println("Print best option: prints lowest calorie item from tree");
        System.out.println("Top 5 Restaurant(write a specific one): Prints top 5 least caloric items from a given company");

            if(args.length != 1){
                System.err.println("Argument count is invalid: " + args.length);
                System.exit(0);
            }

            // clears previous result files
            new PrintWriter("./result.txt").close();

            // actually starts program
            new Parser(args[0]);

        System.out.println("Testing if Data is being used frfr");
        FastFoodNutritionInfo.readFastFoodData("C:\\Users\\desti\\Documents\\project-1-part-2-DDiscipulus\\src\\Edit(3)FFNData.csv");
       FastFoodNutritionInfo.printFFNData();
    }
}