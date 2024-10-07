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
                // if currentLine is insert add command insert and the value to insert to Array Lists
                valueStorage = reader.nextLine().trim();
                commandToADD = "insert";

                valuesForCodes.add(valueStorage);
                validCommands.add(commandToADD);

            } else if ((currentLine.equals("PrintBestOption") || currentLine.equals("printbestoption"))) {
                // if currentLine is search add relevant commands and int
                commandToADD = "pbo";

                valuesForCodes.add(null);
                validCommands.add(commandToADD);

            } else if (currentLine.equals("Remove") || currentLine.equals("remove")) {
                // if currentLine is remove add relevant commands and int
                valueStorage = reader.nextLine();
                commandToADD = "remove";

                valuesForCodes.add(valueStorage);
                validCommands.add(commandToADD);
            } else if (currentLine.equals("rankoptions") || currentLine.equals("RankOptions")) {
                // add print command and null value
                commandToADD = "ro";

                validCommands.add(commandToADD);
                valuesForCodes.add(null);
            }else if (currentLine.equals("list") || currentLine.equals("List")) {

                    // add print command and null value
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

                     String fullID = valuesForCodes.get(i);

                     // Split the line by commas
                     String[] formattedID= fullID.split(",");


                     // grab String data
                     if (formattedID.length >= 2) {
                     String insertCompany = formattedID[0];
                     String insertItem = formattedID[1];



                     FastFoodNutritionInfo ffnToInsert = FastFoodNutritionInfo.getFFNFromItemAndCompany(insertItem,insertCompany);
                         if(ffnToInsert == null){
                             writeToFile("Insert Failed: item not in database", "./result.txt");
                     } else {
                         tree.insert(ffnToInsert);
                         writeToFile("inserted " + ffnToInsert, "./result.txt");
                     }
                 } else{
                         writeToFile("Insert Failed: item not formatted correctly", "./result.txt");
                     }
                 }
                    break;
                case "remove":
                    String fullID = valuesForCodes.get(i);

                    // Split the line by commas
                    String[] formattedID= fullID.split(",");

                    // grab String data
                    String insertCompany = formattedID[0].trim();
                    String insertItem = formattedID[1].trim();

                    // remove and report values if possible
                    FastFoodNutritionInfo ffnToRemove = FastFoodNutritionInfo.getFFNFromItemAndCompany(insertItem, insertCompany);
                  if(ffnToRemove != null){
                      FastFoodNutritionInfo removeResult = tree.remove(ffnToRemove);
                      if(removeResult != null){
                          writeToFile("remove " + ffnToRemove, "./result.txt");
                      }
                  } else{
                        // report failure
                        writeToFile("remove failed", "./result.txt");
                    }
                    break;
                case "ro":
                    // print
                    int treeSize = tree.size();
                    tree.rankedCaloricPrint(treeSize);
                    if(treeSize == 0) {
                        writeToFile("Empty Tree, Rank Options failed", "./result.txt");

                    } else {
                        writeToFile("Printed a ranking of your options", "./result.txt");

                    }
                    break;
                case "pbo":
                    // find the lowest calorie option
                    if (tree.getRoot() != null){
                        FastFoodNutritionInfo pboSearchResult = tree.getMin(tree.getRoot()).getElement();
                        writeToFile("Your best option is " + pboSearchResult, "./result.txt");
                    } else{
                        writeToFile("Empty Tree, best option search failed", "./result.txt");
                }
                    break;
                case "l":
                    // print
                    Proj1.setListWasCalled(true);
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
