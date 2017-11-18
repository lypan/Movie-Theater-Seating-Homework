import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MovieTheaterList {
    List<MovieTheater> theaterList = new ArrayList<>();
    public MovieTheaterList() {

    }

    public void addMovieTheater(MovieTheater theater) {
        theaterList.add(theater);
    }

    public MovieTheater getMovieTheaterByID(int movieTheaderID) {
        return theaterList.get(movieTheaderID);
    }

    public int getMovieTheaterListSize() {
        return theaterList.size();
    }
}
