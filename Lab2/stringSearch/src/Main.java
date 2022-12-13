import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the search path: ");
        File searchPath = new File(scanner.nextLine());

        System.out.print("Enter the search string: ");
        String searchString = scanner.nextLine();

        System.out.println("Select the search method:");
        System.out.println("1 - Parallel search");
        System.out.println("2 - Sequential search");
        System.out.println("3 - Sequential search (with NIO)");
        System.out.print("Method: ");

        switch (scanner.nextInt()) {
            case 1:
                new ParallelSearchManager(searchString, searchPath);
                break;
            case 2:
                (new SequentialSearching()).search(searchPath, searchString);
                break;
            case 3:
                (new SequentialSearchingWithNIO()).search(searchPath, searchString);
                break;
            default:
                System.out.println("Unknown search method...");
        }
    }
}