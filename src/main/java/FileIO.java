import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileIO implements IBaseIO {
    @Override
    public List<String> read(String filePath) {
        List<String> rows = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while (line != null) {
                rows.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return rows;
    }

    @Override
    public void write(String filePath, List<String> content) {
        Path file = Paths.get(filePath);
        try {
            Files.write(file, content, Charset.forName("UTF-8"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
