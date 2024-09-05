package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			System.out.print("Room number: ");
			int number = sc.nextInt();
			System.out.print("Check-in date (dd/MM/yyyy): ");
			LocalDate checkIn = LocalDate.parse(sc.next(), sdf);
			System.out.print("Check-out date (dd/MM/yyy): ");
			LocalDate checkOut = LocalDate.parse(sc.next(), sdf);

			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println(reservation);

			System.out.println("\nEnter data to update the reservation: ");
			System.out.print("Check-in date (dd/MM/yyy): ");
			checkIn = LocalDate.parse(sc.next(), sdf);
			System.out.print("Check-out date (dd/MM/yyy): ");
			checkOut = LocalDate.parse(sc.next(), sdf);

			reservation.updateDates(checkIn, checkOut);
			System.out.println(reservation);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format");
		}
		catch (DomainException e) {
			System.out.println(e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.println("Unexpected error: " + e);
		}

		sc.close();
	}

}
