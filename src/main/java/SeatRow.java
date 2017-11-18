import java.util.ArrayList;

public class SeatRow {
    private ArrayList<Seat> seats = new ArrayList<>();
    public int seatRowID;

    public SeatRow(int seatRowID) {
        this.seatRowID = seatRowID;
    }

    public int getSeatsSize() {
        return seats.size();
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public Seat getSeat(int index) {
        return seats.get(index);
    }
}
