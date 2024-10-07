/**
 * @ Parser.java
 * @ This program implements that reads and processing input commands from a user provided file
 * @ author: Destiny
 * @ date: Oct 5, 2024
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
        ArrayList<String> valuesForCodes = new ArrayList<>(); // store item names needed for each command
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
                // if currentLine is insert add command insert and add the company and item name to Array
                valueStorage = reader.nextLine().trim(); // trim gets rid of leading spaces
                commandToADD = "insert";

                valuesForCodes.add(valueStorage);
                validCommands.add(commandToADD);

            } else if ((currentLine.equals("PrintBestOption") || currentLine.equals("printbestoption"))) {
                // if currentLine is PrintBestOption add relevant command
                commandToADD = "pbo";

                valuesForCodes.add(null);
                validCommands.add(commandToADD);

            } else if (currentLine.equals("Remove") || currentLine.equals("remove")) {
                // if currentLine is remove add relevant commands and String
                valueStorage = reader.nextLine();
                commandToADD = "remove";

                valuesForCodes.add(valueStorage);
                validCommands.add(commandToADD);
            } else if (currentLine.equals("rankoptions") || currentLine.equals("RankOptions")) {
                // add print rank option and null value
                commandToADD = "ro";

                validCommands.add(commandToADD);
                valuesForCodes.add(null);
            }else if (currentLine.equals("list") || currentLine.equals("List")) {
                    // add list command and null value
                    commandToADD = "l";

                    validCommands.add(commandToADD);
                    valuesForCodes.add(null);
            } else {
                // add invalid command and null value
                commandToADD = "invalid command";

                validCommands.add(commandToADD);
                valuesForCodes.add(null);

            }
        }
        //call operate_BST method;
        operate_BST(validCommands,valuesForCodes);
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
                     // take full String
                     String fullID = valuesForCodes.get(i);

                     // Split the line by commas
                     String[] formattedID= fullID.split(",");


                     // Check was formatted correctly with commas
                     if (formattedID.length >= 2) {
                     String insertCompany = formattedID[0];
                     String insertItem = formattedID[1];

                     // get the item
                     FastFoodNutritionInfo ffnToInsert = FastFoodNutritionInfo.getFFNFromItemAndCompany(insertItem,insertCompany);
                        // check for case item isn't in database
                         if(ffnToInsert == null){
                             writeToFile("Insert Failed: item not in database", "./result.txt");
                     } else {
                             // insert item and report back to user
                         tree.insert(ffnToInsert);
                         writeToFile("inserted " + ffnToInsert, "./result.txt");
                     }
                 } else{
                         // for case where item is missing commas or company
                         writeToFile("Insert Failed: item not formatted correctly", "./result.txt");
                     }
                 }
                    break;
                case "remove":
                    // get the item
                    String fullID = valuesForCodes.get(i);
                    // Split the line by commas
                    String[] formattedID= fullID.split(",");
                    // grab String data
                    String insertCompany = formattedID[0].trim();
                    String insertItem = formattedID[1].trim();

                    // remove and report values if possible
                    FastFoodNutritionInfo ffnToRemove = FastFoodNutritionInfo.getFFNFromItemAndCompany(insertItem, insertCompany);
                  if(ffnToRemove != null){
                      // check if item doesn't exist in database
                      FastFoodNutritionInfo removeResult = tree.remove(ffnToRemove);
                      if(removeResult != null){
                          // remove item report back to user
                          writeToFile("remove " + ffnToRemove, "./result.txt");
                      }
                  } else{
                      // case if item is not in current BST
                        writeToFile("remove failed", "./result.txt");
                    }
                    break;
                case "ro":
                    // get tree size
                    int treeSize = tree.size();

                    // print items ranked by calorie amount
                    tree.rankedCaloricPrint(treeSize);
                    // check if tree is empty
                    if(treeSize == 0) {
                        writeToFile("Empty Tree, Rank Options failed", "./result.txt");

                    } else {
                        // report back to user
                        writeToFile("Printed a ranking of your options", "./result.txt");

                    }
                    break;
                case "pbo":
                    // find the lowest calorie option
                    if (tree.getRoot() != null){
                        FastFoodNutritionInfo pboSearchResult = tree.getMin(tree.getRoot()).getElement();

                        // report back to user
                        writeToFile("Your best option is " + pboSearchResult, "./result.txt");
                    } else{
                        // report failure if tree empty
                        writeToFile("Empty Tree, best option search failed", "./result.txt");
                }
                    break;
                case "l":
                    // set list boolean true
                    Proj1.setListWasCalled(true);

                    // report back to user
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
  public void writeToFile(String content, String filePath) throws IOException {
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
    }
        }
