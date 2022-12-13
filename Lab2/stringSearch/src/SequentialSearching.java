import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SequentialSearching {
    private ArrayList<File> found = new ArrayList<>();
    private String string;
    public void search(File path, String string) throws FileNotFoundException {
        long time = System.nanoTime();
        File[] files = path.listFiles();
        this.string = string;

        assert files != null;
        this.getFiles(files);

        if (found.size() == 0) {
            System.out.println("Nothing was found!");
        } else {
            System.out.println();
            for (File i : found) {
                System.out.println(i.getAbsoluteFile());
            }
        }
        System.out.println(String.format("Program running time %s ms.", (System.nanoTime() - time) / 1000000));
    }
    public void getFiles(File[] files) throws FileNotFoundException {
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file.listFiles());
            } else {
                Scanner fileScanner = new Scanner(file);
                int lineID = 0;
                Pattern pattern =  Pattern.compile(this.string);
                Matcher matcher = null;

                while(fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    lineID++;
                    matcher = pattern.matcher(line);
                    if(matcher.find()){
                        found.add(file);
                    }
                }
            }
        }
    }
}
