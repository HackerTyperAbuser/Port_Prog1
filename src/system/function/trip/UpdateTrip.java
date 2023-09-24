package system.function.trip;

import system.PortSystem;

import system.models.Trip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateTrip {

    public UpdateTrip() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        PortSystem.getTripList().forEach(System.out::println);
        Trip selectedTrip = null;
        while (true) {
            try {
                System.out.println("Enter trip ID to modify: ('q' to exit)");
                String tripID = scanner.nextLine();
                if (tripID.equalsIgnoreCase("q")) {
                    break;
                } else {
                    boolean found = false;

                    for (Trip trip : PortSystem.getTripList()) {
                        if (trip.getId().equalsIgnoreCase(tripID)) {
                            selectedTrip = trip;
                            found = true;
                            break; // Stop searching after finding and displaying the container
                        }
                    }

                    if (!found) {
                        System.out.println("Trip not found");
                    } else {

                        while (true) {
                            System.out.println("Choose what to update");
                            System.out.println("1. Departure Date");
                            System.out.println("2. Arrival Date");
                            System.out.println("3. Exit");
                            System.out.println("Your choice: ");

                            try {
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        updateDepartureDate(selectedTrip);
                                        break;
                                    case 2:
                                        updateArrivalDate(selectedTrip);
                                        break;
                                    case 3:
                                        new TripMenu();
                                        return;
                                    default:
                                        System.out.println("Please choose the correct option!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please pick a number");
                                scanner.nextLine(); // Consume the invalid input
                            }
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please pick a number");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public void updateDepartureDate(Trip selectedTrip) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println("Enter new departure date in this format (yyyy-MM-dd): ");
            String departureDate = scanner.nextLine();
            try {
                LocalDate parsedDate = LocalDate.parse(departureDate, dateFormatter);
                selectedTrip.setDepartureDate(parsedDate);
                PortSystem.updateTripInfo(selectedTrip);
                break;
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd-MM-yyyy.");
            }
        }
    }

    public void updateArrivalDate(Trip selectedTrip) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println("Enter new arrival date in this format (yyyy-MM-dd): ");
            String arrivalDate = scanner.nextLine();
            try {
                LocalDate parsedDate = LocalDate.parse(arrivalDate, dateFormatter);
                selectedTrip.setArriveDate(parsedDate);
                PortSystem.updateTripInfo(selectedTrip);
                break;
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd-MM-yyyy.");
            }
        }
    }



}
