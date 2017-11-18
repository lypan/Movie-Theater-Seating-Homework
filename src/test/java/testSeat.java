import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testSeat {
    // seat
    @Test
    public void seatConstructor() {
        Seat seat = new Seat("normal", false, "name");
        seat.setReservationID(0);
        assertEquals("normal", seat.type);
        assertEquals(false, seat.occupied);
        assertEquals("name", seat.name);
        assertEquals(0, seat.reservationID);
    }
    // seat row
    @Test
    public void seatRowConstructor() {
        SeatRow seatRow = new SeatRow(1);
        assertEquals(1, seatRow.seatRowID);
        assertEquals(0, seatRow.getSeatsSize());
        Seat seat = new Seat("normal", false, "name");
        seatRow.addSeat(seat);
        assertEquals(1, seatRow.getSeatsSize());
        assertEquals(seat, seatRow.getSeat(0));

    }
    // seat pool
    @Test
    public void seatPoolConstructor() {
        SeatPool seatPool = new SeatPool(5, 3);
        assertEquals(5, seatPool.getSeatRowSize());
        assertEquals(seatPool.getSeatRow(0), seatPool.getSeatRow(0));
    }


}
