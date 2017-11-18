import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testAllocator {
    static BinPackSeatAllocator binPackSeatAllocator;
    static MovieTheaterList movieTheaterList;
    static MovieTheater movieTheater;
    static List<String> inputData;
    static List<String> resultData;
    static String data;


    @Before
    public void resetVariables() {
        binPackSeatAllocator = new BinPackSeatAllocator();
        movieTheaterList = new MovieTheaterList();
        movieTheater = new MovieTheater("movieTheater", 10, 10);
        movieTheaterList.addMovieTheater(movieTheater);
        inputData = new ArrayList<>();
        resultData = new ArrayList<>();
        data = "";
    }
    @Test
    public void testSingleRequestBelowOneRowSize() {
        data = "R001 9";
        inputData.add(data);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(1, resultData.size());
    }

    @Test
    public void testSingleRequestEqualOneRowSize() {
        data = "R001 10";
        inputData.add(data);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(1, resultData.size());
    }

    @Test
    public void testSingleRequestLargerOneRowSize() {
        data = "R001 11";
        inputData.add(data);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(1, resultData.size());
    }

    @Test
    public void testSingleRequestEqualAllSeatsSize() {
        data = "R001 100";
        inputData = new ArrayList<>();
        resultData = new ArrayList<>();
        inputData.add(data);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(1, resultData.size());
    }

    @Test
    public void testSingleRequestLargerAllSeatsSize() {
        data = "R001 101";
        inputData = new ArrayList<>();
        resultData = new ArrayList<>();
        inputData.add(data);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(0, resultData.size());
    }

    @Test
    public void testTwoRequestLessOneRowSize() {
        List<String> resultData;
        String data1 = "";
        String data2 = "";

        data1 = "R001 5";
        data2 = "R002 3";
        inputData.add(data1);
        inputData.add(data2);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(2, resultData.size());
    }


    @Test
    public void testTwoRequestEqualOneRowSize() {
        String data1 = "";
        String data2 = "";

        data1 = "R001 5";
        data2 = "R002 5";
        inputData.add(data1);
        inputData.add(data2);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(2, resultData.size());
    }

    @Test
    public void testTwoRequestLargerOneRowSize() {
        String data1 = "";
        String data2 = "";

        data1 = "R001 6";
        data2 = "R002 6";
        inputData.add(data1);
        inputData.add(data2);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(2, resultData.size());
    }

    @Test
    public void testTwoRequestEqualAllSeatsSize() {
        String data1 = "";
        String data2 = "";

        data1 = "R001 50";
        data2 = "R002 50";
        inputData.add(data1);
        inputData.add(data2);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(2, resultData.size());
    }

    @Test
    public void testTwoRequestLargerAllSeatsSize() {
        String data1 = "";
        String data2 = "";

        data1 = "R001 60";
        data2 = "R002 60";
        inputData.add(data1);
        inputData.add(data2);
        binPackSeatAllocator.setData(movieTheater, inputData);
        binPackSeatAllocator.allocate();
        resultData = binPackSeatAllocator.getResult();
        assertEquals(1, resultData.size());
    }
}
