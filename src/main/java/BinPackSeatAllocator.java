import java.util.*;
/**
 * A seat allocator using my modified first-fit decreasing algorithm
 * @author liang yu pan
 */
public class BinPackSeatAllocator implements ISeatAllocator {
    private MovieTheater movieTheater;
    private List<ReservationEntry> requestEntryList = new ArrayList<>();
    private List<ReservationEntry> inValidRequestList = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private int seatCount = 0;


    /**
     * Allocate seats to theater seats
     */
    @Override
    public void allocate() {
        if(requestEntryList == null || requestEntryList.size() == 0)return;
        int MAX_ROW = movieTheater.row;
        int MAX_COL = movieTheater.col;
        int rowIndex = 0;
        int colIndex = 0;
        int seatReservationID = 0;
        int seatNumber = 0;
        int occupiedRow = 1;
        boolean toRight = true;

        // use modified first fit decreasing bin pack algorithm to allocate seats
        // try to assign group to in the same row
        // if the row cannot fit it, divided the group to two group, one seat behind each other
        // time complexity: sort all requests(n) first O(nlogn) & assign group to theater seats(m) O(m)
        for(int i = 0; i < requestEntryList.size(); i ++) {
            ReservationEntry currentEntry = requestEntryList.get(i);
            seatReservationID = currentEntry.requestID;
            seatNumber = currentEntry.seatNumber;
            Seat seat;

            while(seatNumber > 0) {
                seat = movieTheater.seatPool.getSeatRow(rowIndex).getSeat(colIndex);
                seat.setReservationID(seatReservationID);
                seatNumber --;

                if(toRight) {

                    colIndex ++;
                    if(colIndex == MAX_COL) {
                        toRight = false;
                        colIndex --;
                        rowIndex ++;
                        occupiedRow ++;
                    }
                }
                else {
                    colIndex --;
                    if(colIndex == -1) {
                        toRight = true;
                        colIndex ++;
                        rowIndex ++;
                        occupiedRow ++;
                    }
                }
            }
        }

        // if the whole theater is not all full, move the whole seats around the center
        // since center is most people's preference when choosing seats

        // correct rowIdx from last loop
        if(rowIndex == MAX_ROW){
            rowIndex --;
            occupiedRow --;
        }
        else if(toRight && colIndex == 0 && movieTheater.seatPool.getSeatRow(rowIndex).getSeat(colIndex).reservationID == 0 )occupiedRow --;
        else if(!toRight && colIndex == MAX_COL - 1 && movieTheater.seatPool.getSeatRow(rowIndex).getSeat(colIndex).reservationID == 0 )occupiedRow --;

        // calculate how many rows need to move current center into theater center
        int occupiedCenter = occupiedRow / 2;
        int rowCenter = MAX_ROW / 2;
        int offset = rowCenter - occupiedCenter;

        // move seats
        int countLine = 0;
        int backRowindex = occupiedRow - 1;
        while(offset > 0 && countLine < occupiedRow) {
            for(int j = 0; j < MAX_COL; j ++) {
                int oldRegisterID = movieTheater.seatPool.getSeatRow(backRowindex).getSeat(j).reservationID;
                movieTheater.seatPool.getSeatRow(backRowindex + offset).getSeat(j).setReservationID(oldRegisterID);
                movieTheater.seatPool.getSeatRow(backRowindex).getSeat(j).setReservationID(0);
            }
            countLine ++;
            backRowindex --;
        }

    }
    /**
     * Set MovieTheater object information and file's input data to allocator
     * @param movieTheater
     *          - An object store information about movie theater
     * @param inputData
     *          - Each line of a file
     */
    @Override
    public void setData(MovieTheater movieTheater, List<String> inputData) {
        requestEntryList = new ArrayList<>();
        inValidRequestList = new ArrayList<>();
        this.movieTheater = movieTheater;
        this.data = inputData;
        seatCount = 0;

        // check valid data
        // gather requests by reqest sequence until theater cannot fit in anymore
        for(int i = 0; i < data.size(); i ++) {
            String line = data.get(i).trim();
            String[] tokens = line.split(" ");
            if(tokens == null)continue;
            int requestID = Integer.parseInt(tokens[0].substring(1, tokens[0].length()).trim());
            int seatNumber = Integer.parseInt(tokens[1].trim());
            if(seatNumber == 0)continue;
            seatCount += seatNumber;

            if(seatCount <= this.movieTheater.getSeatSize()){
                requestEntryList.add(new ReservationEntry(requestID, seatNumber));
            }
            else {
                inValidRequestList.add(new ReservationEntry(requestID, seatNumber));
            }
        }
        // sort requests by its seat number size in decreasing order
        // because I use the modified bin pack first fit decreasing algorithm
        Collections.sort(requestEntryList, (ReservationEntry r1, ReservationEntry r2) -> Integer.compare(r2.seatNumber, r1.seatNumber));
    }

    /**
     * A helper function to show result and for debug conveniently
     */
    @Override
    public void printData() {
        SeatPool seatPool = this.movieTheater.getSeatPool();
        System.out.println("Some requests have the seats in the theater!");
        for(int i = 0; i < seatPool.getSeatRowSize(); i ++) {
            SeatRow seatRow = seatPool.getSeatRow(i);
            for(int j = 0; j < seatRow.getSeatsSize(); j ++) {
                Seat seat = seatRow.getSeat(j);
                System.out.print(seat.reservationID + ",\t");
            }
            System.out.print("\n");
        }

        if(inValidRequestList.size() != 0) {
            System.out.println("Some requests cannot be handled since not enough seats!");

            for(int i = 0; i < inValidRequestList.size(); i ++) {
                int requestID = inValidRequestList.get(i).requestID;
                int seatNumber = inValidRequestList.get(i).seatNumber;
                System.out.println("Request: " + requestID + ", SeatNumber: " + seatNumber);
            }
        }
    }
    /**
     * A helper function to return the result as a list of string
     */
    @Override
    public List<String> getResult() {
        List<String> result = new ArrayList<>();
        Map<String, String> map = new TreeMap<>();

        // use tree map to save related seat to the same request id
        SeatPool seatPool = movieTheater.seatPool;
        for(int i = 0; i < seatPool.getSeatRowSize(); i ++) {
            SeatRow seatRow = seatPool.getSeatRow(i);
            for(int j = 0; j < seatRow.getSeatsSize(); j ++) {
                Seat seat = seatRow.getSeat(j);
                int reservationID = seat.reservationID;
                String seatName = seat.name;

                if(reservationID != 0) {
                    String key = "";
                    String value = "";

                    if(reservationID < 10)key = "R00" + String.valueOf(reservationID);
                    else if(reservationID < 100)key = "R0" + String.valueOf(reservationID);
                    else key = "R" + String.valueOf(reservationID);

                    if(!map.containsKey(key)) {
                        map.put(key, value);
                    }
                    value = map.get(key);
                    map.put(key, value + ", " + seatName);
                }
            }
        }

        // iterate the tree hashmap to get result by request order number
        for(Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            result.add(key + value.substring(1, value.length()));
        }

        return result;
    }

}
