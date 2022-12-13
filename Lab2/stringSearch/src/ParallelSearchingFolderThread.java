import java.io.File;

public class ParallelSearchingFolderThread implements Runnable {
    private ParallelSearchManager manager;
    private File[] files;

    public ParallelSearchingFolderThread(File[] files, ParallelSearchManager manager) {
        this.manager = manager;
        this.files = files;
    }

    @Override
    public void run() {
        try {
            manager.searchingFolders(files);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}