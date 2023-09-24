package system.function.statistics;

import system.PortSystem;
import system.models.Trip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FindTrips {

    public FindTrips() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Finding Trips ======");
        System.out.println("1. Find by days");
        System.out.println("2. Find by date range");
        try {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    FindTripByDay();
                    break;
                case 2:
                    FindTripByDateRange();
                    break;
                default:
                    System.out.println("Please choose the correct option!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please pick a number");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    private void FindTripByDay() {
        System.out.println("===== List Of Trips =====");
        PortSystem.getTripList().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("Enter a date in the format dd-MM-yyyy (or 'q' to exit):");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("q")) {
                System.out.println("Exiting Find Trip by Day.");
                break; // Exit the loop
            }

            try {
                LocalDate parsedDate = LocalDate.parse(userInput, dateFormatter);
                System.out.println("===== Found Trips =====");
                for (Trip trip : PortSystem.getTripList()) {
                    if (trip.getArriveDate().isEqual(parsedDate)) {
                        System.out.println(trip);
                    }
                }
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd-MM-yyyy.");
            }
        }
    }

    private void FindTripByDateRange() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("Enter the start date in the format dd-MM-yyyy (or 'q' to exit):");
            String startDate = scanner.nextLine();

            if (startDate.equalsIgnoreCase("q")) {
                System.out.println("Exiting Find Trip by Day.");
                break; // Exit the loop
            }

            System.out.println("Enter the end date in the format dd-MM-yyyy: ");
            String endDate = scanner.nextLine();

            try {
                LocalDate parsedStartDate = LocalDate.parse(startDate, dateFormatter);
                LocalDate parsedEndDate = LocalDate.parse(endDate, dateFormatter);
                System.out.println("===== Found Trips =====");
                for (Trip trip : PortSystem.getTripList()) {
                    if (!trip.getArriveDate().isBefore(parsedStartDate) && !trip.getArriveDate().isAfter(parsedEndDate)) {
                        System.out.println(trip);
                    }
                }
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd-MM-yyyy.");
            }
        }
    }


}
