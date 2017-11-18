import java.util.List;

public class Main {
    public static void main(String[] args) {
        // declare variables
        MainService ms = MainService.getInstance();
        IBaseIO bio = new FileIO();
        IBaseRequestHandler requestHandler = new AllocateSeatRequestHandler();
        IBaseResponseHandler responseHandler = new AllocateSeatResponseHandler();
        AllocateSeatRequest seatRequest = new AllocateSeatRequest();
        AllocateSeatResponse seatResponse = new AllocateSeatResponse();
        ISeatAllocator seatAllocator = new BinPackSeatAllocator();
        MovieTheaterList mtlist = new MovieTheaterList();
        String baseDir = "files/";
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        int theaterID = 0;
        String type = "batch";
        String inputPath = baseDir + inputFile;
        String outputPath = baseDir + outputFile;


        // set states
        ms.setFileIO(bio);
        ms.registerRequestHandler(requestHandler);
        ms.registerResponseHandler(responseHandler);
        ms.registerSeatAllocator(seatAllocator);


        // dummy database
        MovieTheater mt1 = new MovieTheater("MT1", 10, 20);
        mtlist.addMovieTheater(mt1);
        ms.setMovieTheater(mtlist);


        // simulate real request
        seatRequest.setData(theaterID, type, inputPath, outputPath);
//        ms.setConfig(new Config(inputFile, outputFile));


        ms.setRequest(seatRequest);
        ms.calculateSeats();
        seatResponse = ms.getResponse();
        ms.writeResult();

    }
}