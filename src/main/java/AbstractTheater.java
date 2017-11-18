public class AbstractTheater {
    String name;
    int row;
    int col;
    public SeatPool seatPool;
    public int getSeatSize() {
        return row * col;
    }
    public SeatPool getSeatPool() {
        return seatPool;
    }

}
