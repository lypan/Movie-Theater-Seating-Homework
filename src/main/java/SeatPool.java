import java.util.ArrayList;

public class SeatPool {
    private ArrayList<SeatRow> allSeats = new ArrayList<>();

    public SeatPool(int row, int col) {
        for(int i = 0; i < row; i ++) {
            SeatRow seatRow = new SeatRow(i, col);
            String type = "normal";
            Boolean occupied = false;
            for(int j = 0; j < col; j ++) {
                String name = Character.toString((char)(i + 65)) + String.valueOf(j + 1);
                Seat seat = new Seat(type, occupied, name);
                seatRow.addSeat(seat);
            }
            allSeats.add(seatRow);
        }
    }

    public int getSeatRowSize() {
        return allSeats.size();
    }

    public SeatRow getSeatRow(int index) {
        return allSeats.get(index);
    }
}
