package com.trg.springboot.DTO;

import java.sql.Date;

public class BookingHistory {

	private int bookingId;
	private Date bookingDate;
	 private String clientName;
	 private String clientEmail;
    private String courseName;
	public BookingHistory(int bookingId, Date bookingDate, String clientName, String clientEmail, String courseName) {
		
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.courseName = courseName;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "BookingHistory [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", clientName=" + clientName
				+ ", clientEmail=" + clientEmail + ", courseName=" + courseName + "]";
	}
    
}
