
public class AllocateSeatRequest extends AbstractRequest {

    public void setData(int theaterID, String type, String inputPath, String outputPath) {
        this.targetID = theaterID;
        this.dataType = type;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }
}
