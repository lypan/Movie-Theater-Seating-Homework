import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testFileIO {
    static FileIO fileIO;
    @BeforeClass
    public static void setUp(){
        fileIO = new FileIO();
    }

    @Test(expected = NullPointerException.class)
    public void writeFileWithoutContent() {
        fileIO.write("test", null);
    }
    @Test
    public void writeFile() {
        List<String> content = new ArrayList<>();
        fileIO.write("test", content);
    }
    @Test(expected = NullPointerException.class)
    public void readFileWithoutFileName() {
        fileIO.read(null);
    }
    @Test
    public void readFile() {
        fileIO.read("test");
    }


}
