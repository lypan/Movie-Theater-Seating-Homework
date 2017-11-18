import java.util.List;
/**
 * A main service that exists only one at any time and keep running as simulation of real daemon
 * It receive request and handle request to send response back
 * @author liang yu pan
 */
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
        mainService = getInstance();
    }
    /**
     * Get the only one mainservice by singleton
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
    /**
     * Set file IO handler
     */
    public static void setFileIO(IBaseIO basicIO) {
        MainService.io = basicIO;
    }
    /**
     * Set main service config
     */
    public static void setConfig(Config config) {
        MainService.config = config;
    }
    /**
     * Set request handler
     */
    public static void registerRequestHandler(IBaseRequestHandler requestHandler) {
        MainService.requestHandler = requestHandler;
    }
    /**
     * Set response handler
     */
    public static void registerResponseHandler(IBaseResponseHandler responseHandler) {
        MainService.responseHandler = responseHandler;
    }
    /**
     * Set seat allocator handler
     */
    public static void registerSeatAllocator(ISeatAllocator seatAllocator) {
        MainService.seatAllocator = seatAllocator;
    }
    /**
     * Set request
     */
    public static void setRequest(AllocateSeatRequest request) {
        MainService.request = request;
    }
    /**
     * Return response
     */
    public static AllocateSeatResponse getResponse() {
        return MainService.response;
    }
    /**
     * Do the logic operation on assigning seats
     */
    public static void calculateSeats() {
        int movieTheaterID = MainService.request.targetID;
        MovieTheater mt = MainService.movieTheaterList.getMovieTheaterByID(movieTheaterID);
        List<String> inputData = MainService.io.read(MainService.request.inputPath);

        MainService.seatAllocator.setData(mt, inputData);
        MainService.seatAllocator.allocate();

    }
    /**
     * Add movie theater object
     */
    public static void setMovieTheater(MovieTheaterList movieTheaterList) {
        MainService.movieTheaterList = movieTheaterList;
    }
    /**
     * Write result back to file system
     */
    public static void writeResult() {
        MainService.seatAllocator.printData();
        List<String> result = MainService.seatAllocator.getResult();
        MainService.io.write(MainService.request.outputPath, result);
    }
}
