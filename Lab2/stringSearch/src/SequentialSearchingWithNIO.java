import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class SequentialSearchingWithNIO {
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
                try {
                    String stringFile = "";

                    RandomAccessFile aFile = new RandomAccessFile(file.getAbsoluteFile(), "r");
                    FileChannel inChannel = aFile.getChannel();
                    ByteBuffer buf = ByteBuffer.allocate(48);

                    int bytesRead = inChannel.read(buf);
                    while (bytesRead != -1) {

                        buf.flip();

                        while(buf.hasRemaining()){
                            stringFile += (char) buf.get();
                        }

                        buf.clear();
                        bytesRead = inChannel.read(buf);
                    }
                    aFile.close();

                    if (stringFile.contains(this.string)) {
                        found.add(file);
                    }
                } catch(Exception e) {
                    System.out.println("Error:"+e);
                }
            }
        }
    }
}
