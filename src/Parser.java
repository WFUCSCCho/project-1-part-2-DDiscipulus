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
        ArrayList<Integer> ValuesForCodes = new ArrayList<Integer>(); // store int number needed for each command
        String currentLine; // stores the current input

        // Processing occurs below
        while(reader.hasNext()) {
            // more variables
            int valueStorage; // stores int inputs
            String commandToADD; // string to store commands for ArrayList

            // reads next line until space of text
            currentLine = reader.next();

            // Remove redundant spaces for each input command
            if (currentLine.equals("")) {

            } else if (currentLine.equals("Insert") || currentLine.equals("insert")) {
                // if currentLine is insert add command insert and the value to insert to Array Lists
                valueStorage = reader.nextInt();
                commandToADD = "insert";

                ValuesForCodes.add(valueStorage);
                validCommands.add(commandToADD);

            } else if ((currentLine.equals("Search") || currentLine.equals("search"))) {
                // if currentLine is search add relevant commands and int
                valueStorage = reader.nextInt();
                commandToADD = "search";

                ValuesForCodes.add(valueStorage);
                validCommands.add(commandToADD);
            } else if (currentLine.equals("Remove") || currentLine.equals("remove")) {
                // if currentLine is remove add relevant commands and int
                valueStorage = reader.nextInt();
                commandToADD = "remove";

                ValuesForCodes.add(valueStorage);
                validCommands.add(commandToADD);
            } else if (currentLine.equals("Print") || currentLine.equals("print")) {
                // add print command and null value
                commandToADD = "print";

                validCommands.add(commandToADD);
                ValuesForCodes.add(null);
            } else {
                // add invalud command and null value
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
    public void operate_BST(ArrayList<String> validCommands, ArrayList<Integer> valuesForCodes) throws IOException {
        ArrayList<Integer> insertDuplicates = new ArrayList<Integer>(); // list to track duplicate inserts
        BST<Integer> tree = new BST<>(); // BST

        // Loop for all commands
        for(int i = 0; i < validCommands.size(); i++) {
            switch (validCommands.get(i)) {
                case "insert":
                    // variables
                    boolean isUniqueInsert = true; // tracks if current num for insertion is a duplicate
                    int numForInsert = valuesForCodes.get(i); // insert num

                    // Ensure insert # is unique
                    if(insertDuplicates.isEmpty()){
                        insertDuplicates.add(numForInsert);
                    } else {
                        for (Integer nonUniqueValues : insertDuplicates) {
                           if(nonUniqueValues == numForInsert){
                               isUniqueInsert = false;
                           }
                        }

                    }
                    // record and insert  unique values
                 if(isUniqueInsert) {
                     tree.insert(valuesForCodes.get(i));
                     writeToFile("insert " + valuesForCodes.get(i), "./result.txt");
                 }
                    break;
                case "remove":
                    // remove and report values if possible
                    Integer removeResult = tree.remove(valuesForCodes.get(i));
                    if(removeResult != null){
                        writeToFile("remove " + valuesForCodes.get(i), "./result.txt");

                    } else{
                        // report failure
                        writeToFile("remove failed", "./result.txt");
                    }
                    break;
                case "search":
                    // find value if possible
                    Integer searchResult = tree.search(valuesForCodes.get(i));
                    if(searchResult != null){
                        writeToFile("found " + valuesForCodes.get(i), "./result.txt");

                } else{
                        // report failure
                        writeToFile("search failed", "./result.txt");
                    }
                    break;
                case "print":
                    // print
                    writeToFile(tree.print(), "./result.txt");
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
