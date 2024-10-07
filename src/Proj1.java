import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Proj1 {
     static boolean listWasCalled = false;

    public static void setListWasCalled(boolean listWasCalled) {
        Proj1.listWasCalled = listWasCalled;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        FastFoodNutritionInfo.readFastFoodData("C:\\Users\\desti\\Documents\\project-1-part-2-DDiscipulus\\src\\Edited(4)FFNData.csv");

       /*
        System.out.println("This program contains info on food from: \n \t McDonald, Burger King, Taco Bell, PizzaHut, Wendy’s, KFC \n ");
        Thread.sleep(4000);
        System.out.println("Here is a list of valid commands \n");
        Thread.sleep(2000);
        System.out.println("List: list all items in database (only prints once)");
        System.out.println("Insert: provide food company & item separated by a comma");
        System.out.println("Delete: provide food company & item");
        System.out.println("PrintBestOption: prints lowest calorie item from tree");
        System.out.println("RankOptions): List user given items in order from least to most caloric");
        Thread.sleep(4500);
*/
            if(args.length != 1){
                System.err.println("Argument count is invalid: " + args.length);
                System.exit(0);
            }

            // clears previous result files
            new PrintWriter("./result.txt").close();

            // actually starts program
            new Parser(args[0]);
            if(listWasCalled){
                System.out.println("__________________Full Database List Start______________________");
                Thread.sleep(3000);
                FastFoodNutritionInfo.printFFNData();
                System.out.println("__________________Database List Complete______________________");
                Thread.sleep(3000);

            }

    }
}