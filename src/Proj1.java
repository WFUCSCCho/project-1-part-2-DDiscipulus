/**
 * @ BST.java
 * @ This program lets a user to various commands using data about Fast Food
 *   and a user constructedBST
 * @ author: Destiny
 * @ date: Oct 5, 2024
 */
import java.io.IOException;
import java.io.PrintWriter;


public class Proj1 {
     static boolean listWasCalled = false; // tracks if list command was used

    // set method for List
    public static void setListWasCalled(boolean listWasCalled) {
        Proj1.listWasCalled = listWasCalled;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // read the dataset
        FastFoodNutritionInfo.readFastFoodData("C:\\Users\\desti\\Documents\\project-1-part-2-DDiscipulus\\src\\Edited(4)FFNData.csv");

        // short intro for user
        System.out.println("This program contains info on food from: \n \t McDonald's, Burger King, Taco Bell, Pizza Hut, Wendy’s, KFC \n ");
        Thread.sleep(4000);

        System.out.println("Here is a list of valid commands \n");
        Thread.sleep(2000);
       // explain commands
        System.out.println("List: list all items in database (only prints once)");
        System.out.println("Insert: provide food company & item (please separate with a comma)");
        System.out.println("Delete: provide food company & item (see above)");
        System.out.println("PrintBestOption: prints lowest calorie item from tree");
        System.out.println("RankOptions): List user given items in order from least to most caloric");
        Thread.sleep(4500);

        // check args
            if(args.length != 1){
                System.err.println("Argument count is invalid: " + args.length);
                System.exit(0);
            }

            // clears previous result files
            new PrintWriter("./result.txt").close();

            // actually starts program
            new Parser(args[0]);

            // print database if list command used
            if(listWasCalled){
                System.out.println("__________________Full Database List Start______________________");
                Thread.sleep(3000);
                FastFoodNutritionInfo.printFFNData();
                System.out.println("__________________Database List Complete______________________");
                Thread.sleep(3000);

            }

    }
}