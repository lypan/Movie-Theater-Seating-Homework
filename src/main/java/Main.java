import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Command: --input inputFilePath --output outputFilePath");
        // parse args
        String baseDir = "files/";
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        if(args.length == 2){
            if(args[0].trim().equals("--input"))inputFile = args[1];
            else System.out.println("Wrong option! Use default option instead!");
        }
        else if(args.length == 4) {
            if(args[0].trim().equals("--input") && args[2].trim().equals("--output")){
                inputFile = args[1];
                outputFile = args[3];
            }
            else {
                System.out.println("Wrong option! Use default option instead!");
            }
        }
            // declare variables
        MainService ms = MainService.getInstance();
        IBaseIO bio = new FileIO();
        IBaseRequestHandler requestHandler = new AllocateSeatRequestHandler();
        IBaseResponseHandler responseHandler = new AllocateSeatResponseHandler();
        AllocateSeatRequest seatRequest = new AllocateSeatRequest();
        AllocateSeatResponse seatResponse = new AllocateSeatResponse();
        ISeatAllocator seatAllocator = new BinPackSeatAllocator();
        MovieTheaterList mtlist = new MovieTheaterList();
        int theaterID = 0;
        String type = "batch";
        String inputPath = baseDir + inputFile;
        System.out.println(inputPath);
        String outputPath = baseDir + outputFile;
        System.out.println(outputPath);
        int seatRowSize = 20;
        int seatColSize = 10;

        // set states
        ms.setFileIO(bio);
        ms.registerRequestHandler(requestHandler);
        ms.registerResponseHandler(responseHandler);
        ms.registerSeatAllocator(seatAllocator);


        // dummy database
        MovieTheater mt1 = new MovieTheater("MT1", seatRowSize, seatColSize);
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