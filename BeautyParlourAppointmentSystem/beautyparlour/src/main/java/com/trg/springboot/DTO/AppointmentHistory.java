package com.trg.springboot.DTO;

import java.sql.Date;

public class AppointmentHistory {

	 private String clientName;
	 private String clientEmail;
	 private int appointmentId;
	 private Date appointmentDate;
	 private String appointmentTime;
	 private String serviceName;
	public AppointmentHistory(String clientName, String clientEmail, int appointmentId, Date appointmentDate,
			String appointmentTime, String serviceName) {
		super();
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.appointmentId = appointmentId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.serviceName = serviceName;
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
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	@Override
	public String toString() {
		return "AppointmentHistory [clientName=" + clientName + ", clientEmail=" + clientEmail + ", appointmentId="
				+ appointmentId + ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime
				+ ", serviceName=" + serviceName + "]";
	}
	 
	 

}
