import java.util.Scanner;

// Created TheaterSeating class allowing reserving, cancelling and suggesting seating.
public class TheaterSeating {
    private static final int ROWS = 5;
    private static final int COLUMNS = 10;
    private static final char AVAILABLE = 'O';
    private static final char RESERVED = 'X';

    private char[][] seatingChart;


    // Created constructor to initialize seating chart.
    public TheaterSeating() {
        seatingChart = new char[ROWS][COLUMNS];
        initializeSeatingChart();
    }

    // Initializes available seats.
    private void initializeSeatingChart() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                seatingChart[i][j] = AVAILABLE;
            }
        }
    }


    // Displays current seating chart.
    public void displaySeatingChart() {
        System.out.println("Current seating chart: ");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(seatingChart[i][j] + " ");
            }

            System.out.println();
        }
    }

    // Reserves available seats, suggests seats if chosen one is not available.
    public void reserveSeat (int row, int column) {
        if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
            System.out.println("Invalid seat selected, please choose a valid seat.");
            return;
        }

        if (seatingChart[row][column] == RESERVED) {
            System.out.println("Seat is already reserved, suggesting an available seat now...");
            suggestAvailableSeat();
        } else {
            seatingChart[row][column] = RESERVED;
            System.out.println("Seat reserved successfully at row " + (row + 1) + ", column  " + (column + 1) + ".");
        }
    }

    // Cancels reserved seat if it is reserved.
    public void cancelReservation(int row, int column) {
        if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
            System.out.println("Invalid seat selected, please choose a valid seat.");
            return;
        }

        if (seatingChart[row][column] == AVAILABLE) {
            System.out.println("Seat at row " + (row + 1) + ", column " + (column + 1) + "is not currently reserved.");
        } else {
            seatingChart[row][column] = AVAILABLE;
            System.out.println("Reservation cancelled successfully on row " + (row + 1) + ", column " + (column + 1) + ".");
        }
    }

    // Suggests available seat.
    private void suggestAvailableSeat() {
        for (int i = 0; i < ROWS; i ++) {
            for (int j = 0; j < COLUMNS; j ++) {
                if (seatingChart[i][j] == AVAILABLE) {
                    System.out.println("Suggesting seat: row " + (i + 1) + ", column " + (j + 1) + ".");
                    return;
                }
            }
        }

        System.out.println("No available seats at this time.");
    }

    // Created main method.
    public static void main(String[] args) {
        TheaterSeating theater = new TheaterSeating();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Display seating chart");
            System.out.println("2. Reserve a seat");
            System.out.println("3. Cancel reservation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice from available options: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                theater.displaySeatingChart();
                break;

                case 2:
                System.out.print("Enter row number (1-" + ROWS + "): ");
                int reserveRow = scanner.nextInt() - 1;
                System.out.print("Enter column number (1-" + COLUMNS + "): ");
                int reserveColumn = scanner.nextInt() - 1;
                theater.reserveSeat(reserveRow, reserveColumn);
                break;

                case 3:
                System.out.print("Enter row number (1-" + ROWS + "): ");
                int cancelRow = scanner.nextInt() - 1;
                System.out.print("Enter column number (1-" + COLUMNS + "): ");
                int cancelColumn = scanner.nextInt() - 1;
                theater.cancelReservation(cancelRow, cancelColumn);
                break;

                case 4:
                System.out.println("Exiting...");
                scanner.close();
                return;

                default:
                System.out.println("Invalid choice, please choose from available options.");

            }
        }
    }
}