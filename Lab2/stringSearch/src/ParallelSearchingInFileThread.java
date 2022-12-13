import java.io.File;
import java.io.FileNotFoundException;

public class ParallelSearchingInFileThread implements Runnable {
    private ParallelSearchManager manager;
    private File file;

    public ParallelSearchingInFileThread(File file, ParallelSearchManager manager) {
        this.manager = manager;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            manager.searchingInFile(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}