import java.util.List;

public interface IBaseIO {
    public List<String> read(String filePath);
    public void write(String filePath, List<String> content);
}
