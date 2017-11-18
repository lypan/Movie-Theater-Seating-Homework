public class MovieTheater extends AbstractTheater{
    public MovieTheater(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.seatPool = new SeatPool(row, col);
    }
}
