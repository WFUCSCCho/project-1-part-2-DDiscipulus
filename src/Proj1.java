import java.io.FileNotFoundException;
import java.io.IOException;

public class Proj1 {
    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        new Parser(args[0]);
    }
}