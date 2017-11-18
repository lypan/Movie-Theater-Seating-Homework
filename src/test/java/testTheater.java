import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testTheater{
        // theater
        @Test
        public void theaterConstructor() {
            MovieTheater movieTheater = new MovieTheater("movieTheater", 5, 3);
            assertEquals(15, movieTheater.getSeatSize());
            assertEquals(movieTheater.seatPool, movieTheater.getSeatPool());
        }
        // theater list
        @Test
        public void theaterListConstructor() {
            MovieTheaterList movieTheaterList = new MovieTheaterList();
            assertEquals(0, movieTheaterList.getMovieTheaterListSize());
            MovieTheater movieTheater = new MovieTheater("movieTheater", 5, 3);
            movieTheaterList.addMovieTheater(movieTheater);
            assertEquals(1, movieTheaterList.getMovieTheaterListSize());
        }
}
