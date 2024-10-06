/**
 * @ Parser.java
 * @ This program implements that reads and processing input commands from a user provided file
 * @ author: Destiny
 * @ date: September 25, 2024
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    //Create a BST tree of Integer type
    private BST<Integer> myBST = new BST<>();

    public Parser(String filename) throws IOException {
        process(new File(filename));
    }

    /* *                  Process
     * This method takes in a text file eliminates redundancies
     * and label and stores a series of commands in values into ArrayLists
     * it then calls operateBST()
     *
     * @param input is the given file
     * @return void
     */
    public void process(File input) throws IOException {
        // basic variables and objects
        Scanner reader = new Scanner(input);
        ArrayList<String> validCommands = new ArrayList<String>(); // stores valid commands
        ArrayList<String> ValuesForCodes = new ArrayList<>(); // store item names needed for each command
        String currentLine; // stores the current input

        // Processing occurs below
        while(reader.hasNext()) {
            // more variables
            String valueStorage; // stores String inputs
            String commandToADD; // string to store commands for ArrayList

            // reads next line until space of text
            currentLine = reader.next();

            // Remove redundant spaces for each input command
            if (currentLine.equals("")) {

            } else if (currentLine.equals("Insert") || currentLine.equals("insert")) {
                // if currentLine is insert add command insert and the value to insert to Array Lists
                valueStorage = reader.next();
                commandToADD = "insert";

                ValuesForCodes.add(valueStorage);
                validCommands.add(commandToADD);

            } else if ((currentLine.equals("Print best option") || currentLine.equals("print best option"))) {
                // if currentLine is search add relevant commands and int
                commandToADD = "pbo";

                ValuesForCodes.add(null);
                validCommands.add(commandToADD);
            } else if (currentLine.equals("Remove") || currentLine.equals("remove")) {
                // if currentLine is remove add relevant commands and int
                valueStorage = reader.next();
                commandToADD = "remove";

                ValuesForCodes.add(valueStorage);
                validCommands.add(commandToADD);
            } else if (currentLine.equals("Top 5") || currentLine.equals("top 5")) {
                // grab restaurant
                valueStorage = reader.next();
                // add print command and null value
                commandToADD = "t5";

                validCommands.add(commandToADD);
                ValuesForCodes.add(valueStorage);
            }else if (currentLine.equals("list") || currentLine.equals("List")) {
                    // add print command and null value
                    commandToADD = "l";

                    validCommands.add(commandToADD);
                    ValuesForCodes.add(null);
            } else {
                // add invalid command and null value
                commandToADD = "invalid command";

                validCommands.add(commandToADD);
                ValuesForCodes.add(null);
            }
        }
        //call operate_BST method;
        operate_BST(validCommands,ValuesForCodes);
    }

    /* *                  operate_BST
     * This method takes the arrayLists made in process() and reads the commands
     * then those commands are implemented in the BST and reported on by writeToFile()
     *
     *
     * @param are the array lists that tracked the commands in their corresponding ints
     * @return void
     */
    public void operate_BST(ArrayList<String> validCommands, ArrayList<String> valuesForCodes) throws IOException {
        ArrayList<String> insertDuplicates = new ArrayList<>(); // list to track duplicate inserts
        BST<FastFoodNutritionInfo> tree = new BST<>(); // BST

        // Loop for all commands
       for(int i = 0; i < validCommands.size(); i++) {
          switch (validCommands.get(i)) {
               case "insert":
                    // variables
                    boolean isUniqueInsert = true; // tracks if current num for insertion is a duplicate
                    String stringForInsert = valuesForCodes.get(i); // insert String

                    // Ensure insert # is unique
                    if(insertDuplicates.isEmpty()){
                        insertDuplicates.add(stringForInsert);
                    } else {
                        for (String nonUniqueValues : insertDuplicates) {
                           if(nonUniqueValues.equals(stringForInsert)){
                               isUniqueInsert = false;
                           }
                        }

                    }
                    // record and insert  unique values
                 if(isUniqueInsert) {
                     FastFoodNutritionInfo ffnToInsert = FastFoodNutritionInfo.getFFNFromItem(valuesForCodes.get(i));

                     tree.insert(ffnToInsert);
                     writeToFile("insert " + ffnToInsert, "./result.txt");
                 }
                    break;
                case "remove":
                    // remove and report values if possible
                    FastFoodNutritionInfo ffnToRemove = FastFoodNutritionInfo.getFFNFromItem(valuesForCodes.get(i));
                    FastFoodNutritionInfo removeResult = tree.remove(ffnToRemove);
                    if(removeResult != null){
                        writeToFile("remove " + ffnToRemove, "./result.txt");

                    } else{
                        // report failure
                        writeToFile("remove failed", "./result.txt");
                    }
                    break;
                case "Rank options":
                    // print
                    int treeSize = tree.size();
                    writeToFile(tree.rankedCaloricPrint(treeSize), "./result.txt");
                    break;

                        break;
                case "print best option":
                    // find the lowest calorie option
                    FastFoodNutritionInfo pboSearchResult = tree.getMin(tree.getRoot()).getElement();
                    if(pboSearchResult != null){
                        writeToFile("Your best option is " + pboSearchResult + "/n it has " + pboSearchResult.getCalories()
                                + " calories", "./result.txt");

                } else{
                        // report failure
                        writeToFile("Empty Tree, best option search failed", "./result.txt");
                    }
                    break;
                case "l":
                    // print
                    FastFoodNutritionInfo.printFFNData();
                    writeToFile("printed Database", "./result.txt");
                    break;

                // default case for Invalid Command
                case "invalid command" : writeToFile("Invalid Command", "./result.txt");
                break;
                 }
            }
        }



    /* *                    writeToFile
     * This method takes in a string and writes on an either already existing or created file
     *
     * @param are the string and filePath
     * @return void
     */
  /*  public void writeToFile(String content, String filePath) throws IOException {
        // create file variable
        File myFile = new File(filePath);

        // ensure it exists or create one
        if (!myFile.exists()) {
            try {
                myFile.createNewFile();
            } catch (IOException e) {

            }
        }

        FileWriter dataTyper = new FileWriter(myFile, true); // file writer
        dataTyper.write(content +"\n"); // write given string and new line

        dataTyper.close(); // close file to preserve data
        */
    }
        }
