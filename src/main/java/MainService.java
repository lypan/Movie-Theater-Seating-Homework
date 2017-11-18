import java.util.List;

public class MainService {
    private static volatile MainService mainService;
    private static IBaseIO io;
    private static Config config;
    private static IBaseRequestHandler requestHandler;
    private static IBaseResponseHandler responseHandler;
    private static AllocateSeatRequest request;
    private static AllocateSeatResponse response;
    private static ISeatAllocator seatAllocator;
    private static MovieTheaterList movieTheaterList;


    private MainService() {
        // TODO: Initialize

    }

    /**
     * Get the only instance of this class.
     *
     * @return the single instance.
     */
    public static MainService getInstance() {
        if (mainService == null) {
            synchronized (MainService.class) {
                if (mainService == null) {
                    mainService = new MainService();
                }
            }
        }
        return mainService;
    }

    public static void setFileIO(IBaseIO basicIO) {
        MainService.io = basicIO;
    }

    public static void setConfig(Config config) {
        MainService.config = config;
    }

    public static void registerRequestHandler(IBaseRequestHandler requestHandler) {
        MainService.requestHandler = requestHandler;
    }

    public static void registerResponseHandler(IBaseResponseHandler responseHandler) {
        MainService.responseHandler = responseHandler;
    }

    public static void registerSeatAllocator(ISeatAllocator seatAllocator) {
        MainService.seatAllocator = seatAllocator;
    }

    public static void setRequest(AllocateSeatRequest request) {
        MainService.request = request;
    }

    public static AllocateSeatResponse getResponse() {
        return MainService.response;
    }

    public static void calculateSeats() {
        int movieTheaterID = MainService.request.targetID;
        MovieTheater mt = MainService.movieTheaterList.getMovieTheaterByID(movieTheaterID);
        List<String> inputData = MainService.io.read(MainService.request.inputPath);

        MainService.seatAllocator.setData(mt, inputData);
        MainService.seatAllocator.allocate();

    }

    public static void setMovieTheater(MovieTheaterList movieTheaterList) {
        MainService.movieTheaterList = movieTheaterList;
    }

    public static void writeResult() {
        MainService.seatAllocator.printData();
        List<String> result = MainService.seatAllocator.getResult();
        MainService.io.write(MainService.request.outputPath, result);
    }
}
