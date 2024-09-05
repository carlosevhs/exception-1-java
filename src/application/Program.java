package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.print("Room number: ");
		int number = sc.nextInt();
		System.out.print("Check-in date (dd/MM/yyy): ");
		LocalDate checkIn = LocalDate.parse(sc.next(), sdf);
		System.out.print("Check-out date (dd/MM/yyy): ");
		LocalDate checkOut = LocalDate.parse(sc.next(), sdf);

		if (checkIn.isAfter(checkOut)) {
			System.out.println("Error in resertation: Check-out date must be after check-in date!");
		} else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println(reservation);

			System.out.println("\nEnter data to update the reservation: ");
			System.out.print("Check-in date (dd/MM/yyy): ");
			checkIn = LocalDate.parse(sc.next(), sdf);
			System.out.print("Check-out date (dd/MM/yyy): ");
			checkOut = LocalDate.parse(sc.next(), sdf);

			String error = reservation.updateDates(checkIn, checkOut);
			if (error == null) {
				System.out.println(reservation);				
			}
			else {
				System.out.println(error);
			}

		}

		sc.close();
	}

}
