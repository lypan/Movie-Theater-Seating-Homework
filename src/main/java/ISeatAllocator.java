import java.util.List;

public interface ISeatAllocator {
    public void allocate();
    public void setData(MovieTheater mt, List<String> inputData);
    public void printData();
    public List<String> getResult();
}
