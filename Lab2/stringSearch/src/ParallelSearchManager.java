import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParallelSearchManager implements Runnable {
    private ArrayList<Thread> threads = new ArrayList<>();
    private ArrayList<File> found = new ArrayList<>();
    private String search;
    private File path;
    private File[] files;
    private Thread thr;

    private long time;

    public ParallelSearchManager(String search, File path) {
        super();
        this.time = System.nanoTime();
        this.search = search;
        this.path = path;
        files = path.listFiles();
        thr = new Thread (this);
        thr.start();
    }

    public void searchingFolders(File[] files) throws InterruptedException {
        if (files == null) return;
        for (File i : files) {
            if (i.isFile()) {
                Thread newThread = new Thread(new ParallelSearchingInFileThread(i, this));
                newThread.start();
                threads.add(newThread);
            }
            if (i.isDirectory()) {
                Thread newThread = new Thread(new ParallelSearchingFolderThread(i.listFiles(), this));
                newThread.start();
                threads.add(newThread);
            }
        }
    }

    public void searchingInFile(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        int lineID = 0;
        Pattern pattern =  Pattern.compile(this.search);
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

    @Override
    public void run() {
        try {
            searchingFolders(files);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (found.size() == 0) {
            System.out.println("Nothing was found!");
        } else {
            System.out.println();
            for (File i : found) {
                System.out.println(i.getAbsoluteFile());
            }
        }
        System.out.println(String.format("Program running time %s ms.", (System.nanoTime() - this.time) / 1000000));
    }
}