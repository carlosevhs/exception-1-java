package model.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.exceptions.DomainException;

public class Reservation {

	private Integer number;
	private LocalDate checkIn;
	private LocalDate checkOut;

	private static DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Reservation(Integer number, LocalDate checkIn, LocalDate checkOut) throws DomainException {
		if (checkIn.isAfter(LocalDate.now())) {
			throw new DomainException("Reservation dates must be in futures dates");
		}
		if (checkIn.isAfter(checkOut)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.number = number;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public long duration() {
		Duration time = Duration.between(checkIn.atStartOfDay(), checkOut.atStartOfDay());
		long t = time.toDays();
		return t;
	}

	public void updateDates(LocalDate checkIn, LocalDate checkOut) throws DomainException {
		if (LocalDate.now().isAfter(checkIn)) {
			throw new DomainException("Error in resertation: Reservation dates for update must be future dates");
		} 
		else if (checkIn.isAfter(checkOut)) {
			throw new DomainException("Error in resertation: Check-out date must be after check-in date!");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Reservation: Room ");
		s.append(number);
		s.append(", check-in: ");
		s.append(checkIn.format(fmt1));
		s.append(", check-out: ");
		s.append(checkOut.format(fmt1));
		s.append(", ");
		s.append(duration());
		s.append(" nights.");
		return s.toString();
	}
}
